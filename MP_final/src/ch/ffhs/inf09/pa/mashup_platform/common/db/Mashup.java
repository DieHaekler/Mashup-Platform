package ch.ffhs.inf09.pa.mashup_platform.common.db;

import java.util.*;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.*;

public class Mashup
{
	private String ident;
	private String name;
	private String username;
	private Date lastUpdated;
	private Date createdAt;
	private Content content;
	private int totalRecords;
	private int start;
	private int number;
	
	public Mashup(String ident, String name)
	{
		this.ident = ident;
		this.name = name;
	}
	
	public void setTotalRecords(int totalRecords)
	{
		this.totalRecords = totalRecords;
	}
	
	public void setStart(int start)
	{
		this.start = start;
	}
	
	public void setNumber(int number)
	{
		this.number = number;
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
	
	public void setContent(Content content)
	{
		this.content = content;
	}
	
	public int getTotalRecord() { return totalRecords; }
	public int getStart() { return start; }
	public int getNumber() { return number; }
	public String getIdent() { return ident; }
	public String getName() { return name; }
	public String getUsername() { return username; }
	public Date getLastUpdated() { return lastUpdated; }
	public Date getCreatedAt() { return createdAt; }
	public Content getContent() { return content; }
}