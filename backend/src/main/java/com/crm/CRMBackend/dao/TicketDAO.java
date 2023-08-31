
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

public class TicketDAO {
	
	public LocalDateTIme getTicketCreatedTimestamp(int ticketId){
		return jdbcTemplate.queryForObject("select created_timestamp from ticket where id=?", new Object[]{ticketId}, LocalDateTIme.class);
	}

	public Integer numOfTicketsForAgent(int agentId) {
		return jdbcTemplate.queryForObject("select count(*) from ticket where agent_id = ?", new Object[] {agentId}, Integer.class);
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

	public List<Float> getCSATForAgent(int agentId){
		List<Float> agentTicketsCsat = jdbcTemplate.query(
				"select customer_rating from tickets where agent_id = ?", 
				new Object[] {agentId},
				new RowMapper<Float>() {
					@Override
					public Float mapRow(ResultSet rs, int rowNum) {
						Float f;
						try {
							if(rs.getFloat("csat_rating") != null)
								f = new Float(rs.getFloat("csat_rating"));
							else
								f = -1.0f;
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
				new Object[] {agentId, status},
				new RowMapper<Integer>() {				
					@Override
					public Integer mapRow(ResultSet rs, int rowNum) {
						Integer i;
						try {
							//Here since ID is a primary key, there is no need to check for null
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
	public int createTicket(Ticket ticket) {
		return jdbcTemplate.update(
			"insert into ticket (id, title, priority, status, created_timestamp, customer_id, agent_id) values (?,?,?,?,?,?,?)",
			ticket.getId(),
			ticket.getTitle(),
			ticket.getPriority(),
			ticket.getStatus(),
			ticket.getCreatedTimestamp(),
			ticket.getCustomerId(),
			ticket.getAgentId()
		);
	}
	
	public int addRating(int ticketId, int csatRating) {
		return jdbcTemplate.update("update ticket set csat_rating = ? where id = ?",
			csatRating,
			ticketId
		);
	}

}