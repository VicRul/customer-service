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
	@Getter
	private long id;
	
	@Getter @Setter
	private String contry;
	
	@Getter @Setter
	private String region;
	
	@Getter @Setter
	private String city;
	
	@Getter @Setter
	private String street;
	
	@Getter @Setter
	private String house;
	
	@Getter @Setter
	private String flat;
	
	@Getter @Setter
	private LocalDateTime created;
	
	@Getter @Setter
	private LocalDateTime modified;

	public Address(String contry, String region, String city, String street, String house, String flat, LocalDateTime created, LocalDateTime modified) {
		this.contry = contry;
		this.region = region;
		this.city = city;
		this.street = street;
		this.house = house;
		this.flat = flat;
		this.created = created;
		this.modified = modified;	
	}
}
