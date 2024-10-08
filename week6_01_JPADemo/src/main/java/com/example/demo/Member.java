package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Member {
	@Id
	private String id;
	

	@Column(length=20)
	private String name;
	@Column(length=100)
	private String address;
}
