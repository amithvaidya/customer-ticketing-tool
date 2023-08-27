package com.crm.CRMBackend.models;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Agent {
	
	private String name;
	private int id;
	private String activityStatus;	
	private float avgCustomerRating;
	private int numTicketsAssigned;
	private String avgResponseTime;
	private String avgResolutionTime;
	private List<Ticket> tickets;
}
