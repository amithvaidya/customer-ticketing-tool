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
import com.crm.CRMBackend.models.Customer;
import com.crm.CRMBackend.models.Response;
import com.crm.CRMBackend.models.Ticket;

@Service
public class AgentService {
	
	
	@Autowired
	private GeneralDAO dao;
	
	public List<Ticket> getTickets(int agentId) {
		return dao.getAllTicketsForAgent(agentId);
	} 
	
	
	public List<Customer> getCustomers(){
		return dao.getAllCustomers();
	}
	
	
	public int addResponse(Response response) {
		return dao.createdResponse(response);
	}
	
	public int editResponse(Response response) {
		return 0;
	}
	
	public int deleteResponse(int responseId) {
		return 0;
	}
	
	public int addAttachmentToResponse(byte[] fileData) {
		return 0;
	}
	
	public int updateStatus(String status) {
		return 0;
	}
	
}
