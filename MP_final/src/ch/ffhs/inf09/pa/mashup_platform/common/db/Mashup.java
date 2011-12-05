package ch.ffhs.inf09.pa.mashup_platform.common.db;

import java.util.*;
import java.io.*;
import javax.persistence.Id;
import javax.persistence.Version;

public class Mashup implements Serializable
{
	private static final long serialVersionUID = 1L;
	public static final int STATUS_PENDING = 1;
	public static final int STATUS_ACTIVE = 2;
	public static final int STATUS_INACTIVE = 3;
	public static final int STATUS_REJECTED = 4;
	
	@Id   
	private Object id;
	
	@Version   
	private Object version;
	
	private String ident;
	private String name;
	private String username;
	private Date lastUpdated;
	private Date createdAt;
	private int status;
	
	// the requested page
	private MashupPage page;

	public void setIdent(String ident) {
		this.ident = ident;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setStatus(int status)
	{
		this.status = status;
	}
	
	public int getStatus()
	{
		return status;
	}
	
	public void setPage(MashupPage page)
	{
		this.page = page;
	}
	
	public MashupPage getPage()
	{
		return page;
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
	
	public String getIdent() { return ident; }
	public String getName() { return name; }
	public String getUsername() { return username; }
	public Date getLastUpdated() { return lastUpdated; }
	public Date getCreatedAt() { return createdAt; }
}