package ch.ffhs.inf09.pa.mashup_platform.web.model;

import ch.ffhs.inf09.pa.mashup_platform.common.db.*;

public class ModelMashups extends ModelApplication
{	
	public ModelMashups()
	{
		super();
	}
	
	public Mashups get(int start, int number, int sortedBy, int status,
			String username)
	{
		return db.getMashups(start, number, sortedBy, status, username);
	}
	
	public Mashups get(int start, int number, int sortedBy, int status)
	{
		return db.getMashups(start, number, sortedBy, status);
	}
	
	public Mashups get(int start, int number, int sortedBy)
	{
		return db.getMashups(start, number, sortedBy);
	}
	
	public Mashups get(int start, int number)
	{
		return db.getMashups(start, number);
	}
}