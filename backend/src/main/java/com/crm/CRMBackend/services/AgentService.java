package com.crm.CRMBackend.services;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.crm.CRMBackend.dao.GeneralDAO;
import com.crm.CRMBackend.dao.TicketDAO;
import com.crm.CRMBackend.dao.AgentDAO;
import com.crm.CRMBackend.dao.ResponseDAO;
import com.crm.CRMBackend.dao.CustomerDAO;


import com.crm.CRMBackend.models.Customer;
import com.crm.CRMBackend.models.Response;
import com.crm.CRMBackend.models.Ticket;

@Service
public class AgentService {
	
	
	@Autowired
	private GeneralDAO dao;
	
	@Autowired private TicketDAO ticketDAO;
	@Autowired private AgentDAO agentDAO;
	@Autowired private ResponseDAO responseDAO;
	@Autowired private CustomerDAO customerDAO;
	
	public List<Ticket> getTickets(int agentId) {
		return ticketDAO.getAllTicketsForAgent(agentId);
	} 
	
	//TODO: Method to get all responses for a ticket
	
	public List<Customer> getCustomers(){
		return customerDAO.getAllCustomers();
	}
	
	
	public int addResponse(Response response) {
		return responseDAO.createResponse(response);
	}
	
	//TODO: Define the following methods in DAO
	public int editResponse(Response response) {
		return responseDAO.updateResponse(response);
	}
	
	/*
	TODO: Implement DAO for this method
	public int deleteResponse(int responseId) {
		return responseDAO.deleteResponse(responseId);
	} */
	
	public int addAttachmentToResponse(byte[] fileData) {
		return 0;
	}
	/*
	TODO: Implement DAO for this method
	public int updateAgentActivityStatus(String status) {
		return agentDAO.updateAgentActivityStatus(status);
	}
	*/
	
}
