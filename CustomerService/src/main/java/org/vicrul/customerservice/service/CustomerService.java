package org.vicrul.customerservice.service;

import java.util.List;

import org.vicrul.customerservice.entity.Address;
import org.vicrul.customerservice.entity.Customer;

public interface CustomerService {

	Customer saveCustomer(Customer newCustomer, boolean actualAddresIsRegisteredAddress);
	List<Customer> findByFirstNameAndLastName(String firstName, String lastName);
	Customer updateActualAddress(long customerId, Address newActualAddress);
}
