package org.vicrul.customerservice;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.vicrul.customerservice.model.entity.Address;
import org.vicrul.customerservice.model.entity.Customer;
import org.vicrul.customerservice.repository.AddressRepository;
import org.vicrul.customerservice.repository.CustomerRepository;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

@SpringBootTest
@TestPropertySource("/application-test.properties")
@Sql(value = "/insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class RepositoryTest {

	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	AddressRepository addressRepository;

	@Test
	@Transactional
	public void shouldUpdateActualAddress() {
		Address address = addressRepository.findById(95L).get();
		assertEquals(customerRepository.updateActualAddress(96L, address), 1);
	}
	
	@Test
	public void shouldFindCustomers() {
		List<Customer> customers = customerRepository.findByFirstNameAndLastName("Ivan", "Ivanov");
		assertEquals(customers.size(), 2);
	}
}
