package com.crm.CRMBackend.models;

import java.time.LocalDateTime;

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
public class Response {
	
	private int id;
	private int agentId;
	private int customerId;
	private int ticketId;
	private String message;
	private String ticketStatus;
	private LocalDateTime createdTime;
	private boolean responseByAgent;
	
}
