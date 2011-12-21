package ch.ffhs.inf09.pa.mashup_platform.core.system.config;

import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.Config;
import ch.ffhs.inf09.pa.mashup_platform.core.PlatformResetter;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * This is a test class for the class ConfigMashup.
 * 
 * @author Alexander
 * 
 */
public class ConfigMashupTest {
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
				+ "portrait_of_finnish_bands" + "/config/config.properties";
		// create ConfigMashup instance
		ConfigMashup config = new ConfigMashup(filepath);
		assertNotNull(config.properties);
		assertEquals("3", config.getValue("NUMBER_RECORDS_PER_PAGE"));
	}

	@Test(expected = ExceptionMP.class)
	public void testWrongFilepathException() throws ExceptionMP {
		new ConfigMashup("wrongFilepath");
	}
}