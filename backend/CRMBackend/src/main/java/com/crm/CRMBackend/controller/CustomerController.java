package com.crm.CRMBackend.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crm.CRMBackend.models.Response;
import com.crm.CRMBackend.models.Ticket;
import com.crm.CRMBackend.services.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/tickets")
	public ResponseEntity<List<Ticket>> viewTickets(@RequestParam Integer customerId){
		return new ResponseEntity<>(customerService.getTickets(customerId), HttpStatus.OK);
	}
	
	@PostMapping("/create-ticket")
	public ResponseEntity<Map<String, Object>> raiseTicket(@RequestBody Ticket ticket){	
		customerService.createTicket(ticket);
		return new ResponseEntity<>(Map.of("message", "Ticket has been created successfully."), HttpStatus.OK);
	}
	
	@GetMapping("/rate-ticket")
	public ResponseEntity<Map<String, Object>> rateService(@RequestParam Integer ticketId, @RequestParam Integer rating){	
		customerService.addRating(ticketId, rating);
		return new ResponseEntity<>(Map.of("message", "Ticket rating is updated successfully."), HttpStatus.OK);
	}
	
	@GetMapping("/responses")
	public ResponseEntity<List<Response>> responses(
				@RequestParam Integer ticketId
			){
		return new ResponseEntity<>(customerService.getResponses(ticketId), HttpStatus.OK);
	}
}
