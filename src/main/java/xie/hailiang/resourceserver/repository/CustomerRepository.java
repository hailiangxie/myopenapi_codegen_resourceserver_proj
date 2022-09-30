package xie.hailiang.resourceserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import xie.hailiang.resourceserver.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>  {
	
	@Query("SELECT c FROM Customer c WHERE c.email = ?1")
	Customer findByEmail(String email);
}
