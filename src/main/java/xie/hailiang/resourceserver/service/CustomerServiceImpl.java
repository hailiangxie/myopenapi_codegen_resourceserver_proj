package xie.hailiang.resourceserver.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xie.hailiang.resourceserver.entity.Customer;
import xie.hailiang.resourceserver.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	private final CustomerRepository customerRepository;
	
	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	@Transactional
	public Customer addCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Optional<Customer> findCustomerById(long id) {
		return customerRepository.findById(id);
	}
	
	@Override
	public Optional<Customer> findCustomerByEmail(String email) {
		return Optional.of(customerRepository.findByEmail(email));
	}

	@Override
	@Transactional
	public Customer updateCustomer(Customer customer) throws Exception {
		customerRepository.findById(customer.getId()).orElseThrow(() -> new Exception("Customer not found: " + customer.getId()));
		return customerRepository.save(customer);
	}

	@Override
	@Transactional
	public void deleteCustomer(long id) throws Exception {
		customerRepository.findById(id).orElseThrow(() -> new Exception("Customer not found: " + id));
		customerRepository.deleteById(id);
	}

}
