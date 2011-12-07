package ch.ffhs.inf09.pa.mashup_platform.web;

import javax.servlet.*;
import javax.servlet.http.*;
import ch.ffhs.inf09.pa.mashup_platform.common.db.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.*;

public class Environment
{
	private HttpServletRequest request;
	private HttpSession session;
	private Config config;
	private DBLocal db;
	
	public Environment(HttpServletRequest request) throws ExceptionMP
	{
		this.request = request;
		session = request.getSession(true);
		ServletContext context = session.getServletContext();
		String filepath = context.getRealPath("");
		config = Config.getInstance(filepath + "/config/config.properties");
		db = new DBOrient(config.getValue(Config.PARAM_DB_USERNAME),
			config.getValue(Config.PARAM_DB_PASSWORD));
	}
	
	public boolean login(String username, String password)
	{
		if (username != null && password != null
			&& username.equals("admin") && password.equals("admin") )
		{
			setUsername(username);
			return true;
		}
		return false;
	}
	
	public DBLocal getDB() { return db; }
	public void close() { db.close(); }
	
	public void logout()
	{
		setUsername(null);
	}
	
	public String getUsername()
	{
		return (String)session.getAttribute("username");
	}
	
	public void setUsername(String username)
	{
		session.setAttribute("username", username);
	}
	
	public boolean isUserLoggedIn()
	{
		return getUsername() != null;
	}
	
	public String getValuePost(String name)
	{
		return request.getParameter(name);
	}

	public Config getConfig() { return config; }
}
