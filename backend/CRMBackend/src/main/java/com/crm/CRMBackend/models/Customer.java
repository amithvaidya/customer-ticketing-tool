package com.crm.CRMBackend.models;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Customer {
	
	private int id;
	private String name;
	private String password;
	private List<Ticket> tickets;
	
}
