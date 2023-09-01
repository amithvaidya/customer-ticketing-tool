package com.crm.CRMBackend.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.crm.CRMBackend.models.Customer;

public class CustomerMapper implements RowMapper<Customer>{
	
	@Override
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Customer customer = new Customer();
		customer.setId(rs.getInt("id"));
		customer.setName(rs.getString("customer_name"));
		customer.setCompanyName(rs.getString("company_name"));
		customer.setEmailId(rs.getString("email_id"));
		customer.setPhone(rs.getString("phone"));
		return customer;
	}
	
}
