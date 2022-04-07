package com.ujjwal.controller;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.ujjwal.model.Ride;

import org.junit.Test;

public class RestControllerTest {

	@Test(timeout=10000)
	public void testCreateRides() {
		RestTemplate restTemplate = new RestTemplate();

		Ride ride = new Ride();
		ride.setName("UMBC Trail Ride");
		ride.setDuration(23);

		ride = restTemplate.postForObject("http://localhost:8080/ride_tracker/ride",
				ride,
				Ride.class);

		System.out.println("Ride:	" + ride);
	}

	@Test(timeout=3000)
	public void testGetRides() {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<List<Ride>> ridesResponse = restTemplate.exchange(
				"http://localhost:8080/ride_tracker/rides", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Ride>>() {
				});
		List<Ride> rides = ridesResponse.getBody();

		for (Ride ride : rides) {
			System.out.println("Ride name: " + ride.getName());
		}
	}
}
