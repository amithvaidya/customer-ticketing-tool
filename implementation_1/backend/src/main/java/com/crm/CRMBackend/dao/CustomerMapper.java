package com.crm.CRMBackend.dao;

import java.sql.ResultSet;

import org.springframework.jdbc.core.RowMapper;

import com.crm.CRMBackend.models.Customer;

public class CustomerMapper implements RowMapper<Customer>{
	
	@Override
	public Customer mapRow(ResultSet rs, int rowNum) {
		
		return null;
	}
	
}
