package ch.ffhs.inf09.pa.mashup_platform.web.controller;

import java.io.*;



import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import ch.ffhs.inf09.pa.mashup_platform.common.db.DBOrient;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.Config;
import ch.ffhs.inf09.pa.mashup_platform.core.PlatformResetter;
import ch.ffhs.inf09.pa.mashup_platform.core.system.controller.Controller;
import ch.ffhs.inf09.pa.mashup_platform.web.view.*;
import ch.ffhs.inf09.pa.mashup_platform.web.*;

import static org.junit.Assert.*;



import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.servlet.ServletException; 
import org.junit.*; 
import static org.junit.Assert.*; 
import org.springframework.mock.web.*; 

public class FrontControllerTest
{
	
	  private FrontController servlet = new FrontController(); 
	  private MockHttpServletRequest request; 
	  private MockHttpServletResponse response; 

	  @BeforeClass
	  public static void setUpBeforeClass() throws ExceptionMP {
	     PlatformResetter.main(null);
	     Controller controller = new Controller("portrait_of_finnish_bands", 0);
	     Config config = Config.getInstance(Config.getFilepathSystem() + "WebContent/config/config.properties");
	     DBOrient db = new DBOrient(config.getValue(Config.PARAM_DB_USERNAME),
		 config.getValue(Config.PARAM_DB_PASSWORD));
	     db.setPage(controller.getPage());
	  }

	  @AfterClass
	  public static void tearDownAfterClass() {
	      PlatformResetter.main(null);
	  }
	  
	  @Before 
	  public void before() throws ServletException 
	  { 
	    servlet.init( new MockServletConfig() ); 

	    servlet = new FrontController(); 
	    request = new MockHttpServletRequest(); 	    
	    response = new MockHttpServletResponse(); 	    
	  } 

	  @Test 
	  public void doGetTest() throws Exception 
	  { 
		 request.setParameter("filepath", Config.getFilepathSystem() + "WebContent");
		 request.setParameter("user", "admin");
		 request.setParameter("pw", "admin");   		
		 servlet.doGet(request, response); 
		 request.setParameter("logout", "1");
		 servlet.doGet(request, response);
		 request.setParameter("menu", "MASHUP");
		 request.setParameter("id", "portrait_of_finnish_bands");
		 request.setParameter("p", "0");
	
		 servlet.doPost(request, response);
		 assertEquals(ViewApplication.CONTENT_TYPE_HTML, response.getContentType());
		 assertNotNull(response.getOutputStream());
		 assertTrue(response.getContentAsString().contains("flickr"));
		 assertTrue(response.getContentAsString().contains("google_search"));
		 assertTrue(response.getContentAsString().contains("finnish_bands"));
	  } 

}
