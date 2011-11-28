package ch.ffhs.inf09.pa.mashup_platform.common.db;

import ch.ffhs.inf09.pa.mashup_platform.common.util.ExceptionMP;

public class DBOrient extends DBLocal
{
	public DBOrient(String dbUsername, String dbPassword) throws ExceptionMP
	{
		super(dbUsername, dbPassword);
	}
	
	public void connect() throws ExceptionMP
	{
		// todo
	}
	
	public void close()
	{
		// todo
	}
	
	public Mashups getMashups(int start, int number, int sortedBy,
			int status, String username)
	{
		// todo
		return null;
	}
	
	public Mashup getMashup(String ident, int start, int number)
	{
		// todo
		return null;
	}
	
	public void setMashup(Mashup mashup)
	{
		// todo
	}
	
	public User getUser(String username, String password)
	{
		// todo
		return null;
	}
	
	public void setUser(User user)
	{
		// todo
	}
}