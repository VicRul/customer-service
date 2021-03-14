package org.vicrul.customerservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.vicrul.customerservice.model.entity.Address;
import org.vicrul.customerservice.model.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	List<Customer> findByFirstNameAndLastName(String firstName, String lastName);
	
	@Modifying
	@Query("UPDATE Customer c SET c.actualAddress = :newActualAddress WHERE c.id = :customerId")
	int updateActualAddress(@Param("customerId") long customerId, @Param("newActualAddress") Address newActualAddress);
}
