package com.ujjwal.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.ujjwal.model.Ride;

@Repository("rideRepository")
public class RideRepositoryImpl implements RideRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Ride> getRides() {
		Ride ride = new Ride();
		ride.setName("Corner Canyon");
		ride.setDuration(120);
		List <Ride> rides = new ArrayList<>();
		rides.add(ride);
		return rides;
	}

	@Override
	public Ride createRide(Ride ride) {

		//Simply using query
		jdbcTemplate.update("insert into ride (name, duration) values (?,?)",
				ride.getName(),
				ride.getDuration());

		//Other way without using query
//		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
//
//		List<String> columns = new ArrayList<>();
//		columns.add("name");
//		columns.add("duration");
//
//		insert.setTableName("ride");
//		insert.setColumnNames(columns);
//
//		Map<String, Object> data = new HashMap<>();
//		data.put("name", ride.getName());
//		data.put("duration", ride.getDuration());
//
//		insert.setGeneratedKeyName("id");
//
//		Number key = insert.executeAndReturnKey(data);
//		System.out.println("Returned Key:	" +  key);
		return null;
	}
}
