package org.vicrul.customerservice;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.vicrul.customerservice.model.entity.Address;
import org.vicrul.customerservice.model.entity.Customer;
import org.vicrul.customerservice.exception.AddressFieldException;
import org.vicrul.customerservice.exception.EmptyRequestParamsException;
import org.vicrul.customerservice.exception.SexFieldException;
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

		Mockito.verify(addressRepository, Mockito.times(1)).save(address);
		Mockito.verify(customerRepository, Mockito.times(1)).save(customer);
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
	
	@Test
	public void shouldThrowSexFieldException() {
		LocalDateTime date = LocalDateTime.now();
		Address address = new Address("Russia", "NSK", "Novosibirsk", "Lenina", "2", "21", date, date);
		Customer customer = new Customer("Petr", "Petrov", "Petrovich", "CBHSJACB", address, address);
		
		assertThrows(SexFieldException.class, () -> {
			customerService.saveCustomer(customer, false);
		});
	}
	
	@Test
	public void shouldThrowSexFieldExceptionWithNullSexField() {
		LocalDateTime date = LocalDateTime.now();
		Address address = new Address("Russia", "NSK", "Novosibirsk", "Lenina", "2", "21", date, date);
		Customer customer = new Customer("Petr", "Petrov", "Petrovich", null, address, address);
		
		assertThrows(SexFieldException.class, () -> {
			customerService.saveCustomer(customer, false);
		});
	}
	
	@Test
	public void shouldThrowSexFieldExceptionWithEmptySexField() {
		LocalDateTime date = LocalDateTime.now();
		Address address = new Address("Russia", "NSK", "Novosibirsk", "Lenina", "2", "21", date, date);
		Customer customer = new Customer("Petr", "Petrov", "Petrovich", "", address, address);
		
		assertThrows(SexFieldException.class, () -> {
			customerService.saveCustomer(customer, false);
		});
	}
	
	@Test
	public void shouldThrowEmptyRequestParamsFieldException() {		
		assertThrows(EmptyRequestParamsException.class, () -> {
			customerService.findByFirstNameAndLastName(null, "vsdvs");
		});
	}
	
	@Test
	public void shouldThrowAddressFieldException() {
		LocalDateTime date = LocalDateTime.now();
		Address address = new Address("Russia", "NSK", "Novosibirsk", "Lenina", "2", "21", date, date);
		Customer customer = new Customer("Petr", "Petrov", "Petrovich", "male", null, address);
		
		assertThrows(AddressFieldException.class, () -> {
			customerService.saveCustomer(customer, false);
		});
	}
}
