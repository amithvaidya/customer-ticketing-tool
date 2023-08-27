package com.crm.CRMBackend.models;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer {
	
	private int id;
	private String name;
	private List<Ticket> tickets;
	private String companyName;
	private String emailId;
	private String phone; 
	
}
