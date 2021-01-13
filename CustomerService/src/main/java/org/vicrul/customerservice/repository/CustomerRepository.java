package org.vicrul.customerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vicrul.customerservice.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
