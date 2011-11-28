package ch.ffhs.inf09.pa.mashup_platform.common.db;

import java.util.*;

public class User
{
	private String username;
	private String password;
	private Date lastLogin;
	
	public User(String username)
	{
		this.username = username;
	}
	
	public void setLastLogin(Date lastLogin)
	{
		this.lastLogin = lastLogin;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public Date getLastLogin() { return lastLogin; }
	public String getUsername() { return username; }
	public String getPassword() { return password; }
}