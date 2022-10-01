package xie.hailiang.resourceserver.api;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import xie.hailiang.resourceserver.entity.Customer;
import xie.hailiang.resourceserver.mapper.CustomerMapper;
import xie.hailiang.resourceserver.model.ApiResponseJson;
import xie.hailiang.resourceserver.model.CustomerJson;
import xie.hailiang.resourceserver.service.CustomerService;
import xie.hailiang.resourceserver.service.StorageService;

/**
 * 
 * @author Hailiang XIE
 * Implements the CustomerApi interface generated by the OpenApi.
 *
 */
@Component
public class CustomerApiDelegateImpl implements CustomerApiDelegate {
	
	private final CustomerService customerService;
	private final CustomerMapper customerMapper;
	private final StorageService storageService;
	
	@Autowired
	public CustomerApiDelegateImpl(CustomerService customerService, CustomerMapper customerMapper, StorageService storageService) {
		this.customerService = customerService;
		this.customerMapper = customerMapper;
		this.storageService = storageService;
	}
	
	@Override
	public ResponseEntity<CustomerJson> addCustomer(CustomerJson customerJson) {
		Customer customer = customerService.addCustomer(customerMapper.toEntity(customerJson));
		return new ResponseEntity<>(customerMapper.toDto(customer), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Void> deleteCustomer(Long customerId) {
		try {
			customerService.deleteCustomer(customerId);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<CustomerJson> getCustomerById(Long customerId) {
		Customer customer = null;
		try {
			customer = customerService.findCustomerById(customerId)
					.orElseThrow(() -> new Exception("Customer not found: " + customerId));
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(customerMapper.toDto(customer), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<CustomerJson> updateCustomer(CustomerJson customerJson) {
		Customer customer = null;
		try {
			customer = customerService.updateCustomer(customerMapper.toEntity(customerJson));
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(customerMapper.toDto(customer), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<CustomerJson> getCustomerByEmail(String email) {
		Customer customer = null;
		try {
			customer = customerService.findCustomerByEmail(email)
					.orElseThrow(() -> new Exception("Customer not found: " + email));
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(customerMapper.toDto(customer), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<ApiResponseJson> uploadFile(Long customerId, MultipartFile fileName) {
		ApiResponseJson responseJson = new ApiResponseJson();
		try {
			storageService.createStorageLocationById(customerId);
			storageService.store(fileName);
			responseJson.setCode(200);
			responseJson.setMessage("Upload file successfully!");
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(responseJson, HttpStatus.OK);
	}
}
