package org.vicrul.customerservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private long id;
	
	@Getter @Setter
	private String firstName;
	
	@Getter @Setter
	private String lastName;
	
	@Getter @Setter
	private String middleName;
	
	@Column(nullable = false, length = 6)
	@Getter @Setter
	private String sex;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "registred_address_id", nullable = false)
	@Getter @Setter
	private Address registredAddress;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "actual_address_id", nullable = false)
	@Getter @Setter
	private Address actualAddress;

	public Customer(String firstName, String lastName, String middleName, String sex, Address registredAddress, Address actualAddress) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.sex = sex;
		this.registredAddress = registredAddress;
		this.actualAddress = actualAddress;
	}
}
