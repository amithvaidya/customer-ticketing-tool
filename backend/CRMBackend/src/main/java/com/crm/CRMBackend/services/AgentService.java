package com.crm.CRMBackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.crm.CRMBackend.models.Agent;

@Service
public class AgentService {
	
	@Autowired
	private Agent agent;
	
	@GetMapping("/tickets")
	public ResponseEntity<String>tickets(){
		return new ResponseEntity<>("", HttpStatus.OK);
	}
	
}
