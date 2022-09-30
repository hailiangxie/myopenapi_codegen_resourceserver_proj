package xie.hailiang.resourceserver.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import xie.hailiang.resourceserver.MyOpenApiCodegenResourceServerApplication;
import xie.hailiang.resourceserver.entity.Customer;
import xie.hailiang.resourceserver.repository.CustomerRepository;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = MyOpenApiCodegenResourceServerApplication.class)
@AutoConfigureMockMvc
class CustomerServiceTest {
	
	@Mock
	private CustomerRepository customerRepository;
	
	@InjectMocks
	private CustomerServiceImpl customerService;
	
	private Customer customer;
	
	@BeforeEach
	public void setUp() throws Exception {
		customer = new Customer().builder()
				.id(1L)
				.firstName("AA")
				.lastName("Li")
				.email("ali@email.com")
				.phone("11112222")
				.status(1)
				.build();
	}
	
	@Test
    public void test1_whenAddCustomer_returnCustomer() {
		when(customerRepository.save(customer)).thenReturn(customer);
		
		Customer createdCustomer = customerService.addCustomer(customer);
		assertThat(createdCustomer).isNotNull();
		assertThat(createdCustomer.getEmail()).isSameAs(customer.getEmail());
    }
	
	@Test
    public void test2_whenUpdateExistingCustomer_returnCustomer() {
		when(customerRepository.save(customer)).thenReturn(customer);
		when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));
		
		customer.setFirstName("BB");
		customer.setEmail("bli@email.com");
		Customer updatedCustomer = null;
		try {
			updatedCustomer = customerService.updateCustomer(customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertThat(updatedCustomer).isNotNull();
		assertThat(updatedCustomer.getEmail()).isSameAs(customer.getEmail());
    }
	
	@Test
    public void test3_whenUpdateNotExistsCustomer_returnCustomerNotFound() {
		when(customerRepository.findById(customer.getId())).thenReturn(Optional.empty());
		
		customer.setFirstName("BB");
		customer.setEmail("bli@email.com");
		Customer updatedCustomer = null;
		try {
			updatedCustomer = customerService.updateCustomer(customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertThat(updatedCustomer).isNull();
    }
	
	@Test
    public void test4_whenDeleteExistingCustomer_thenOk() {
		when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));
		
		try {
			customerService.deleteCustomer(customer.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		verify(customerRepository, times(1)).deleteById(customer.getId());;
    }
	
	@Test
    public void test5_givenExistingCustomer_whenFindCustomerByEmail_returnCustomer() {
		when(customerRepository.findByEmail(customer.getEmail())).thenReturn(customer);
		
		Optional<Customer> foundCustomer = customerService.findCustomerByEmail(customer.getEmail());
		assertTrue(!foundCustomer.isEmpty());
		assertThat(foundCustomer.get().getEmail()).isSameAs(customer.getEmail());
    }
}
