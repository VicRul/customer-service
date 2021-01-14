package org.vicrul.customerservice.service;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.vicrul.customerservice.entity.Address;
import org.vicrul.customerservice.entity.Customer;
import org.vicrul.customerservice.exception.AddressFieldException;
import org.vicrul.customerservice.exception.EmptyRequestParamsException;
import org.vicrul.customerservice.exception.SexFieldException;
import org.vicrul.customerservice.repository.AddressRepository;
import org.vicrul.customerservice.repository.CustomerRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
	
	private final CustomerRepository customerRepository;
	private final AddressRepository addressRepository;

	@Override
	public Customer saveCustomer(Customer newCustomer, boolean actualAddresIsRegisteredAddress) {

		if (newCustomer.getSex() == null || newCustomer.getSex().isEmpty())
			throw new SexFieldException();
		
		if (!(newCustomer.getSex().equals("male")) && !(newCustomer.getSex().equals("female")))
			throw new SexFieldException();
		
		if (newCustomer.getActualAddress() == null || newCustomer.getRegistredAddress() == null)
			throw new AddressFieldException();
		
		Address actualAddress = null;
		Address registeredAddress = null;
		
		if (actualAddresIsRegisteredAddress) {
			actualAddress = addressRepository.save(newCustomer.getActualAddress());
			registeredAddress = actualAddress;
		} else {
			actualAddress = addressRepository.save(newCustomer.getActualAddress());
			registeredAddress = addressRepository.save(newCustomer.getRegistredAddress());
		}
		
		newCustomer.setActualAddress(actualAddress);
		newCustomer.setRegistredAddress(registeredAddress);
		return customerRepository.save(newCustomer);
	}

	@Override
	public List<Customer> findByFirstNameAndLastName(String firstName, String lastName) {
		
		if (firstName == null || lastName == null)
			throw new EmptyRequestParamsException();
		
		List<Customer> foundedCustomers = customerRepository.findByFirstNameAndLastName(firstName, lastName);
		return (foundedCustomers.size() > 0) ? foundedCustomers : Collections.emptyList() ;
	}

	@Override
	public boolean updateActualAddress(long customerId, Address newActualAddress) {
		
		if (newActualAddress == null)
			throw new EmptyRequestParamsException();
		
		Address newSavedAddress = addressRepository.save(newActualAddress);
		return customerRepository.updateActualAddress(customerId, newSavedAddress) == 1;
	}

}
