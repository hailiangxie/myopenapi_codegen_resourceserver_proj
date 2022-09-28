package xie.hailiang.resourceserver.mapper;

import org.springframework.stereotype.Component;

import xie.hailiang.resourceserver.entity.Customer;
import xie.hailiang.resourceserver.model.CustomerJson;

@Component
public class CustomerMapperImpl implements CustomerMapper {

	@Override
	public CustomerJson toDto(Customer customer) {
		return new CustomerJson()
				.id(customer.getId())
				.firstName(customer.getFirstName())
				.lastName(customer.getLastName())
				.email(customer.getEmail())
				.phone(customer.getPhone())
				.status(customer.getStatus());
	}

	@Override
	public Customer toEntity(CustomerJson dto) {
		return new Customer().builder()
				.id(dto.getId())
				.firstName(dto.getFirstName())
				.lastName(dto.getLastName())
				.email(dto.getEmail())
				.phone(dto.getPhone())
				.status(dto.getStatus())
				.build();
	}

}

