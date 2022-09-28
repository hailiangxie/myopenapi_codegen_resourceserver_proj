package xie.hailiang.resourceserver.mapper;

import xie.hailiang.resourceserver.entity.Customer;
import xie.hailiang.resourceserver.model.CustomerJson;

public interface CustomerMapper {
	
	CustomerJson toDto(Customer customer);
	
	Customer toEntity(CustomerJson dto);
}

