package com.cooksys.neverland.daos;

import java.util.List;

import com.cooksys.neverland.models.Flight;
import com.cooksys.neverland.models.Route;
import com.cooksys.neverland.models.User;

public interface UserDao
{
	User createUser(User user);

	User getUser(String username);
	
	User getUserById(int userId);

	void cancelTickets(int userId, Route route);

	List<Route> getCurrentTrips(int userId);

	

}
