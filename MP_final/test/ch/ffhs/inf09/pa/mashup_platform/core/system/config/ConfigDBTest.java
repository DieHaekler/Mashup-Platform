package ch.ffhs.inf09.pa.mashup_platform.core.system.config;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.ffhs.inf09.pa.mashup_platform.common.util.ExceptionMP;
import ch.ffhs.inf09.pa.mashup_platform.config.Config;
import ch.ffhs.inf09.pa.mashup_platform.core.PlatformResetter;

/**
 * This is a test class for the class ConfigDB.
 * 
 * @author Alexander
 * 
 */
public class ConfigDBTest {
	@BeforeClass
	public static void setUpBeforeClass() {
		PlatformResetter.main(null);
	}

	@AfterClass
	public static void tearDownAfterClass() {
		PlatformResetter.main(null);
	}

	@Test
	public void mainTest() throws ExceptionMP {
		// get file path
		String filepath = Config.getFilepathVar() + "/"
				+ "portrait_of_finnish_bands"
				+ "/config/db/DBFinnishBands.properties";
		// create ConfigMashup instance
		ConfigDB config = new ConfigDB(filepath);

		// check values
		assertNotNull(config.properties);
		assertEquals("http://en.wikipedia.org/wiki/List_of_bands_from_Finland",
				config.getValue("URL"));
	}

	@Test(expected = ExceptionMP.class)
	public void testWrongFilepathException() throws ExceptionMP {
		new ConfigDB("wrongFilepath");
	}
}