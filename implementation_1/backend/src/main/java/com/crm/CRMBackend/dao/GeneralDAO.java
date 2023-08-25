package com.crm.CRMBackend.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.crm.CRMBackend.mappers.AgentMapper;
import com.crm.CRMBackend.mappers.CustomerMapper;
import com.crm.CRMBackend.mappers.TicketMapper;
import com.crm.CRMBackend.models.Agent;
import com.crm.CRMBackend.models.Customer;
import com.crm.CRMBackend.models.Response;
import com.crm.CRMBackend.models.Ticket;

public class GeneralDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Integer numOfTicketsForAgent(int agentId) {
		return jdbcTemplate.queryForObject("select count(*) from ticket where agent_id = ?", new Object[] {agentId}, Integer.class);
	}
	
	public List<Response> getAllResponsesForTicket(int ticketId){
		return jdbcTemplate.query(
				"select * from response where ticket_id = ?",
				new Object[] {ticketId},
				new RowMapper<Response>() {
					
					@Override
					public Response mapRow(ResultSet rs, int rowNum) {
						Response resp = new Response();
						try {
							resp.setId(rs.getInt("id"));
							resp.setMessage(rs.getString("message"));
							resp.setTicketId(rs.getInt("ticket_id"));
							resp.setTicketStatus(rs.getString("ticket_status"));
							resp.setCreatedTime(rs.getTimestamp("created_timestamp").toLocalDateTime());
							resp.setResponseByAgent(rs.getBoolean("response_by_agent"));
							resp.setAgentId(rs.getInt("agent_id"));
							resp.setCustomerId(rs.getInt("customer_id"));
						}catch(SQLException sqle) { sqle.printStackTrace(); }
						
						return resp;
					}
				}
				);
	}
	
	public List<Ticket> getAllTickets(){
		return jdbcTemplate.query("select * from ticket", new TicketMapper());
	}
	
	public List<Ticket> getAllTicketsForAgent(int agentId){
		return jdbcTemplate.query("select * from ticket where agent_id = ?", new Object[] {agentId}, new TicketMapper());
	}
	
	public List<Ticket> getAllTicketsForCustomer(int customerId){
		return jdbcTemplate.query("select * from ticket where agent_id = ?", new Object[] {customerId}, new TicketMapper());
	}
	
	
	public List<Agent> getAllAgents(){
		return jdbcTemplate.query("select * from agent", new AgentMapper());
	}
	
	public List<Customer> getAllCustomers(){
		return jdbcTemplate.query("select * from customer", new CustomerMapper());
	}
	
	/**
	 * Fetch the ids of all the agents.
	 * */
	
	public List<Integer> getAllAgentIds(){
		List<Integer> agentIds = jdbcTemplate.query("select id as agentId, name as agentName from agents", new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rowNum) {
				try {
					return new Integer(rs.getInt("id"));
				}catch(SQLException sqle) {
					sqle.printStackTrace();
					return -1;
				}
			}
		});
		return agentIds;
	}
	
	
	/**
	 * Fetch the customer satisfaction rating (CSAT rating) for all the tickets that are handled by an agent
	 * */
	
	public List<Float> getCSATForAgent(int agentId){
		List<Float> agentTicketsCsat = jdbcTemplate.query(
				"select customer_rating from tickets where agent_id = ?", 
				new Object[] {agentId},
				new RowMapper<Float>() {
					@Override
					public Float mapRow(ResultSet rs, int rowNum) {
						Float f;
						try {
							f = new Float(rs.getFloat("csat_rating"));
						}
						catch(SQLException sqle) {
							sqle.printStackTrace();
							f = -1.0f;
						}
						return f;
					}
				}
			); 
		
		return agentTicketsCsat;
	}
	
	
	/**
	 * Fetch all the ticket IDs handled by an agent
	 * 
	 * */
	public List<Integer> getAllTicketsHandledByAgent(int agentId){
		List<Integer> ticketIds = jdbcTemplate.query(
				"select id as ticket_id from tickets where agent_id = ?", 
				new Object[] {agentId},
				new RowMapper<Integer>() {				
					@Override
					public Integer mapRow(ResultSet rs, int rowNum) {
						Integer i;
						try {
							i = rs.getInt("id");
						}catch(SQLException sqle) {
							sqle.printStackTrace();
							i = -1;
						}
						
						return i;
					}
				}
			);
		
		return ticketIds;
	}
	
	
	public List<Integer> getAllTicketsHandledByAgent(int agentId, String status){
		List<Integer> ticketIds = jdbcTemplate.query(
				"select id as ticket_id from tickets where agent_id = ? and status = ?", 
				new Object[] {agentId},
				new RowMapper<Integer>() {				
					@Override
					public Integer mapRow(ResultSet rs, int rowNum) {
						Integer i;
						try {
							i = rs.getInt("id");
						}catch(SQLException sqle) {
							sqle.printStackTrace();
							i = -1;
						}
						
						return i;
					}
				}
			);
		
		return ticketIds;
	}
	/**
	 * Get the count and the list of tickets resolved by an agent
	 * */
	public List<Integer> getTicketsByStatus(int agentId, String status){
		return null;
	}
	
	
	/**
	 * Get the resolved time stamp for a ticket
	 * */
	public LocalDateTime getTicketResolvedTImestamp(int ticketId) {
		LocalDateTime resolvedTimestamp = jdbcTemplate.queryForObject(
				"select created_timestamp from response where status=?",
				new Object[] {ticketId, "resolved"},
				LocalDateTime.class);
		
		return resolvedTimestamp;
	}
	
	
	/**
	 * Get time stamps of responses for a given ticket
	 * */
	
	public List<LocalDateTime> getTimestampsOfResponses(int ticketId, boolean responseByAgent, boolean orderByTime){
		
		String orderBy = (orderByTime)?" order by created_timestamp":"";
		String queryString = "select created_timestamp from response where ticket_id = ? and response_by_agent = ?" + orderBy;
		
		List<LocalDateTime> responseTimestampList = jdbcTemplate.query(
				queryString,
				new Object[] {ticketId, responseByAgent},
				new RowMapper<LocalDateTime>() {
					@Override
					public LocalDateTime mapRow(ResultSet rs, int rowNum) {
						LocalDateTime t;
						try {
							t = rs.getTimestamp("created_timestamp").toLocalDateTime();
						}catch(SQLException sqle) {
							sqle.printStackTrace();
							t = null;
						}
						return t;
					}
				}
				);
		
		return responseTimestampList;
	}
	
	
	public int createAgent(Agent agent) {
		return jdbcTemplate.update("insert into agent () values ()");
	}
	
	public int createTicket(Ticket ticket) {
		return 0;
	}
	
	public int createdResponse(Response response) {
		return 0;
	}
	
	public int createCustomer(Customer customer) {
		return 0;
	}
	
	public int addRating(int csatRating) {
		return 0;
	}
}
