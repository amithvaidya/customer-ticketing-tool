package com.crm.CRMBackend.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.crm.CRMBackend.dao.GeneralDAO;
import com.crm.CRMBackend.mappers.AgentMapper;
import com.crm.CRMBackend.mappers.TicketMapper;
import com.crm.CRMBackend.models.Agent;
import com.crm.CRMBackend.models.Response;
import com.crm.CRMBackend.models.Ticket;
import com.crm.CRMBackend.util.UtilServices;

@Service
public class AdminService {
	
	@Autowired
	private GeneralDAO dao;
	
//	@Autowired
//	private UtilServices util;
//		
	@Autowired
	private UtilServices utils;
	
	
	public int addResponse(Response response) {
		return 0;
	}
	
	public int editResponse(Response response) {
		return 0;
	}
	
	public List<Response> getAllResponsesForTicket(int ticketId){
		return dao.getAllResponsesForTicket(ticketId);
	}
	
	public int createAgent(Agent agent) {
		return 0;
	}
	
	public List<Ticket> getAllTickets(){
		return dao.getAllTickets();
	}
	
	public List<Agent> getAgentAnalytics(){
		return dao.getAllAgents()
						.stream()
						.map(agent -> {
							agent.setAvgCustomerRating(getAvgCustomerRating(agent.getAgentId()));
							agent.setAvgResponseTime(utils.timeParser(getAvgResponseTime(agent.getAgentId())));
							agent.setAvgResolutionTime(utils.timeParser(getAvgResolutionTime(agent.getAgentId())));
							agent.setNumTicketsAssigned(dao.numOfTicketsForAgent(agent.getAgentId()));
							return agent;
						})
						.toList();
	}
	
	
	public Float getAvgCustomerRating(int agentId) {
		float rating = 0.0f;
		List<Float> ratings = dao.getCSATForAgent(agentId);
		int totalCount = ratings.size();
		float totalRating = ratings.stream().reduce((prevRating, nextRating) -> (prevRating+nextRating)).get();
		rating = totalRating/totalCount;
		
		return rating;
	}
	
	
	/*
	 * Computes the average resolution time for an agent.
	 * 
	 * Average resolution time for an agent: ART
	 * Resolution time for a ticket (#1): RT_1
	 * ART = avg(RT_1, RT_2, RT_3.. all tickets handled by agent)
	 * */
	
	public long getAvgResolutionTime(int agentId) {
		long avgResolutionTime=0;		
		List<Integer> ticketIds = dao.getAllTicketsHandledByAgent(agentId, "resolved");
		
		long numOfTickets = ticketIds.size();
		long totalTime = ticketIds.stream()
			.map((ticketId) -> Duration.between(
								//Ticket Created time
								dao.getTicketResolvedTImestamp(ticketId),
								
								//Ticket resolved time
								dao.getTicketResolvedTImestamp(ticketId))
					.getSeconds())
			.reduce((t1, t2) -> t1+t2).get();
		
		avgResolutionTime = totalTime / numOfTickets;
		
		return avgResolutionTime;
	}
	
	
	/*
	 * Computes the average response time for an agent.
	 * 
	 * Average response time: ART = avg(ART_T1, ART_T2, ART_T3.. so on)
	 * 
	 * ART_T1 = avg(time difference between customer response and agent response)
	 * */
	
	public long getAvgResponseTime(int agentId) {
		
		long avgRespTime = 0;
		long duration = 0;
		List<Ticket> tickets = dao.getAllTicketsForAgent(agentId);
		long count = tickets.size();
		
		// For each ticket, find the avg response time
		 for(Ticket ticket: tickets) {
			duration+=avgResponseTimeForTicket(ticket.getId());
		 }
		
		
		avgRespTime = duration / count;
		return avgRespTime;
	}
	
	
	public long avgResponseTimeForTicket(int ticketId) {
		
		List<LocalDateTime> agentResponseTS = dao.getTimestampsOfResponses(ticketId, true, true);
		List<LocalDateTime> customerResponseTS = dao.getTimestampsOfResponses(ticketId, false, true);
		int count = customerResponseTS.size();
		long duration=0;
		int jumpToIndex=0;
		
		for(int i=0; i<customerResponseTS.size(); i++) {
			LocalDateTime custResp = customerResponseTS.get(i);
			loop: for(int j=jumpToIndex; j<agentResponseTS.size(); j++) {
				LocalDateTime agentResp = agentResponseTS.get(j);
				if(agentResp.isAfter(custResp) || agentResp.isEqual(custResp)) {
					duration+=(Duration.between(custResp, agentResp).getSeconds());
					jumpToIndex=j+1;
					break loop;
				}
			}
		}
		
		if(customerResponseTS.size()>0) return ( duration / count);
		else return -1;
	}	
		
	
}
