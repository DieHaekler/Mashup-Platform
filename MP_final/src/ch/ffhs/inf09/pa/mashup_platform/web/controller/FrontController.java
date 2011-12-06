package ch.ffhs.inf09.pa.mashup_platform.web.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.web.view.*;
import ch.ffhs.inf09.pa.mashup_platform.web.*;

@WebServlet("/main")
public class FrontController extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	private enum Menu 
	{ 
		HOME, OVERVIEW, MASHUP, ACCOUNT;

	    private static final Menu[] copyOfValues = values();

	    public static Menu forName(String name) {
	        for (Menu value : copyOfValues) {
	            if (value.name().equals(name)) {
	                return value;
	            }
	        }
	        return Menu.HOME;
	    }


	};
	
	public FrontController()
	{
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{	
		ControllerApplication controller = null;
		Environment environment = null;
		try
		{
			environment = initEnvironment(request);
			
			//Default Menu
			Menu menu = Menu.HOME;
			
			//Menu navigation
			if (environment.getValuePost("menu") != null)
			{
				String menuParm = environment.getValuePost("menu");
				menuParm = menuParm.toUpperCase();
				menu = Menu.forName(menuParm);
			}
			
			switch(menu)
             {
               case HOME:
            	   controller = new ControllerMashupOverview(environment);
            	   break;
               case OVERVIEW:
            	   controller = new ControllerMashupOverview(environment);
            	   break;
               case MASHUP:
            	   controller = new ControllerMashup(environment);
            	 break;
               case ACCOUNT:
	       			if ( environment.isUserLoggedIn() )
	    			{
	    				controller = new ControllerAccount(environment);
	    			} else
	    			{
	    				controller = new ControllerLogin(environment);
	    			}
	       			break;
               default:
            	   controller = new ControllerMashupOverview(environment);
             }
		} catch (ExceptionMP e)
		{
			LoggerMP.writeError(e);
			e.printStackTrace();
		}
		if (controller != null)
		{
			ViewApplication view = controller.getView();
			response.setContentType(view.getContentType());
			PrintWriter out = response.getWriter();
			out.println(view.getContent());
		}
		if (environment != null) environment.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		doGet(request, response);
	}
	
	private Environment initEnvironment(HttpServletRequest request) throws ExceptionMP
	{
		Environment environment = new Environment(request);
		if ( environment.isUserLoggedIn() )
		{
			// check if user wants to log out
			String flag = environment.getValuePost("logout");
			if (flag != null && flag.equals("1") )
			{
				environment.logout();
			}
		} else
		{
			// check if user has submitted login data
			if ( !environment.isUserLoggedIn() )
			{
				String username = environment.getValuePost("user");
				String password = environment.getValuePost("pw");
				environment.login(username, password);
			}
		}
		return environment;
	}

}
