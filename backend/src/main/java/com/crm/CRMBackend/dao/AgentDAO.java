
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

//TODO: complete the imports

@Component
public class AgentDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public int createAgent(Agent agent) {
		return jdbcTemplate.update(
			"insert into agents (id, name, status) values (?,?,?)",
			agent.getId(),
			agent.getName(),
			agent.getActivityStatus()
			);
	}
	
	public List<Integer> getAllAgentIds(){
		List<Integer> agentIds = jdbcTemplate.query("select id from agents", new RowMapper<Integer>() {
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
	
	public List<Agent> getAllAgents(){
		return jdbcTemplate.query("select * from agents", new AgentMapper());
	}
	
	
}