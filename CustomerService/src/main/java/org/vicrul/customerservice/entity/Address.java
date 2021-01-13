package org.vicrul.customerservice.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter @Setter
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String contry;
	private String region;
	private String city;
	private String street;
	private String house;
	private String flat;
	private LocalDateTime created;
	private LocalDateTime modified;

	public Address(String contry, String region, String city, String street, String house, String flat, LocalDateTime created, LocalDateTime modified) {
		this.contry = contry;
		this.region = region;
		this.city = city;
		this.street = street;
		this.house = house;
		this.flat = flat;
		this.created = created;
		this.modified = modified;	}
}
