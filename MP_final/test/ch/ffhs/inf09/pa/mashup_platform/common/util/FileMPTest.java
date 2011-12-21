package ch.ffhs.inf09.pa.mashup_platform.common.util;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.ffhs.inf09.pa.mashup_platform.config.Config;
import ch.ffhs.inf09.pa.mashup_platform.core.PlatformResetter;

/**
 * This is a test class for the class FileMP.
 * 
 * @author Alexander
 * 
 */
public class FileMPTest {

	@BeforeClass
	public static void setUpBeforeClass() {
		PlatformResetter.main(null);
	}

	@AfterClass
	public static void tearDownAfterClass() {
		PlatformResetter.main(null);
	}

	@Test
	public void mainTest() throws IOException, ClassNotFoundException {
		// initialize test variables
		Config config = Config.getInstance();
		String message = "Test";
		String filepath = config.getValue(Config.PARAM_FILE_PATH_SYSTEM);
		String testFilepath = filepath + "Test.txt";
		String testString = new String("Test");
		String testFilepathObj = filepath + "Test.obj";

		// write test file
		FileMP.write(testFilepath, message, false);
		assertTrue(FileMP.exists(testFilepath));
		assertEquals("Test\n", FileMP.getContent(testFilepath));

		// append message
		FileMP.write(testFilepath, message, true);
		assertEquals("TestTest\n", FileMP.getContent(testFilepath));

		// write serialized object to file
		FileMP.write(testFilepathObj, testString);
		assertEquals("Test", FileMP.get(testFilepathObj));
		assertEquals("Test.obj", FileMP.getFilenames(filepath, "obj").get(0));

		// move the file (no force)
		FileMP.move(testFilepathObj, testFilepath, false);
		// check if the file still exists
		assertTrue(FileMP.exists(testFilepathObj));

		// move the file (force)
		FileMP.move(testFilepathObj, testFilepath, true);
		// check if the old file path doesn't exist anymore
		assertFalse(FileMP.exists(testFilepathObj));
		// check if the file has been moved to the new file path
		assertEquals("Test", FileMP.get(testFilepath));

		// remove the file
		FileMP.remove(testFilepath);
		assertFalse(FileMP.exists(testFilepath));
	}
}
