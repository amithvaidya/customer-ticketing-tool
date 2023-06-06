package com.crm.CRMBackend.controller;

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

import com.crm.CRMBackend.models.Admin;
import com.crm.CRMBackend.models.Agent;
import com.crm.CRMBackend.models.Comment;
import com.crm.CRMBackend.models.Ticket;
import com.crm.CRMBackend.services.AdminService;
import com.crm.CRMBackend.services.AgentService;

@RestController
@RequestMapping("/admin")

public class AdminController {
	
	@Autowired
	private AdminService service;
	
	@GetMapping("/agents")
	@CrossOrigin(origins = "*")
	public ResponseEntity<List<Map<String, Object>>> agents(){
		return new ResponseEntity<>(service.getAgents(), HttpStatus.OK);
	}
	
	@GetMapping("/tickets")
	public ResponseEntity<List<Ticket>> tickets(
			@RequestParam(name="ticket_id", required=false) String ticketId, 
			@RequestParam(name="agent_id", required=false) String agentId,
			@RequestParam(name="title", required=false) String title
	)
	{
		return new ResponseEntity<>(service.getTickets(ticketId, agentId, title), HttpStatus.OK);
	}
	
	@PostMapping("/create-agent")
	public ResponseEntity<String> createAgent(@RequestBody Agent agent){
		return new ResponseEntity<>(service.createAgent(agent), HttpStatus.OK);
	}
	
//	@PostMapping("/create-admin")
//	public ResponseEntity<String> createAdmin(@RequestBody Admin admin){
//		return service.createAdmin(admin);
//	}
	
	@PostMapping("/add-comment")
	public ResponseEntity<String> addComment(@RequestBody Comment comment){
		service.addComment(comment);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
//	@PostMapping("/edit-comment")
//	public ResponseEntity<String> editComment(){
//		return service.editComment();
//	}
//	
//	@PostMapping("/delete-comment")
//	public ResponseEntity<String> deleteComment(){
//		return service.deleteComment();
//	}
	
}
