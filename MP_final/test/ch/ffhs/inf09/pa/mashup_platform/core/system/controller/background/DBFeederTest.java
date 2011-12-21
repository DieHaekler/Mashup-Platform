package ch.ffhs.inf09.pa.mashup_platform.core.system.controller.background;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.ffhs.inf09.pa.mashup_platform.common.db.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.Config;
import ch.ffhs.inf09.pa.mashup_platform.core.PlatformResetter;

/**
 * This is a test class for the class DBFeeder.
 * 
 * @author Alexander
 * 
 */
public class DBFeederTest {

	@BeforeClass
	public static void setUpBeforeClass() {
		PlatformResetter.main(null);
	}

	@AfterClass
	public static void tearDownAfterClass() {
		PlatformResetter.main(null);
	}

	@Test
	public void mainTest() throws ExceptionMP, InterruptedException {
		String ident = "portrait_of_finnish_bands";
		// get Config instance
		Config config = Config.getInstance();

		// start new Bot thread
		new Thread(new Bot()).start();

		// start new DBFeeder thread
		DBFeeder dbFeeder = new DBFeeder();
		new Thread(dbFeeder).start();

		// set up database
		DBOrient db = new DBOrient(config.getValue(Config.PARAM_DB_USERNAME),
				config.getValue(Config.PARAM_DB_PASSWORD));

		// get sleep time
		String property = dbFeeder.properties
				.getProperty(DBFeeder.PARAM_SLEEP_TIME);
		int sleepTime;
		if (property == null) {
			sleepTime = 0;
		} else {
			sleepTime = Integer.parseInt(property);
		}
		Thread.sleep(sleepTime + 1000);

		// check if the first page has been created
		assertNotNull(db.getPage(ident, 0));
		Thread.sleep(30000);

		// check if the second page has been created
		assertNotNull(db.getPage(ident, 1));
		db.close();
	}

}