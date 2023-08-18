package com.crm.CRMBackend.admin;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.crm.CRMBackend.agent.Agent;
import com.crm.CRMBackend.agent.AgentMapper;
import com.crm.CRMBackend.util.Response;
import com.crm.CRMBackend.util.Ticket;
import com.crm.CRMBackend.util.TicketMapper;

@Service
public class AdminService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
		
	public List<Map<String, Object>> getAgents(){
		List<Map<String, Object>> agents = jdbcTemplate.queryForList("select id as agentId, name as agentName from agents");
		for(int i=0; i<agents.size(); i++) {
			System.out.println("Agent: "+agents.get(i).get("agentId").toString());
			agents.get(i).put("numOfTickets", getNumOfTickets(agents.get(i).get("agentId").toString()));
			agents.get(i).put("avgResponseTime", timeParser(getAvgResponseTime(agents.get(i).get("agentId").toString())));
			agents.get(i).put("avgResolutionTime",timeParser( getAvgResolutionTime(agents.get(i).get("agentId").toString())));
			agents.get(i).put("avgCustomerRating", avgCustomerRating(agents.get(i).get("agentId").toString()));
		}		
		return agents;
	}
	
	
	
	public List<Map<String, Object>> getTickets(){
		String query = "select * from tickets";
		return jdbcTemplate.queryForList(query);
	}
	
	
	public String timeParser(Long time) {
		Long days, hours, mins, sec;
		String ret = "";
		if(time > 86400) {
			days = time / 86400;
			time %= 86400;
			ret+= days+"d, ";
		}
		if(time > 3600) {
			hours = time/3600;
			time %= 3600;
			ret += hours+"h, ";
		}
		if(time > 60) {
			mins = time / 60;
			time %= 60;
			ret += mins +"m, ";
		}
		ret += time+"s";
		return ret;
	}
	
	
	public Float avgCustomerRating(String agentId) {
		float rating = 0.0f;
		String query1 = "select customer_rating from tickets where agent_id = "+agentId;
		List<Map<String, Object>> result1 = jdbcTemplate.queryForList(query1);
		for(int i=0; i<result1.size(); i++) {
			rating+= Float.parseFloat(result1.get(i).get("customer_rating").toString());
		}
		return (result1.size() > 0)?(rating / result1.size()):-1f;
	}
	
	public Long getAvgResolutionTime(String agentId) {
		Long avgResolutionTime = 0L;
		String query1 = "select id as ticket_id from tickets where agent_id = "+agentId;
		List<Map<String, Object>> ticketList = jdbcTemplate.queryForList(query1);
		for(int i=0; i<ticketList.size(); i++) {
			Long ticketResolutionTime = getAvgResolutionTimeForTicket(ticketList.get(i).get("ticket_id").toString());
			if (ticketResolutionTime > -1)avgResolutionTime += ticketResolutionTime;
		}
		if(ticketList.size() > 0) {
			String query2 = "select count(*) as resolved_tickets_count from responses where ticket_status = 'resolved' and ticket_id in (";
			for(int i=0; i<ticketList.size(); i++) {
				if(i != ticketList.size()-1)	query2 += "'"+ticketList.get(i).get("ticket_id")+"',";
				else query2 += "'"+ticketList.get(i).get("ticket_id").toString()+"')";
			}
			System.out.println(query2);
			List<Map<String, Object>> resolutionCount = jdbcTemplate.queryForList(query2);
			if(resolutionCount.get(0).get("resolved_tickets_count")!= null) {
				if(Integer.parseInt(resolutionCount.get(0).get("resolved_tickets_count").toString()) != 0) {
					avgResolutionTime /= Integer.parseInt(resolutionCount.get(0).get("resolved_tickets_count").toString());
				}
			}
		}
		return avgResolutionTime;
	}
	
	
	public Long getAvgResolutionTimeForTicket(String ticketId) {
		String query1 = "select created_time as ticket_resolved_time from responses where ticket_id = "+ticketId+" and ticket_status = 'resolved' ";
		List<Map<String, Object>> queryResult1 = jdbcTemplate.queryForList(query1);
		
		String query2 = "select created_time as ticket_created_time from tickets where id = "+ticketId;
		List<Map<String, Object>> queryResult2 = jdbcTemplate.queryForList(query2);
		

		if(queryResult1.size()>0 && queryResult2.size()>0) {
			String createdTime = queryResult2.get(0).get("ticket_created_time").toString();
			String resolvedTime = queryResult1.get(0).get("ticket_resolved_time").toString();
			return Duration.between(LocalDateTime.parse(createdTime), LocalDateTime.parse(resolvedTime)).toSeconds();
		}
		else {
			return -1L;
		}
		
		
	}
	
	public Integer getNumOfTickets(String agentId) {
		Integer numOfTickets = Integer.parseInt(jdbcTemplate.queryForList(
				"select count(*) as no_of_tickets, ticket_id  "
				+ "from "
				+ "(select tickets.id as ticket_id,tickets.created_time as ticket_created_time, agents.id as agent_id "
				+ "from agents left join tickets on agents.id = tickets.agent_id) as t1 where agent_id = "
		+agentId).get(0).get("no_of_tickets").toString());
		return numOfTickets;
	}
	
	public long getAvgResponseTime(String agentId) {
		
		long avgRespTime = 0;
		
		List<Map<String, Object>> query1 = jdbcTemplate.queryForList("select id as ticket_id from tickets where agent_id = "+agentId);
		List<String> ticketList = new ArrayList<String>();
		for(int i=0; i<query1.size(); i++) {
			ticketList.add(query1.get(i).get("ticket_id").toString());
		}
		
		for(int i=0; i<ticketList.size(); i++) {
			
			long avgResponseTimeTicket = getAvgResponseTimeForTicket(ticketList.get(i));
			if(avgResponseTimeTicket > -1)	{
				avgRespTime += getAvgResponseTimeForTicket(ticketList.get(i));
				
			}
			
		}
		
		if(ticketList.size() > 0) avgRespTime /= (ticketList.size());
		else avgRespTime = -1;
		return avgRespTime;
	}
	
	public long getAvgResponseTimeForTicket(String ticketId) {
		long avgResponseTime = 0;
		List<Map<String, Object>> query = jdbcTemplate.queryForList("select created_time as response_created_time from responses where ticket_id = "+ticketId+" and response_by_agent = 1");
		for(int i=0; i<query.size()-1; i++) {
			String t1 = query.get(i).get("response_created_time").toString();
			String t2 = query.get(i+1).get("response_created_time").toString();
			
			System.out.println("T1: "+t1);
			System.out.println("T2: "+t2);
			
			avgResponseTime += Duration.between(
					LocalDateTime.parse(t1),
					LocalDateTime.parse(t2)
				).toSeconds();
			
			System.out.println(avgResponseTime);
		}
		if(query.size() > 0) {
			List<Map<String, Object>> query1 = jdbcTemplate.queryForList("select created_time from tickets where id = "+ticketId);
			String ticketCreatedTime = query1.get(0).get("created_time").toString();
			String firstResponseTime = query.get(0).get("response_created_time").toString();
			avgResponseTime +=  Duration.between(
							LocalDateTime.parse(ticketCreatedTime),
							LocalDateTime.parse(firstResponseTime)
						).toSeconds();

			avgResponseTime /= (query.size());
		}
		else avgResponseTime = -1;
		return avgResponseTime;
	}
	
	
	
	
	public String createAgent(Agent agent) {
		jdbcTemplate.execute("insert into agents(name, password, rating) values ('"+agent.getAgentName()+"', "+"'password'"+", 0.0)");
		return "Successfully added agent.";
	}
	
	public void addResponse(Response comment) {
		jdbcTemplate.execute("insert into responses(message, created_time, ticket_id) values "
				+ "('"+comment.getMessage()+"', '"+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))+"', "+comment.getTicketId()+")");
	}
	
	public void editResponse() {
		
	}
	
	
	public List<Ticket> getTickets(Integer ticketId){
		String query = "select * from tickets where ticket_id=?";
		return jdbcTemplate.query(
				query,
				new Object[] {ticketId}, 
				(rs, rowNum) -> {
					Ticket t = new Ticket();
					t.setId(rs.getInt("id"));
					t.setTitle(rs.getString("title"));
					t.setDescription(rs.getString("description"));
					t.setStatus(rs.getString("status"));
					t.setCreatedTime(rs.getTimestamp("created_time").toLocalDateTime());
					t.setPriority(rs.getInt("priority"));					
					return t;
				}
		);
	}
	
	public List<Response> getResponses(Integer ticketId){
		String query = "select * from responses where ticket_id = ? order by created_time";
		return jdbcTemplate.query(
				query,
				new Object[] {ticketId},
				(rs, rowNum) -> {
					Response r = new Response();
					r.setId(rs.getInt("id"));
					r.setMessage(rs.getString("message"));
					r.setCreatedTime(rs.getTimestamp("created_time").toLocalDateTime());
					r.setTicketId(ticketId);
					return r;
				}
		);
	}
	
	
}
