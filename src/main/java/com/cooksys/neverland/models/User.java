package com.cooksys.neverland.models;

// Generated May 17, 2016 5:58:09 AM by Hibernate Tools 4.3.1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name = "user", catalog = "neverland", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User implements java.io.Serializable
{

	private Integer userId;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	@JsonIgnore
	private Set<Route> routes = new HashSet<Route>(0);

	public User()
	{
	}

	public User(String username, String password, String firstName,
			String lastName)
	{
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public User(String username, String password, String firstName,
			String lastName, Set<Route> routes)
	{
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.routes = routes;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	public Integer getUserId()
	{
		return this.userId;
	}

	public void setUserId(Integer userId)
	{
		this.userId = userId;
	}

	@Column(name = "username", unique = true, nullable = false, length = 45)
	public String getUsername()
	{
		return this.username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	@Column(name = "password", nullable = false, length = 60)
	public String getPassword()
	{
		return this.password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	@Column(name = "first_name", nullable = false, length = 45)
	public String getFirstName()
	{
		return this.firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	@Column(name = "last_name", nullable = false, length = 45)
	public String getLastName()
	{
		return this.lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Route> getRoutes()
	{
		return this.routes;
	}

	public void setRoutes(Set<Route> routes)
	{
		this.routes = routes;
	}

}
