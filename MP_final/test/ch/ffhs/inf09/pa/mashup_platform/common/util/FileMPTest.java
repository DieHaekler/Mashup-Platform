package ch.ffhs.inf09.pa.mashup_platform.common.util;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.ffhs.inf09.pa.mashup_platform.config.Config;
import ch.ffhs.inf09.pa.mashup_platform.core.PlatformResetter;

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
	public void mainTest() throws IOException, ClassNotFoundException{
		Config config = Config.getInstance();
		String message = "Test";
		String filepath = config.getValue(Config.PARAM_FILE_PATH_SYSTEM);
		String testFilepath = filepath + "Test.txt";
		FileMP.write(testFilepath, message, false);
		assertEquals("Test\n", FileMP.getContent(testFilepath));		
		assertTrue(FileMP.exists(testFilepath));	
		FileMP.write(testFilepath, message, true);			
		Date now = new Date();
		//assertTrue(now.getTime() > FileMP.getTimestamp(testFilepath));	
		assertEquals("TestTest\n", FileMP.getContent(testFilepath));		
		String testString = "Test";
		String testFilepathObj = filepath + "Test.obj";
		FileMP.write(testFilepathObj,testString);
		assertEquals("Test",FileMP.get(testFilepathObj));						
		assertEquals("Test.obj", FileMP.getFilenames(filepath, "obj").get(0));		
		FileMP.move(testFilepathObj, testFilepath, false);	
		assertTrue(FileMP.exists(testFilepathObj));

		FileMP.move(testFilepathObj, testFilepath, true);	
		assertFalse(FileMP.exists(testFilepathObj));
		assertEquals("Test",FileMP.get(testFilepath));

		FileMP.remove(testFilepath);
		assertFalse(FileMP.exists(testFilepath));						
	}	
}
