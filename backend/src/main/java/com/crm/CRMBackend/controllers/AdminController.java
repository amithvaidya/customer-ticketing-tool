package com.crm.CRMBackend.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crm.CRMBackend.models.Agent;
import com.crm.CRMBackend.models.Response;
import com.crm.CRMBackend.models.Ticket;
import com.crm.CRMBackend.services.AdminService;
import com.crm.CRMBackend.services.AgentService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminController {
	
	@Autowired
	private AdminService service;
	
	@GetMapping("/hello")
	public ResponseEntity<String> sayHello(){
		return new ResponseEntity<>("Hey!", HttpStatus.OK);
	}
	
	@GetMapping("/agents")
	public ResponseEntity<List<Agent>> agents(){
		return new ResponseEntity<>(service.getAgentAnalytics(), HttpStatus.OK);
	}
	
	@GetMapping("/tickets")
	public ResponseEntity<List<Ticket>> tickets(){
		return new ResponseEntity<>(service.getAllTickets(), HttpStatus.OK);
	}
	
	@PostMapping("/create-agent")
	public ResponseEntity<Integer> createAgent(@RequestBody Agent agent){
		return new ResponseEntity<>(service.createAgent(agent), HttpStatus.OK);
	}
		
	@PostMapping("/add-response")
	public ResponseEntity<Integer> addComment(@RequestBody Response comment){
		service.addResponse(comment);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//TODO: This has some errors while running
	@PostMapping("/edit-response")
	public ResponseEntity<Integer> editComment(
				@RequestBody Response response
	)
	{
		
		return new ResponseEntity<>(service.editResponse(response), HttpStatus.OK);
	}
	
	@GetMapping("/responses")
	public ResponseEntity<List<Response>> responses(
				@RequestParam Integer ticketId
	)
	{
		return new ResponseEntity<>(service.getAllResponsesForTicket(ticketId), HttpStatus.OK);
	}
}
