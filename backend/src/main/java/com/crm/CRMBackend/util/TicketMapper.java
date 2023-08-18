package com.crm.CRMBackend.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;

public class TicketMapper implements RowMapper<Ticket>{
	
	public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException{
		Ticket ticket = new Ticket();
		
		ticket.setId(rs.getInt("id"));
		ticket.setDescription(rs.getString("description"));
//		ticket.setStatus(rs.getString("status"));
		ticket.setCreatedTime(
				LocalDateTime.of(
				rs.getDate("created_time").toLocalDate(),
				rs.getTime("created_time").toLocalTime()
			)
		);
		ticket.setTitle(rs.getString("title"));
		ticket.setAgentId(rs.getInt("agent_id"));
		return ticket;
	}
}
