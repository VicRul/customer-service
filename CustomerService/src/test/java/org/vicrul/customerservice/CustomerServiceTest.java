package org.vicrul.customerservice;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.vicrul.customerservice.entity.Address;
import org.vicrul.customerservice.entity.Customer;
import org.vicrul.customerservice.repository.AddressRepository;
import org.vicrul.customerservice.repository.CustomerRepository;
import org.vicrul.customerservice.service.CustomerService;

@SpringBootTest
public class CustomerServiceTest {

	@Autowired
	CustomerService customerService;
	@MockBean
	CustomerRepository customerRepository;
	@MockBean
	AddressRepository addressRepository;
	
	@Test
	public void shouldSaveNewCustomerWithSameAddresses() {

		LocalDateTime date = LocalDateTime.now();
		Address address = new Address("Russia", "NSK", "Novosibirsk", "Lenina", "2", "21", date, date);
		Customer customer = new Customer("Petr", "Petrov", "Petrovich", "male", address, address);
		
		customerService.saveCustomer(customer, true);
		
		Mockito.verify(customerRepository, Mockito.times(1)).save(customer);
		Mockito.verify(addressRepository, Mockito.times(1)).save(address);
	}
	
	@Test
	public void shouldSaveNewCustomerWithDifferentAddresses() {

		LocalDateTime date = LocalDateTime.now();
		Address registeredAddress = new Address("Russia", "NSK", "Novosibirsk", "Lenina", "2", "21", date, date);
		Address actualAddress = new Address("Russia", "NSK", "Novosibirsk", "Visotskogo", "15", "12", date, date);
		Customer customer = new Customer("Petr", "Petrov", "Petrovich", "male", registeredAddress, actualAddress);
		
		customerService.saveCustomer(customer, false);
		
		Mockito.verify(customerRepository, Mockito.times(1)).save(customer);
		Mockito.verify(addressRepository, Mockito.times(1)).save(registeredAddress);
		Mockito.verify(addressRepository, Mockito.times(1)).save(actualAddress);
	}
}
