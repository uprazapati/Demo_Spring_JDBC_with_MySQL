package com.ujjwal.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.ujjwal.repository.util.RideRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.ujjwal.model.Ride;

import javax.sql.RowSet;

@Repository("rideRepository")
public class RideRepositoryImpl implements RideRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Ride> getRides() {
		List <Ride> rides = jdbcTemplate.query("select * from ride", new RideRowMapper());
		return rides;
	}

	@Override
	public Ride createRide(Ride ride) {

		//Simply using query
		/*jdbcTemplate.update("insert into ride (name, duration) values (?,?)",
				ride.getName(),
				ride.getDuration());*/

		//Other way without using query
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);

		List<String> columns = new ArrayList<>();
		columns.add("name");
		columns.add("duration");

		insert.setTableName("ride");
		insert.setColumnNames(columns);

		Map<String, Object> data = new HashMap<>();
		data.put("name", ride.getName());
		data.put("duration", ride.getDuration());

		insert.setGeneratedKeyName("id");

		Number id = insert.executeAndReturnKey(data);
		System.out.println("Returned Key:	" +  id);

		// Inserting using KeyHolder
		/*KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement("insert into ride (name, duration) values (?,?)",
						new String[] {"id"});
				ps.setString(1, ride.getName());
				ps.setInt(2, ride.getDuration());
				return ps;
			}
		}, keyHolder);

		Number id = keyHolder.getKey();*/
		return getRide(id.intValue());
	}

	@Override
	public Ride getRide(Integer id) {
		Ride ride = jdbcTemplate.queryForObject("select * from ride where id = ?", new RideRowMapper(), id);
		return ride;
	}

	@Override
	public Ride updateRide(Ride ride) {
		jdbcTemplate.update("update ride set name = ?, duration = ? where id = ?",
				ride.getName(),
				ride.getDuration(),
				ride.getId());

		return ride;
	}

	@Override
	public void updateRides(List<Object[]> pairs) {
		jdbcTemplate.batchUpdate("update ride set ride_date = ? where id = ?", pairs);
	}

	@Override
	public void deleteRide(Integer id) {

		//Simple using JDBC Template
		/*jdbcTemplate.update("delete from ride where id = ?", id);*/

		//Using NamedParam
		NamedParameterJdbcTemplate namedParameterJdbcTemplate =
				new NamedParameterJdbcTemplate(jdbcTemplate);

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", id);
		namedParameterJdbcTemplate.update("delete from ride where id = :id", paramMap);
	}
}
