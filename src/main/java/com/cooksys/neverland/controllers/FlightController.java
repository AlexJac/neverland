package com.cooksys.neverland.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.neverland.daos.FlightDao;
import com.cooksys.neverland.models.Flight;

@RestController
@RequestMapping(value = "/flights")
public class FlightController
{
	@Autowired
	private FlightDao flightDao;

	@RequestMapping(value = "/{flight_id}", method = RequestMethod.GET)
	public Flight getFlightById(@PathVariable int flight_id) {
		return flightDao.getFlightById(flight_id);
	}
}
