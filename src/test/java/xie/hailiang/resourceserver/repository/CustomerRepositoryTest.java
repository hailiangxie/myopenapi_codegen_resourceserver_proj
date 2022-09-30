package xie.hailiang.resourceserver.repository;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import xie.hailiang.resourceserver.MyOpenApiCodegenResourceServerApplication;
import xie.hailiang.resourceserver.entity.Customer;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = MyOpenApiCodegenResourceServerApplication.class)
@ExtendWith(SpringExtension.class)
@DataJpaTest
class CustomerRepositoryTest {
	
	@Autowired
	private TestEntityManager em;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	private Customer customer;
	
	@BeforeEach
	public void setUpEach() {
		customer = new Customer().builder()
				.firstName("AA")
				.lastName("Li")
				.email("ali@email.com")
				.phone("11112222")
				.status(1)
				.build();
	}
	
	@Test
	void injectedComponentsAreNotNull(){
	    assertThat(em).isNotNull();
	}
	
	@Test
    void test1_givenNoAnyData_whenFindByEmail_returnNoCustomer() {
		Customer foundCustomer = customerRepository.findByEmail(customer.getEmail());
		
		assertThat(foundCustomer).isNull();
	}
	
	@Test
    void test2_givenExistingData_whenFindByEmail_returnCustomer() {
		em.persist(customer); //Avoid setting the Id , as it is auto generated
		Customer foundCustomer = customerRepository.findByEmail(customer.getEmail());
		
		assertThat(foundCustomer).isNotNull();
		assertThat(foundCustomer.getEmail()).isSameAs(customer.getEmail());
	}
	
}
