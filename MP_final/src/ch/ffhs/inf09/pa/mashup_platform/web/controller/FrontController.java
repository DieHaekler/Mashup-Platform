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
		initEnvironment(request);
		Environment environment = Environment.getInstance();
		ControllerApplication controller = null;
		try
		{
			if ( environment.isUserLoggedIn() )
			{
				controller = new ControllerAccount();
			} else
			{
				controller = new ControllerMashupOverview();
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
	
	private void initEnvironment(HttpServletRequest request)
	{
		Environment environment = Environment.getInstance(request);
		
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
	}

}
