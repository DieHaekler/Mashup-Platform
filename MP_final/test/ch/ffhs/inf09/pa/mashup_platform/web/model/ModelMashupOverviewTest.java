package ch.ffhs.inf09.pa.mashup_platform.web.model;

import ch.ffhs.inf09.pa.mashup_platform.common.db.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.ExceptionMP;
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
 * This is a test class for the class ModelMashupOverview.
 * 
 * @author Alexander
 * 
 */
public class ModelMashupOverviewTest {

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
		controller = new Controller(ident, pageNr + 1);
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

		// create Environment instance
		Environment env = new Environment(request, Config.getFilepathSystem()
				+ "WebContent");

		// create ModelMashupOverview instance and check values
		ModelMashupOverview model = new ModelMashupOverview(env);
		assertNotNull(model.getDB());
		MashupOverview info = model.get(0, 2);
		assertNotNull(info);
		assertEquals(ident, info.getList().get(0).getMashupIdent());
		assertEquals(pageNr + 1, info.getList().get(0).getNumberPages());
		env.close();
	}
}