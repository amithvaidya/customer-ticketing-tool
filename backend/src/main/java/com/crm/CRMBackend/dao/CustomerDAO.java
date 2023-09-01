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

@Component
public class CustomerDAO {
	
	@Autowired private JdbcTemplate jdbcTemplate;
	
	public List<Customer> getAllCustomers(){
		return jdbcTemplate.query("select * from customers", new CustomerMapper());
	}
	
	public int createCustomer(Customer customer){
		return jdbcTemplate.update("insert into customers (name, company_name, email_id, phone) values(?,?,?,?) ",
				customer.getName(),
				customer.getCompanyName(),
				customer.getEmailId(),
				customer.getPhone()
		);
	}
	
}