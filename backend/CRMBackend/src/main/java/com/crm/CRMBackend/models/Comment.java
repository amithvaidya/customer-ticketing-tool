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
public class Comment {
	
	private int id;
	private String message;
	private LocalDateTime createdTime;
	private int ticketId;
	
}
