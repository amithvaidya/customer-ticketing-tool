package com.crm.CRMBackend.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.crm.CRMBackend.models.Agent;

public class AgentMapper implements RowMapper<Agent>{
	
	public Agent mapRow(ResultSet rs, int rowNum) throws SQLException{
		Agent agent = new Agent();
		agent.setId(rs.getInt("id"));
		agent.setName(rs.getString("name"));
		agent.setActivityStatus(rs.getString("status"));
		return agent;
	}
}
