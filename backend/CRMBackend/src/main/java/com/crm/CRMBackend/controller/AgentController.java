package com.crm.CRMBackend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crm.CRMBackend.models.Customer;
import com.crm.CRMBackend.models.Response;
import com.crm.CRMBackend.models.Ticket;
import com.crm.CRMBackend.services.AgentService;

@RestController
@RequestMapping("/agent")
public class AgentController {
	
	@Autowired
	private AgentService agentService;
	
	
	
	@GetMapping("/tickets")
	public ResponseEntity<List<Ticket>> ticekts(
			@RequestParam(required = false) Integer ticketId,
			@RequestParam(required = false) Integer agentId,
			@RequestParam(required = false) String title,
			@RequestParam(required = false) String status
	){
		
		Map<String, Object> param = Map.of(
					"agent_id", agentId,
					"id", ticketId,
					"title", title,
					"status", status
				);
		
		return new ResponseEntity<>(agentService.getTickets(param), HttpStatus.OK);
	}
	
	
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> customers(
			@RequestParam(required = false) String customerName,
			@RequestParam(required = false) String company,
			@RequestParam(required = false) Integer customerId
			
	){
		Map<String, Object> params = Map.of(
				"name", customerName,
				"company", company,
				"id", customerId
			);
		return new ResponseEntity<>(agentService.getCustomers(params), HttpStatus.OK);
	}
	
	
	@PostMapping("/add-response")
	public ResponseEntity<Map<String, Object>> addResponse(@RequestBody Response response){
		String message = agentService.addResponse(response)?"Response added successfully.":"Unable to add response.";
		return new ResponseEntity<>(Map.of("message", message), HttpStatus.OK);
	}
	
	
}
