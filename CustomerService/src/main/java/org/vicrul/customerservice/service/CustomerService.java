package org.vicrul.customerservice.service;

import java.util.List;

import org.vicrul.customerservice.model.entity.Address;
import org.vicrul.customerservice.model.entity.Customer;

public interface CustomerService {

	Customer saveCustomer(Customer newCustomer, boolean actualAddresIsRegisteredAddress);
	List<Customer> findByFirstNameAndLastName(String firstName, String lastName);
	boolean updateActualAddress(long customerId, Address newActualAddress);
	List<Customer> findAllCustomers();
}
