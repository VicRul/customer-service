package org.vicrul.customerservice.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.vicrul.customerservice.model.entity.Address;
import org.vicrul.customerservice.model.entity.Customer;
import org.vicrul.customerservice.exception.AddressFieldException;
import org.vicrul.customerservice.exception.EmptyRequestParamsException;
import org.vicrul.customerservice.exception.SexFieldException;
import org.vicrul.customerservice.model.Sex;
import org.vicrul.customerservice.repository.AddressRepository;
import org.vicrul.customerservice.repository.CustomerRepository;

import lombok.AllArgsConstructor;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    @Override
    public Customer saveCustomer(Customer newCustomer, boolean actualAddressIsRegisteredAddress) {
        checkSexField(newCustomer.getSex());
        checkAddress(newCustomer.getRegistredAddress());

        List<Address> addressesForSave = new ArrayList<>();
        addressesForSave.add(newCustomer.getRegistredAddress());

        if (!actualAddressIsRegisteredAddress) {
            checkAddress(newCustomer.getActualAddress());
            addressesForSave.add(newCustomer.getActualAddress());
        }

        List<Address> savedAddresses = saveAddresses(addressesForSave, actualAddressIsRegisteredAddress);

        newCustomer.setRegistredAddress(savedAddresses.get(0));
        newCustomer.setActualAddress(savedAddresses.get(1));
        return customerRepository.save(newCustomer);
    }

    @Override
    public List<Customer> findByFirstNameAndLastName(String firstName, String lastName) {
        checkCustomerInitials(firstName, lastName);

        List<Customer> foundedCustomers = customerRepository.findByFirstNameAndLastName(firstName, lastName);
        return (foundedCustomers.size() > 0) ? foundedCustomers : Collections.emptyList();
    }

    @Override
    @Transactional
    public boolean updateActualAddress(long customerId, Address newActualAddress) {
        checkAddress(newActualAddress);

        Address newSavedAddress = addressRepository.save(newActualAddress);
        return customerRepository.updateActualAddress(customerId, newSavedAddress) == 1;
    }

    @Override
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    private void checkSexField(String sexFieldValue) {
        if (sexFieldValue == null || sexFieldValue.isEmpty())
            throw new SexFieldException();

        String correctMaleValue = Sex.MALE.getAlias();
        String correctFemaleValue = Sex.FEMALE.getAlias();
        if (!(sexFieldValue.equals(correctMaleValue)) && !(sexFieldValue.equals(correctFemaleValue)))
            throw new SexFieldException();
    }

    private void checkAddress(Address addressForChecking) {
        if (addressForChecking == null)
            throw new AddressFieldException();
    }

    private List<Address> saveAddresses(List<Address> newAddresses, boolean actualAddressIsRegisteredAddress) {
        List<Address> savedAddresses = new ArrayList<>();
        Address actualAddress;
        Address registeredAddress = addressRepository.save(newAddresses.get(0));

        if (actualAddressIsRegisteredAddress) {
            actualAddress = registeredAddress;
        } else {
            actualAddress = addressRepository.save(newAddresses.get(1));
        }

        savedAddresses.add(registeredAddress);
        savedAddresses.add(actualAddress);
        return savedAddresses;
    }

    private void checkCustomerInitials(String firstName, String lastName) {
        if (firstName == null || lastName == null)
            throw new EmptyRequestParamsException();
    }

}
