package ch.ffhs.inf09.pa.mashup_platform.common.db;

import ch.ffhs.inf09.pa.mashup_platform.common.util.*;

public abstract class DBLocal
{
	protected String dbUsername;
	protected String dbPassword;
	
	public DBLocal(String dbUsername, String dbPassword) throws ExceptionMP
	{
		this.dbUsername = dbUsername;
		this.dbPassword = dbPassword;
		connect();
	}
	
	protected abstract void connect() throws ExceptionMP;
	public abstract void close();
	public abstract Mashups getMashups(int start, int number, int sortedBy,
			int status, String username);
	public abstract Mashup getMashup(String ident, int pagenr);
	public abstract void setMashup(Mashup mashup);
	public abstract User getUser(String username, String password);
	public abstract void setUser(User user);
	
	public Mashups getMashups(int start, int number, int sortedBy, int status)
	{
		return getMashups(start, number, sortedBy, status, null);
	}
	
	public Mashups getMashups(int start, int number, int sortedBy)
	{
		return getMashups(start, number, sortedBy, Mashups.STATUS_ACTIVE);
	}
	
	public Mashups getMashups(int start, int number)
	{
		return getMashups(start, number, Mashups.SORTED_BY_NAME_ASC);
	}
}