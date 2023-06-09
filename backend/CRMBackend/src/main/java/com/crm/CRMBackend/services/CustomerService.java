package com.crm.CRMBackend.services;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Service;

import com.crm.CRMBackend.models.Ticket;

@Service
public class CustomerService {
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Ticket> getTickets(Integer customerId){
		String query = "select * from tickets where customer_id=?";
		return jdbcTemplate.query(
				query,
				new Object[] {customerId}, 
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
	
	public Boolean createTicket(Ticket t){
		String query = "insert into tickets(title, description, created_time, customer_id, priority, status) values (?,?,?,?,?,?)";
		return jdbcTemplate.execute(query, new PreparedStatementCallback<Boolean>() {
			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException{
				ps.setString(1, t.getTitle());
				ps.setString(2, t.getDescription());
				ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
				ps.setInt(4, t.getCustomerId());
				ps.setInt(5, t.getPriority());
				ps.setString(6, "open");
				return ps.execute();
			}
		});
	}
	
	
	public Boolean addRating(Integer ticketId, Integer rating) {
		String query = "update tickets set customer_rating = ? where id = ?";
		return jdbcTemplate.execute(query, new PreparedStatementCallback<Boolean>() {
			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException{
				ps.setInt(1, rating);
				ps.setInt(2, ticketId);
				return ps.execute();
			}
		});
	}
}
