package ch.ffhs.inf09.pa.mashup_platform.web.controller;

import ch.ffhs.inf09.pa.mashup_platform.common.db.DBOrient;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.Config;
import ch.ffhs.inf09.pa.mashup_platform.core.PlatformResetter;
import ch.ffhs.inf09.pa.mashup_platform.core.system.controller.Controller;
import ch.ffhs.inf09.pa.mashup_platform.web.view.*;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import org.springframework.mock.web.*;

/**
 * This is a test class for the class FrontController.
 * 
 * @author Alexander
 * 
 */
public class FrontControllerTest {
	@BeforeClass
	public static void setUpBeforeClass() throws ExceptionMP {
		PlatformResetter.main(null);

		// create a new mashup page and store it into database
		Controller controller = new Controller("portrait_of_finnish_bands", 0);
		Config config = Config.getInstance(Config.getFilepathSystem()
				+ "WebContent/config/config.properties");
		DBOrient db = new DBOrient(config.getValue(Config.PARAM_DB_USERNAME),
				config.getValue(Config.PARAM_DB_PASSWORD));
		db.setPage(controller.getPage());
	}

	@AfterClass
	public static void tearDownAfterClass() {
		PlatformResetter.main(null);
	}

	@Test
	public void mainTest() throws Exception {
		// create FrontController instance and mock objects
		FrontController servlet = new FrontController();
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		servlet.init(new MockServletConfig());

		// set request parameters
		request.setParameter("filepath", Config.getFilepathSystem()
				+ "WebContent");
		request.setParameter("user", "admin");
		request.setParameter("pw", "admin");
		// log in
		servlet.doGet(request, response);
		assertTrue(response.getContentAsString().contains("Mashup Overview"));

		// log out
		request.setParameter("logout", "1");
		servlet.doGet(request, response);
		assertTrue(response.getContentAsString().contains("Mashup Overview"));

		// set parameters for mashup request
		request.setParameter("menu", "MASHUP");
		request.setParameter("id", "portrait_of_finnish_bands");
		request.setParameter("p", "0");

		// check doPost method
		servlet.doPost(request, response);
		// check response values
		assertEquals(ViewApplication.CONTENT_TYPE_HTML,
				response.getContentType());
		assertNotNull(response.getOutputStream());
		assertTrue(response.getContentAsString().contains("flickr"));
		assertTrue(response.getContentAsString().contains("google_search"));
		assertTrue(response.getContentAsString().contains("finnish_bands"));
	}

}
