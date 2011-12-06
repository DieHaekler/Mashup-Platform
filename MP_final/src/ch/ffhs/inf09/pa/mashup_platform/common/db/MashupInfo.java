package ch.ffhs.inf09.pa.mashup_platform.common.db;

import java.util.*;

public class MashupInfo
{
	private String mashupIdent;
	private String name;
	private String username;
	private Date lastUpdated;
	private Date createdAt;
	private int numberPages;
	
	public MashupInfo(String mashupIdent)
	{
		this.mashupIdent = mashupIdent;
	}
	
	public String getMashupIdent() { return mashupIdent; }
	public String getName() { return name; }
	public String getUsername() { return username; }
	public Date getLastUpdated() { return lastUpdated; }
	public Date getCreatedAt() { return createdAt; }
	public int getNumberPages() { return numberPages; }
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public void setLastUpdated(Date lastUpdated)
	{
		this.lastUpdated = lastUpdated;
	}
	
	public void setCreatedAt(Date createdAt)
	{
		this.createdAt = createdAt;
	}
	
	public void setNumberPages(int numberPages)
	{
		this.numberPages = numberPages;
	}
}