package xie.hailiang.resourceserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xie.hailiang.resourceserver.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>  {

}
