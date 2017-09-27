package com.cooksys.neverland.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.neverland.daos.UserDao;
import com.cooksys.neverland.models.Flight;
import com.cooksys.neverland.models.Route;
import com.cooksys.neverland.models.User;

@RestController
@RequestMapping(value = "/user")
public class UserController
{
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(method = RequestMethod.POST)
	public User createUser(@RequestBody User user) {
		return userDao.createUser(user);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public User getUser(@RequestBody String username) {
		return userDao.getUser(username);
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public User getUserById(@PathVariable int userId) {
		return userDao.getUserById(userId);
	}
	
	@RequestMapping(value = "/{userId}/currentTrips/cancel", method = RequestMethod.PATCH)
	public void cancelTickets(@PathVariable int userId, @RequestBody Route route) {
		userDao.cancelTickets(userId, route);
	}
	
	@RequestMapping(value = "/{userId}/currentTrips", method = RequestMethod.GET)
	public List<Route> getCurrentTrips(@PathVariable int userId) {
		return userDao.getCurrentTrips(userId);
	}
}