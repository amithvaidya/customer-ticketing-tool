package com.crm.CRMBackend.controllers;

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
	public ResponseEntity<List<Ticket>> ticekts(@RequestParam int agentId){		
		return new ResponseEntity<>(agentService.getTickets(agentId), HttpStatus.OK);
	}
	
	
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getCustomers(){
		return new ResponseEntity<>(agentService.getCustomers(), HttpStatus.OK);
	}
	
	
	@PostMapping("/add-response")
	public ResponseEntity<String> addResponse(@RequestBody Response response){
		agentService.addResponse(response);
		return new ResponseEntity<>("Response added.", HttpStatus.OK);
	}

}
 