package com.crm.CRMBackend.customer;

import java.util.List;

import org.springframework.stereotype.Component;

import com.crm.CRMBackend.util.Ticket;

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
	private String password;
	private List<Ticket> tickets;
	private String company;
	private String emailId;
	private String phone; 
	
}
