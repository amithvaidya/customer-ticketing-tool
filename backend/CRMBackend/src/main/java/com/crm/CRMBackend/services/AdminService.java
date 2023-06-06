package com.crm.CRMBackend.services;

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

import com.crm.CRMBackend.dao.AgentMapper;
import com.crm.CRMBackend.dao.TicketMapper;
import com.crm.CRMBackend.models.Agent;
import com.crm.CRMBackend.models.Comment;
import com.crm.CRMBackend.models.Ticket;

@Service
public class AdminService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	
	
//	List<Map<String, Object>> agents = jdbcTemplate.queryForList(
//			"SELECT id, name, rating FROM AGENTS"
//			"select * from "
//			+ "(select "
//			+ "	agent_id,"
//			+ "	t1.ticket_id as ticket_id,"
//			+ "	ticket_created_time,"
//			+ "	created_time as response_created_time"
//			+ "    from "
//			+ "	(select agents.id as agent_id, "
//			+ "			tickets.id as ticket_id,"
//			+ "            tickets.created_time as ticket_created_time"
//			+ "	from agents left outer join tickets on agents.id = tickets.agent_id) as t1"
//			+ " left join "
//			+ "	responses "
//			+ "on t1.ticket_id = responses.ticket_id) as t2"
//	);
	
	public List<Map<String, Object>> getAgents(){
		List<Map<String, Object>> agents = jdbcTemplate.queryForList("select id as agentId, name as agentName from agents");
		for(int i=0; i<agents.size(); i++) {
			System.out.println("Agent: "+agents.get(i).get("agentId").toString());
			agents.get(i).put("no_of_tickets", getNumOfTickets(agents.get(i).get("agentId").toString()));
			agents.get(i).put("avgResponseTime", getAvgResponseTime(agents.get(i).get("agentId").toString()));
//			agents.get(i).put("avgResolutionTime", getAvgResolutionTime());
		}		
		return agents;
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
		
		avgRespTime /= (ticketList.size());
		return avgRespTime;
	}
	
	public long getAvgResponseTimeForTicket(String ticketId) {
		
		long avgResponseTime = 0;
		
		//Obtain the response created time
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
	
	
	
	
	public List<Ticket> getTickets(String ticketId, String agentId, String title){
		String query = "select * from tickets";
//		if(ticketId!=null || agentId!=null || title!=null) query+=" where ";
//		
//		List<String> paramNameList = List.of("ticektId", "agentId", "title");
//		List<String> paramValueList = List.of(ticketId, agentId, title);
//		int paramCount=0;
//		for(int i=0; i<paramValueList.size(); i++) {
//			if(paramCount == 0 && paramValueList.get(i)!=null) {
//				query+=paramNameList.get(i)+"="+paramValueList.get(i);
//				paramCount++;
//			}
//			else if(paramCount>0 && paramValueList.get(i)!=null) {
//				query+=" and "+paramNameList.get(i)+"="+paramValueList.get(i);
//				paramCount++;
//			}
//			else {
//				//Do nothing
//			}
//		}
		List<Ticket> tickets = jdbcTemplate.query(query, new TicketMapper());
		return tickets;
	}
	
	public String createAgent(Agent agent) {
//		jdbcTemplate.execute("insert into agents(name, password, rating) values ('"+agent.getName()+"', "+"'password'"+", 0.0)");
		return "Successfully added agent.";
	}
	
	public void addComment(Comment comment) {
		jdbcTemplate.execute("insert into responses(message, created_time, ticket_id) values "
				+ "('"+comment.getMessage()+"', '"+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))+"', "+comment.getTicketId()+")");
	}
}
