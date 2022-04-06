package com.ujjwal.repository;

import java.util.List;

import com.ujjwal.model.Ride;

public interface RideRepository {

	Ride createRide(Ride ride);

	List<Ride> getRides();

}