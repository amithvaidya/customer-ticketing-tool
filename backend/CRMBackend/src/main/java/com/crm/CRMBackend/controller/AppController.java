package com.crm.CRMBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crm.CRMBackend.services.AdminService;

@RestController
public class AppController {
	
	@Autowired
	private AdminService service;
	
	@GetMapping("/avg-resp-time")
	public ResponseEntity<Long> avgResponseTime(@RequestParam String ticketId){
		Long avgRespTime = service.getAvgResponseTimeForTicket(ticketId);
		return new ResponseEntity<Long>(avgRespTime, HttpStatus.OK);
	}
	
	@GetMapping("/avg-response-time-for-agent")
	public ResponseEntity<Long> avgResponseTimeForAgent(@RequestParam String agentId){
		Long avgRespTime = service.getAvgResponseTime(agentId);
		return new ResponseEntity<Long>(avgRespTime, HttpStatus.OK);
	}
}
