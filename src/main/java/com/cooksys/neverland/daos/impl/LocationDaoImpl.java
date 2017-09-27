package com.cooksys.neverland.daos.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cooksys.neverland.models.Location;
import com.cooksys.neverland.daos.LocationDao;

@Repository
@Transactional
public class LocationDaoImpl implements LocationDao
{
	//private static Gson gson = new Gson();
	
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Location> getAllLocations()
	{
		//String locations = com.cooksys.neverland.core.controllers.CoreModelsController.getLocations();
		Session session = sessionFactory.getCurrentSession();
		return session
				.createQuery("from Location")
				.list();
	}

	@Override
	public Location addLocation(Location location)
	{
		Session session = sessionFactory.getCurrentSession();
		Serializable id = session.save(location);
		return (Location) session.get(Location.class, id);
	}

	@Override
	public Location getLocation(int location_id)
	{
		Session session = sessionFactory.getCurrentSession();
		return (Location) session.createQuery("select l from Location as l where l.locationId = :id")
				.setInteger("id", location_id)
				.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Location> getLocationsByState(String state)
	{
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("select l from Location as l where l.state = :state")
				.setString(state, state)
				.list();
	}
	
	@Override
	public Location getLocationByCity(String city) {
		Session session = sessionFactory.getCurrentSession();
		return (Location) session.createQuery("select l from Location as l where l.city = :city")
								.setString("city", city)
								.uniqueResult();
	}
	
}
