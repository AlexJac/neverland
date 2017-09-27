package com.cooksys.neverland.daos.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cooksys.neverland.daos.UserDao;
import com.cooksys.neverland.models.Flight;
import com.cooksys.neverland.models.Route;
import com.cooksys.neverland.models.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao
{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public User createUser(User user)
	{
		Session session = sessionFactory.getCurrentSession();
		Serializable id = session.save(user);
		
		return (User) session.get(User.class, id);	
	}

	@Override
	public User getUserById(int userId)
	{
		Session session = sessionFactory.getCurrentSession();
		return (User) session.createQuery("select u from User as u where u.userId = :id")
							.setInteger("id", userId)
							.uniqueResult();
	}
	
	@Override
	public User getUser(String username)
	{
		Session session = sessionFactory.getCurrentSession();
		return (User) session
				.createQuery("select u from User as u where u.username = :un")
				.setString("un", username)
				.uniqueResult();
	}

	@Override
	public void cancelTickets(int userId, Route route)
	{
		Session session = sessionFactory.getCurrentSession();
		route.setCanceled(true);
		session.update(route);
		List<Flight> routeFlights = (List<Flight>) route.getFlights();
		for(Flight flight: routeFlights){
			int i = flight.getCapacity() + 1;
			flight.setCapacity(i);
			session.update(flight);
		}
	}

	@Override
	public List<Route> getCurrentTrips(int userId)
	{
		Session session = sessionFactory.getCurrentSession();
		List<Route> currentTrips = new ArrayList<Route>();
		Iterator<Route> itflit = ((User) session.createQuery("select u from User as u where u.userId = :id")
					.setInteger("id", userId)
					.uniqueResult()).getRoutes().iterator();
		while(itflit.hasNext()){
			Route route = itflit.next();
			if(!route.isCanceled() && !route.isCompleted()){
				currentTrips.add(route);
			}
		}
		return currentTrips;
	}

}
