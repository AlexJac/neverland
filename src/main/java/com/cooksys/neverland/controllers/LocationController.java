package com.cooksys.neverland.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.neverland.daos.LocationDao;
import com.cooksys.neverland.models.Location;

@RestController
@RequestMapping(value = "/locations")
public class LocationController
{
	@Autowired
	private LocationDao locationDao;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Location> getAllLocations(){
		return locationDao.getAllLocations();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Location addLocation(@RequestBody Location location) {
		return locationDao.addLocation(location);
	}
	
	@RequestMapping(value = "/{location_id}", method = RequestMethod.GET)
	public Location getLocation(@PathVariable int location_id) {
		return locationDao.getLocation(location_id);
	}
	
	@RequestMapping(value = "/{state}", method = RequestMethod.GET)
	public List<Location> getLocationsByState(@PathVariable String state) {
		return locationDao.getLocationsByState(state);
	}
	
	@RequestMapping(value = "/{city}", method = RequestMethod.GET)
	public Location getLocationByCity(@PathVariable String city) {
		return locationDao.getLocationByCity(city);
	}
}
