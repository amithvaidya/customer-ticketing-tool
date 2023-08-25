package com.crm.CRMBackend.services;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Service;

import com.crm.CRMBackend.dao.GeneralDAO;
import com.crm.CRMBackend.models.Response;
import com.crm.CRMBackend.models.Ticket;

@Service
public class CustomerService {
	
	
	@Autowired
	private GeneralDAO dao;
	
	public List<Response> getResponses(Integer ticketId){
		return dao.getAllResponsesForTicket(ticketId);
	}
	
	public List<Ticket> getTickets(int customerId){
		return dao.getAllTicketsForCustomer(customerId);
	}
	
	public int createTicket(Ticket t){
		return dao.createTicket(t);
	}
	
	
	public int addRatingForTicket(int rating) {
		return dao.addRating(rating);
	}
}
