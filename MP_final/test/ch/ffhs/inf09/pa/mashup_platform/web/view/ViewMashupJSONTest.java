package ch.ffhs.inf09.pa.mashup_platform.web.view;

import java.util.Date;

import ch.ffhs.inf09.pa.mashup_platform.common.db.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.Config;
import ch.ffhs.inf09.pa.mashup_platform.core.PlatformResetter;
import ch.ffhs.inf09.pa.mashup_platform.core.system.controller.Controller;
import ch.ffhs.inf09.pa.mashup_platform.web.Environment;
import ch.ffhs.inf09.pa.mashup_platform.web.model.*;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import org.springframework.mock.web.MockHttpServletRequest;

/**
 * This is a test class for the class ViewMashupJSON.
 * 
 * @author Alexander
 * 
 */
public class ViewMashupJSONTest {
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
		controller.getPage().setLastUpdated(new Date());
		db.setPage(controller.getPage());
	}

	@AfterClass
	public static void tearDownAfterClass() {
		PlatformResetter.main(null);
	}

	@Test
	public void mainTest() throws ExceptionMP {
		// create Environment instance
		Environment env = new Environment(new MockHttpServletRequest(),
				Config.getFilepathSystem() + "WebContent");

		// create ViewMashupJSON instance and check values
		ViewMashupJSON view = new ViewMashupJSON(new ModelMashup(env, ident,
				pageNr));
		String content = view.getContent();
		assertTrue(content.contains("Portrait of Finnish bands"));
		env.close();
	}
}