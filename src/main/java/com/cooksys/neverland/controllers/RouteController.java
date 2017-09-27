package com.cooksys.neverland.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.neverland.core.models.ConnectingFlights;
import com.cooksys.neverland.core.models.FlightModel;
import com.cooksys.neverland.daos.RouteDao;
import com.cooksys.neverland.models.Route;

@RestController
@RequestMapping(value = "/routes")
public class RouteController
{
	@Autowired
	private RouteDao routeDao;
	
	@RequestMapping(value = "/find/{loc_start_id}/{loc_end_id}", method = RequestMethod.GET)
	public List<ConnectingFlights> getPossibleRoutes(@PathVariable int loc_start_id, @PathVariable int loc_end_id) {
		return routeDao.getPossibleRoutes(loc_start_id, loc_end_id);
	}
	
	@RequestMapping(value = "/allFlights", method = RequestMethod.GET)
	public FlightModel getAllFlights() {
		return routeDao.getAllFlights();
	}
	
	@RequestMapping(value = "/book/{userId}", method = RequestMethod.POST)
	public Route bookTrip(@PathVariable int userId, @RequestBody ConnectingFlights cf) {
		return routeDao.bookTrip(userId, cf);
	}
}
