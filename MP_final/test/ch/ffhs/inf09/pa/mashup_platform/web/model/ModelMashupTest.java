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
 * This is a test class for the class ModelMashup.
 * 
 * @author Alexander
 * 
 */
public class ModelMashupTest {
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
		// create Environment instance
		Environment env = new Environment(new MockHttpServletRequest(),
				Config.getFilepathSystem() + "WebContent");

		// create ModelMashup instance and check values
		ModelMashup model = new ModelMashup(env, ident, pageNr);
		assertNotNull(model.getDB());
		assertEquals(ident, model.getPage().getMashupIdent());
		assertEquals(pageNr, model.getPage().getPageNr());
		assertEquals(0, model.getInfo().getNumberPages());
		env.close();
	}
}