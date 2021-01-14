package org.vicrul.customerservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.vicrul.customerservice.entity.Address;
import org.vicrul.customerservice.entity.Customer;
import org.vicrul.customerservice.service.CustomerService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CustomerController {
	
	private final CustomerService customerService;

	@GetMapping("/find")
	public List<Customer> findCustomers(
			@RequestParam(value = "firstName") String firstName,
			@RequestParam(value = "lastName") String lastName)
	{
		System.out.println(firstName + " " + lastName);
		return customerService.findByFirstNameAndLastName(firstName, lastName);
	}
	
	@PostMapping("/new")
	public ResponseEntity<Customer> createNewCustomer(
				@RequestBody Customer customer, 
				@RequestParam(value = "isSameAddresses") boolean isSameAddresses)
	{
		
		Customer savedCustomer = customerService.saveCustomer(customer, isSameAddresses);
		return new ResponseEntity<Customer>(savedCustomer, HttpStatus.CREATED);	
	}

	@PutMapping("/update")
	public ResponseEntity updateActualAddres(
				@RequestParam(value = "customerId", required = false, defaultValue = "1") String customerId,
				@RequestBody Address newActualAddress
			) 
	{
		
		boolean result = customerService.updateActualAddress(Long.parseLong(customerId), newActualAddress);
		if (!result) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok().build();
	}	
}
