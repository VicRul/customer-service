package org.vicrul.customerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vicrul.customerservice.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
