package com.crm.CRMBackend.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.crm.CRMBackend.models.Agent;
import com.crm.CRMBackend.models.Customer;
import com.crm.CRMBackend.models.Ticket;

public class GeneralDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public List<Ticket> getTickets(){
		return jdbcTemplate.query("select * from ticket", new TicketMapper());
	}
	
	public List<Ticket> getTicketsForAgent(int agentId){
		return jdbcTemplate.query("select * from ticket where agent_id = ?", new Object[] {agentId}, new TicketMapper());
	}
	
	public List<Ticket> getTicketsForCustomer(int customerId){
		return jdbcTemplate.query("select * from ticket where agent_id = ?", new Object[] {customerId}, new TicketMapper());
	}
	
	
	public List<Agent> getAgents(){
		return jdbcTemplate.query("select * from agent", new AgentMapper());
	}
	
	public List<Customer> getCustomers(){
		return jdbcTemplate.query("select * from customer", new CustomerMapper());
	}
	
	
	
	
}
