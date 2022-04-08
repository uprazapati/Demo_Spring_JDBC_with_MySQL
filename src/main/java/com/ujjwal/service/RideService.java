package com.ujjwal.service;

import java.util.List;

import com.ujjwal.model.Ride;

public interface RideService {

	Ride createRide(Ride ride);

	List<Ride> getRides();

	Ride getRide(Integer id);

	Ride updateRide(Ride ride);

	void batch();

	void deleteRide(Integer id);
}