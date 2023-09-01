package com.crm.CRMBackend.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;

import com.crm.CRMBackend.models.Ticket;
import com.crm.CRMBackend.util.TableColumns;

public class TicketMapper implements RowMapper<Ticket>{
	
	public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException{
		Ticket ticket = new Ticket();
		
		ticket.setId(rs.getInt("id"));
		ticket.setStatus(rs.getString("status"));
		ticket.setCreatedTimestamp(rs.getTimestamp("created_timestamp").toLocalDateTime());
		ticket.setTitle(rs.getString("title"));
		ticket.setAgentId(rs.getInt("agent_id"));
		ticket.setPriority(rs.getInt("priority"));
		ticket.setCustomerId(rs.getInt("customer_id"));
		ticket.setCsatRating(rs.getInt("csat_rating"));
		return ticket;
	}
}
