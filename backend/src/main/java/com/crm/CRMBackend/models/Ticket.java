package com.crm.CRMBackend.models;

import java.time.LocalDateTime;
import java.time.LocalTime;
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
public class Ticket {
	
	private int id;
	private String title;
	private String status;
	private LocalDateTime createdTimestamp;
	private int agentId;
	private String agentName;

	private int customerId;
	private String customerName;
	private String companyName;

	private int priority;
	private String priorityLabel;

	private float csatRating;	
}
