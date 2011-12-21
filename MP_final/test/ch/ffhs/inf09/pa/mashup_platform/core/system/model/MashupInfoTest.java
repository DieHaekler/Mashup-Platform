package ch.ffhs.inf09.pa.mashup_platform.core.system.model;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;

import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.*;
import ch.ffhs.inf09.pa.mashup_platform.core.PlatformResetter;
import ch.ffhs.inf09.pa.mashup_platform.core.system.config.ConfigMashup;

/**
 * This is a test class for the class MashupInfo.
 * 
 * @author Alexander
 * 
 */
public class MashupInfoTest {
	@BeforeClass
	public static void setUpBeforeClass() {
		PlatformResetter.main(null);
	}

	@AfterClass
	public static void tearDownAfterClass() {
		PlatformResetter.main(null);
	}

	@Test
	public void mainTest() throws ExceptionMP, NumberFormatException,
			FileNotFoundException, IOException, InterruptedException {
		// initialize test variables
		String testFolder = "portrait_of_finnish_bands";
		int nextPageNr = 2;
		String filepath = Config.getFilepathVar() + "/" + testFolder
				+ "/config/config.properties";
		String filepathStatus = Config.getFilepathSystem() + "/output/status/"
				+ testFolder + "_processed.status";
		long lastProcessed = FileMP.getTimestamp(filepathStatus);

		// create MashupInfo instance
		MashupInfo info = new MashupInfo(filepath);
		assertNotNull(info);
		assertEquals(lastProcessed, info.getLastProcessed());

		// create ConfigMashup instance and compare the values with the values
		// of the MashupInfo instance
		ConfigMashup config = new ConfigMashup(filepath);
		assertEquals(config.getValue("IDENT"), info.getIdent());
		assertEquals(config.getValue("MAX_NUMBER_PAGES"),
				Integer.toString(info.getMaxNumberPages()));

		// set the next page number to be processed
		info.setPageNrProcessed(nextPageNr);
		assertEquals(nextPageNr, info.getPageNrProcessed());
		assertEquals(0, info.getStatus());
	}

	@Test(expected = ExceptionMP.class)
	public void testWrongFilepathException() throws ExceptionMP {
		new MashupInfo("wrongFilepath");
	}

}