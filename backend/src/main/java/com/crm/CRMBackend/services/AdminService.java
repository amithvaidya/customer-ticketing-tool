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
import java.util.stream.Collectors;
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

import com.crm.CRMBackend.dao.TicketDAO;
import com.crm.CRMBackend.dao.AgentDAO;
import com.crm.CRMBackend.dao.ResponseDAO;


@Service
public class AdminService {
	
	@Autowired private UtilServices utils;
	@Autowired private ResponseDAO responseDAO;
	@Autowired private AgentDAO agentDAO;
	@Autowired private TicketDAO ticketDAO;
	
	//TODO: Define DAO methods for the following 4 methods
	public int addResponse(Response response) {
		return responseDAO.createResponse(response);
	}
	
	public int editResponse(Response response) {
		return responseDAO.updateResponse(response);
	}
	
	public List<Response> getAllResponsesForTicket(int ticketId){
		return responseDAO.getAllResponsesForTicket(ticketId);
	}
	
	public int createAgent(Agent agent) {
		return agentDAO.createAgent(agent);
	}
	
	public List<Ticket> getAllTickets(){
		return ticketDAO.getAllTickets();
	}
	
	public List<Agent> getAgentAnalytics(){
		return agentDAO.getAllAgents()
						.stream()
						//TODO: check the methods used in the map section
						.map(agent -> {
							agent.setAvgCustomerRating(getAvgCustomerRating(agent.getId()));
							agent.setAvgResponseTime(utils.timeParser(getAvgResponseTime(agent.getId())));
							agent.setAvgResolutionTime(utils.timeParser(getAvgResolutionTime(agent.getId())));
							agent.setNumTicketsAssigned(ticketDAO.numOfTicketsForAgent(agent.getId()));
							agent.setNumTicketsResolved(ticketDAO.numOfTicketsResolvedByAgent(agent.getId()));
							return agent;
						})
						.collect(Collectors.toList());
	}
	

	public Float getAvgCustomerRating(int agentId) {
		float rating = 0.0f;
		List<Float> ratings = ticketDAO.getCSATForAgent(agentId).stream().filter(tempRating -> tempRating > -1.0f)
								.collect(Collectors.toList());
		int totalCount = ratings.size();
		float totalRating = 0.0f;
		
		for(Float rate: ratings){
			totalRating += rate;
		}
		
		rating = (totalCount>0)?(totalRating/totalCount):(-1.0f);
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
		List<Integer> ticketIds = ticketDAO.getAllTicketsHandledByAgent(agentId, "resolved");
		
		long numOfTickets = ticketIds.size();
		long totalTime = 0L; /*ticketIds.stream()
			.map((ticketId) -> Math.abs(Duration.between(
								//Ticket Created time
								ticketDAO.getTicketCreatedTimestamp(ticketId),
								
								//Ticket resolved time
								responseDAO.getTicketResolvedTimestamp(ticketId))
					.getSeconds()))
			.reduce((t1, t2) -> t1+t2).get();
			*/
		for(int ticketId: ticketIds){
			totalTime += 
				Duration.between(
					ticketDAO.getTicketCreatedTimestamp(ticketId),
					responseDAO.getTicketResolvedTimestamp(ticketId)
					).getSeconds(); 
		}
		
		if (numOfTickets>0)avgResolutionTime = totalTime / numOfTickets;
		else avgResolutionTime = -1L;
		
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
		
		long avgRespTime = 0L;
		long duration = 0L;
		List<Ticket> tickets = ticketDAO.getAllTicketsForAgent(agentId);
		long count = tickets.size();
		
		// For each ticket, find the avg response time
		
		//TODO: Change this iteration using stream API
		for(Ticket ticket: tickets) {
			duration+=avgResponseTimeForTicket(ticket.getId());
		 }
		 
		 
//		duration = tickets.stream().map(ticket -> avgResponseTimeForTicket(ticket.getId())).reduce((t1, t2) -> t1+t2).get();
		avgRespTime =  (count > 0)?(duration / count):-1L;
		return avgRespTime;
	}
	
	
	//TODO: Test this method with different scenarios of response timestamps
	public long avgResponseTimeForTicket(int ticketId) {
		
		List<LocalDateTime> agentResponseTS = responseDAO.getTimestampsOfResponses(ticketId, true, true);
		List<LocalDateTime> customerResponseTS = responseDAO.getTimestampsOfResponses(ticketId, false, true);

		int custRespCount = customerResponseTS.size();
		int agentRespCount = agentResponseTS.size();
		long duration=0;
		int jumpToIndex=0;
		
		//This can be implemented using Stream API
		for(int i=0; i<custRespCount; i++) {
			
			LocalDateTime custResp = customerResponseTS.get(i);
			
			loop: for(int j=jumpToIndex; j<agentRespCount; j++) {
				LocalDateTime agentResp = agentResponseTS.get(j);
				if(agentResp.isAfter(custResp) || agentResp.isEqual(custResp)) {
					duration+=(Duration.between(custResp, agentResp).getSeconds());
					jumpToIndex=j+1;
					break loop;
				}
			}
		}
		
		return (custRespCount>0)?( duration / custRespCount):-1L;
		
	}	
		
	
}
