package ch.ffhs.inf09.pa.mashup_platform.web;

import javax.servlet.http.*;

public class Environment
{
	private HttpServletRequest request;
	private HttpSession session;
	
	public Environment(HttpServletRequest request)
	{
		this.request = request;
		session = request.getSession(true);
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
}
