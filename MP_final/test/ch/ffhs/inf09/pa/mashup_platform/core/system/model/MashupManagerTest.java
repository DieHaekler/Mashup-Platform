package ch.ffhs.inf09.pa.mashup_platform.core.system.model;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;
import java.io.*;

import ch.ffhs.inf09.pa.mashup_platform.common.db.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.*;
import ch.ffhs.inf09.pa.mashup_platform.core.*;

/**
 * This is a test class for the class MashupManager.
 * 
 * @author Alexander
 * 
 */
public class MashupManagerTest {
	@BeforeClass
	public static void setUpBeforeClass() {
		PlatformResetter.main(null);
	}

	@AfterClass
	public static void tearDownAfterClass() {
		PlatformResetter.main(null);
	}

	@Test
	public void mainTest() throws ExceptionMP, InterruptedException,
			ClassNotFoundException, IOException {
		// initialize test variables
		String ident = "portrait_of_finnish_bands";
		String filepath = Config.getFilepathVar() + "/" + ident
				+ "/config/config.properties";
		String folderpathOutput = Config.getFilepathSystem() + "output/";

		// create new MashupInfo instance
		MashupInfo info = new MashupInfo(filepath);
		info.setPageNrProcessed(1);
		info = new MashupInfo(filepath);

		// wait for 2 seconds
		Thread.sleep(2000);

		// check if the MashupManager doesn't pick the info with a higher
		// interval than 2 seconds
		assertNull(MashupManager.pick(3));

		// check if the MashupManager picks the info with a lower interval than
		// 2 seconds
		assertNotNull(MashupManager.pick(1));

		// pick the info and compare the values with the created MashupInfo
		// instance
		MashupInfo mashupInfo = MashupManager.pick(0);
		assertEquals(info.getIdent(), mashupInfo.getIdent());
		assertEquals(info.getLastProcessed(), mashupInfo.getLastProcessed());

		// create new MashupPage instance
		MashupPage page = new MashupPage();
		Content content = new Content();
		page.setContent(content);
		page.setMashupIdent(ident);
		page.setLastUpdated(new Date());

		// get the file path
		String hash = content.getHashCode();
		String filename = page.getMashupIdent() + "_" + page.getPageNr() + "_"
				+ hash + ".output";
		String filepathOutput = folderpathOutput + filename;

		// check if the file doesn't already exist
		assertFalse(FileMP.exists(filepathOutput));

		// store the file
		MashupManager.store(page);

		// check if the file exists now
		assertTrue(FileMP.exists(filepathOutput));

		// deserialize the MashupPage instance and compare the idents
		MashupPage mp = (MashupPage) FileMP.get(filepathOutput);
		assertEquals(ident, mp.getMashupIdent());

	}
}