

package com.crm.CRMBackend.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.crm.CRMBackend.mappers.AgentMapper;
import com.crm.CRMBackend.mappers.CustomerMapper;
import com.crm.CRMBackend.mappers.TicketMapper;
import com.crm.CRMBackend.models.Agent;
import com.crm.CRMBackend.models.Customer;
import com.crm.CRMBackend.models.Response;
import com.crm.CRMBackend.models.Ticket;

@Component
public class ResponseDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Response> getAllResponsesForTicket(int ticketId){
		return jdbcTemplate.query(
				"select \n	ticket_id,\n    created_timestamp,\n    ticket_status,\n    message,\n    response_by_agent,\n    agent_name,\n    customer_name,\n    company_name\n from \n(select \n	ticket_id,\n    created_timestamp,\n    ticket_status,\n    message,\n    response_by_agent,\n    name as agent_name,\n    agent_id,\n    customer_id\n from \n	(select * from responses where ticket_id=?) as t1 \nleft join agents\n	on t1.agent_id = agents.id) as t2 left join customers on t2.customer_id = customers.id",
				new Object[] {ticketId},
				new RowMapper<Response>() {
					
					@Override
					public Response mapRow(ResultSet rs, int rowNum) {
						Response resp = new Response();
						try {
							// resp.setId(rs.getInt("id"));
							resp.setMessage(rs.getString("message"));
							resp.setTicketId(rs.getInt("ticket_id"));
							resp.setTicketStatus(rs.getString("ticket_status"));
							resp.setCreatedTimestamp(rs.getTimestamp("created_timestamp").toLocalDateTime());
							resp.setResponseByAgent(rs.getBoolean("response_by_agent"));
							// resp.setAgentId(rs.getInt("agent_id"));
							// resp.setCustomerId(rs.getInt("customer_id"));
							resp.setCustomerName(rs.getString("customer_name"));
							resp.setCompanyName(rs.getString("company_name"));
							resp.setAgentName(rs.getString("agent_name"));
						}catch(SQLException sqle) { sqle.printStackTrace(); }
						
						return resp;
					}
				}
				);
	}
	
	
	public LocalDateTime getTicketResolvedTimestamp(int ticketId) {
		LocalDateTime resolvedTimestamp = jdbcTemplate.queryForObject(
				"select created_timestamp from responses where ticket_id=? and ticket_status=?",
				new Object[] {ticketId, "resolved"},
				LocalDateTime.class);
		
		return resolvedTimestamp;
	}
	
	public List<LocalDateTime> getTimestampsOfResponses(int ticketId, boolean responseByAgent, boolean orderByTime){
		
		String orderBy = (orderByTime)?" order by created_timestamp":"";
		String queryString = "select created_timestamp from responses where ticket_id = ? and response_by_agent = ?" + orderBy;
		
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
	

	public int createResponse(Response response) {
		return jdbcTemplate.update(
			"insert into responses (id, ticket_id, agent_id, customer_id, message, created_timestamp,  ticket_status, response_by_agent) values (?,?,?,?,?,?,?,?)",
			response.getId(),
			response.getTicketId(),
			response.getAgentId(),
			response.getCustomerId(),
			response.getMessage(),
			response.getCreatedTimestamp(),
			response.getTicketStatus(),
			response.getResponseByAgent()
		);
	}
	
	public int updateResponse(Response response){
		
		Response tempResp = jdbcTemplate.queryForObject("select * from responses where id=?", Response.class);
		
			if(response.getTicketId()!=0) tempResp.setTicketId(response.getTicketId());
			if(response.getAgentId() != 0) tempResp.setAgentId(response.getAgentId());
			if(response.getCustomerId()!=0) tempResp.setCustomerId(response.getCustomerId());
			if(response.getMessage().equals("") ) tempResp.setMessage(response.getMessage());
			if(response.getCreatedTimestamp()!=null) tempResp.setCreatedTimestamp(response.getCreatedTimestamp());
			if(response.getTicketStatus()!=null) tempResp.setTicketStatus(response.getTicketStatus());
			tempResp.setResponseByAgent(tempResp.getResponseByAgent());
		
		return jdbcTemplate.update(
			"update responses set ticket_id=? agent_id=? customer_id=? message=? created_timestamp=? ticket_status=? response_by_agent=? where id=?",
			tempResp.getTicketId(),
			tempResp.getAgentId(),
			tempResp.getCustomerId(),
			tempResp.getMessage(),
			tempResp.getCreatedTimestamp(),
			tempResp.getTicketStatus(),
			tempResp.getResponseByAgent(),
			tempResp.getId()
		);
	}
	
}