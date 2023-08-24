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
	
	private String agentName;
	private int agentId;
	private String agentActivityStatus;	
	private float avgCustomerRating;
	private int numTicketsAssigned;
	private String avgResponseTime;
	private String avgResolutionTime;
	private List<Ticket> tickets;
}
