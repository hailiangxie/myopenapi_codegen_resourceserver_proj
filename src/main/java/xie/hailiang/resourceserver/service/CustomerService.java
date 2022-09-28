package xie.hailiang.resourceserver.service;

import java.util.Optional;

import xie.hailiang.resourceserver.entity.Customer;

public interface CustomerService {
	
	Customer addCustomer(Customer customer);
	Optional<Customer> findCustomerById(long id);
	Customer updateCustomer(Customer customer) throws Exception;
	void deleteCustomer(long id) throws Exception;
}
