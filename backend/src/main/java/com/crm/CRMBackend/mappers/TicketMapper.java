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
		
		ticket.setId(rs.getInt(TableColumns.Ticket.AGENT_ID));
//		ticket.setDescription(TableColumns.Ticket.DESCRIPTION);
//		ticket.setStatus(rs.getString("status"));
		ticket.setCreatedTimestamp(
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
