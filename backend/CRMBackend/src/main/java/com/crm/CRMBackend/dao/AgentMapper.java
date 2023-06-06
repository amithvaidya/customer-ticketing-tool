package com.crm.CRMBackend.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.crm.CRMBackend.models.Agent;

public class AgentMapper implements RowMapper<Agent>{
	
	public Agent mapRow(ResultSet rs, int rowNum) throws SQLException{
		Agent agent = new Agent();
		agent.setAgentId(rs.getInt("agent_id"));
		agent.setAgentName(rs.getString("agent_name"));
		agent.setAvgCustomerRating(rs.getFloat("customer_rating"));
		return agent;
	}
}
