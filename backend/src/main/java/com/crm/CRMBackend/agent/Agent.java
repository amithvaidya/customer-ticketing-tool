package com.crm.CRMBackend.agent;

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
	private float avgCustomerRating;
	private int ticketsAssigned;
	private String avgResponseTime;
	private String avgResolutionTime;
	
}
