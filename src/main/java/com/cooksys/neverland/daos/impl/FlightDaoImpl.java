package com.cooksys.neverland.daos.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cooksys.neverland.daos.FlightDao;
import com.cooksys.neverland.models.Flight;

@Repository
@Transactional
public class FlightDaoImpl implements FlightDao
{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Flight getFlightById(int flight_id)
	{
		Session session = sessionFactory.getCurrentSession();
		return (Flight) session.createQuery("select f from Flight as f where f.flightId = :id")
								.setInteger("id", flight_id)
								.uniqueResult();
	}
	
}
