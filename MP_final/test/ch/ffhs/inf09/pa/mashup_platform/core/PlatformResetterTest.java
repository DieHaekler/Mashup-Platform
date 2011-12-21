package ch.ffhs.inf09.pa.mashup_platform.core;

import ch.ffhs.inf09.pa.mashup_platform.common.db.MashupPage;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import static org.junit.Assert.*;

import org.junit.Test;
import ch.ffhs.inf09.pa.mashup_platform.config.*;

import java.io.IOException;

/**
 * This is a test class for the class PlatformResetter.
 * 
 * @author Alexander
 * 
 */
public class PlatformResetterTest {
	@Test
	public void mainTest() throws IOException {
		// initalize test variables
		String filepathCache = Config.getFilepathSystem() + "/cache/Test.cache";
		String filepathStatus = Config.getFilepathSystem()
				+ "/output/status/Test.status";
		String filepathQueue = Config.getFilepathSystem()
				+ "/output/queue/Test.output";
		String filepathDone = Config.getFilepathSystem()
				+ "/output/done/Test.output";
		String filepathOutput = Config.getFilepathSystem()
				+ "/output/Test.output";
		MashupPage page = new MashupPage();

		// write files
		FileMP.write(filepathCache, page);
		FileMP.write(filepathStatus, page);
		FileMP.write(filepathQueue, page);
		FileMP.write(filepathDone, page);
		FileMP.write(filepathOutput, page);

		// check if the files have been created
		assertTrue(FileMP.exists(filepathCache));
		assertTrue(FileMP.exists(filepathStatus));
		assertTrue(FileMP.exists(filepathQueue));
		assertTrue(FileMP.exists(filepathDone));
		assertTrue(FileMP.exists(filepathOutput));

		// reset the platform
		PlatformResetter.main(null);

		// check if the files have been deleted
		assertFalse(FileMP.exists(filepathCache));
		assertFalse(FileMP.exists(filepathStatus));
		assertFalse(FileMP.exists(filepathQueue));
		assertFalse(FileMP.exists(filepathDone));
		assertFalse(FileMP.exists(filepathOutput));
	}
}