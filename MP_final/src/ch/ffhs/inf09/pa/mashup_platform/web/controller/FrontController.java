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

	public FrontController()
	{
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Environment environment = initEnvironment(request);
		ControllerApplication controller = null;
		try
		{
			if ( environment.isUserLoggedIn() )
			{
				controller = new ControllerAccount(environment);
			} else
			{
				// check if user wants to log in
				String loginParm = environment.getValuePost("login");
				if (loginParm != null && loginParm.equals("1") )
				{
					controller = new ControllerLogin(environment);
				} else 
				{
					// Check if specific mashup was accessed
					String repositoryParm = environment.getValuePost("repository");
					//String mashupParm = environment.getValuePost("mashup");

					if (repositoryParm != null)
					{					
						controller = new ControllerViewMashups(environment);
						//controller = new ControllerViewMashup(environment);
					} 
					else {
						controller = new ControllerMashupOverview(environment);
					}
				}
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
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
	
	private Environment initEnvironment(HttpServletRequest request)
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
