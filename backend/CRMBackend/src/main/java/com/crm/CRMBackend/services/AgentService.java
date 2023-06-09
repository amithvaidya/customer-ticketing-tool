package com.crm.CRMBackend.services;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.crm.CRMBackend.models.Agent;
import com.crm.CRMBackend.models.Customer;
import com.crm.CRMBackend.models.Response;
import com.crm.CRMBackend.models.Ticket;

@Service
public class AgentService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	private String queryBuilder(String base, Map<String, Object> params) {
		String query = base;
		int count=0;
		for(String key : params.keySet()) {
			if(params.get(key) != null && count==0) {
				query+="where "+key+"=? ";
				count++;
			}
			else if(params.get(key) != null) {
				query+=" and "+key+"=? ";
			}
			else {
				//Do nothing.
			}
		}
		
		return query;
	}
	
	
	private Map<String, Object> validParamValues(Map<String, Object> params){
		Map<String, Object> validMap = new HashMap<String, Object>();
		for(String key: params.keySet()) {
			if(params.get(key) != null) validMap.put(key, params.get(key));
		}
		return validMap;
	}
	
	public List<Ticket> getTickets(Map<String, Object> params) {
		return jdbcTemplate.query(
				queryBuilder("select * from tickets ", params),
				validParamValues(params).values().toArray(),
				(rs, rowNum) -> {
					Ticket t = new Ticket();
					t.setId(rs.getInt("id"));
					t.setTitle(rs.getString("title"));
					t.setDescription(rs.getString("description"));
					t.setStatus(rs.getString("status"));
					t.setCreatedTime(rs.getTimestamp("created_time").toLocalDateTime());
					t.setAgentId(rs.getInt("agent_id"));
					return t;
				}
			);
	} 
	
	
	public List<Customer> getCustomers(Map<String, Object> params){
		return jdbcTemplate.query(
				queryBuilder("select * from customers ", params),
				validParamValues(params).values().toArray(),
				(rs, rowNum) -> {
					Customer t = new Customer();
					t.setId(rs.getInt("id"));
					t.setName(rs.getString("name"));
					t.setCompany(rs.getString("company"));
					t.setEmailId(rs.getString("email"));
					t.setPhone(rs.getString("phone"));
					return new Customer();
				}
		);
	}
	
	
	public boolean addResponse(Response response) {
		return jdbcTemplate.execute("insert into responses(message, created_time, ticket_id, ticket_status) values(?,?,?,?)", new PreparedStatementCallback<Boolean>() {
			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException{
				ps.setString(1, response.getMessage());
				ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
				ps.setInt(3, response.getId());
				ps.setString(4, "open");
				return ps.execute();
			}
		});
	}
	
	
}
