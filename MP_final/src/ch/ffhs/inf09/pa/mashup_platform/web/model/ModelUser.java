package ch.ffhs.inf09.pa.mashup_platform.web.model;

import ch.ffhs.inf09.pa.mashup_platform.common.db.*;

public class ModelUser extends ModelApplication
{
	private User user;
	
	public ModelUser(String username, String password)
	{
		super();
		//user = db.getUser(username, password);
	}
	
	public boolean doesExist()
	{
		return user != null;
	}
	
	public User getUser() { return user; }
}