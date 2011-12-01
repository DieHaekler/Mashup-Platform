package ch.ffhs.inf09.pa.mashup_platform.common.db;

import java.util.*;

import javax.persistence.Id;
import javax.persistence.Version;

public class Mashups
{	
	public static final int STATUS_ALL = 1;
	public static final int STATUS_PENDING = 2;
	public static final int STATUS_ACTIVE = 3;
	public static final int STATUS_INACTIVE = 4;
	public static final int STATUS_REJECTED = 5;
	public static final int SORTED_BY_NAME_ASC = 1;
	public static final int SORTED_BY_NAME_DESC = 2;
	public static final int SORTED_BY_DATE_ASC = 3;
	public static final int SORTED_BY_DATE_DESC = 4;
	private int status;
	private int sortedBy;
	private String username;
	private ArrayList<Mashup> list = new ArrayList<Mashup>();
	
	public Mashups(int status, int sortedBy)
	{
		this.status = status;
		this.sortedBy = sortedBy;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}

	public void setSortedBy(int sortedBy) {
		this.sortedBy = sortedBy;
	}

	public void add(Mashup mashup)
	{
		list.add(mashup);
	}
	
	public ArrayList<Mashup> getList()
	{
		return list;
	}
	
	
	public int getStatus() { return status; }
	public int getSortedBy() { return sortedBy; }
	public String getUsername() { return username; }
}