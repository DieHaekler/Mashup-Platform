package ch.ffhs.inf09.pa.mashup_platform.web.controller;

import ch.ffhs.inf09.pa.mashup_platform.common.db.DBOrient;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.Config;
import ch.ffhs.inf09.pa.mashup_platform.core.PlatformResetter;
import ch.ffhs.inf09.pa.mashup_platform.core.system.controller.Controller;
import ch.ffhs.inf09.pa.mashup_platform.web.*;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

/**
 * This is a test class for the class ControllerMashup.
 * 
 * @author Alexander
 * 
 */
public class ControllerMashupTest {
	private static final String ident = "portrait_of_finnish_bands";
	private static final int pageNr = 0;

	@BeforeClass
	public static void setUpBeforeClass() throws ExceptionMP {
		PlatformResetter.main(null);

		// create a new mashup page and store it into database
		Controller controller = new Controller(ident, pageNr);
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
	public void mainTest() throws ExceptionMP {
		// create MockHttpServletRequest instance and set parameters
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("id", ident);
		request.setParameter("p", Integer.toString(pageNr));

		// create Environment instance
		Environment env = new Environment(request, Config.getFilepathSystem()
				+ "WebContent");

		// create ControllerMashup instance and check values
		ControllerMashup controller = new ControllerMashup(env);
		assertNotNull(controller.environment);
		String content = controller.getView().getContent();
		assertNotNull(content);
		assertTrue(content.contains("flickr"));
		assertTrue(content.contains("google_search"));
		assertTrue(content.contains("finnish_bands"));
		env.close();
	}
}