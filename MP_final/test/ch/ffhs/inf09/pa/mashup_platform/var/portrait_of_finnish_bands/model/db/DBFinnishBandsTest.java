package ch.ffhs.inf09.pa.mashup_platform.var.portrait_of_finnish_bands.model.db;

import ch.ffhs.inf09.pa.mashup_platform.core.PlatformResetter;

import ch.ffhs.inf09.pa.mashup_platform.core.system.model.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.Config;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * This is a test class for the class DBFinnishBands.
 * 
 * @author Alexander
 * 
 */
public class DBFinnishBandsTest {
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
		// initialize test variables
		int start = 0;
		int number = 2;
		Content content = new Content();
		content.setCaption("Portrait of Finnish Bands");

		// create new DBFinnishBands instance
		DBFinnishBands dbFinnishBands = new DBFinnishBands(
				Config.getFilepathVar()
						+ "/portrait_of_finnish_bands/config/db/DBFinnishBands.properties");

		// check if the content is only filled with the caption
		assertEquals("{\"caption\":\"Portrait of Finnish Bands\"}",
				content.getJSON());

		// fill in
		dbFinnishBands.fillIn(content, start, number);

		// check if the content is filled now
		assertNotNull(content.getSection(DBFinnishBands.SECTION_IDENT));
		assertEquals(2, content.getSection(DBFinnishBands.SECTION_IDENT)
				.getParts().size());
		assertEquals(
				"{\"caption\":\"Portrait of Finnish Bands\",\"finnish_bands\":{\"parts\":[{\"caption\":\"22-Pistepirkko\"},{\"caption\":\"The 69 Eyes\"}]}}",
				content.getJSON());
	}
}