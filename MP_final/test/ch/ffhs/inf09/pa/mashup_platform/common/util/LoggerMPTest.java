package ch.ffhs.inf09.pa.mashup_platform.common.util;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.ffhs.inf09.pa.mashup_platform.config.Config;
import ch.ffhs.inf09.pa.mashup_platform.core.PlatformResetter;

/**
 * This is a test class for the class LoggerMP.
 * 
 * @author Alexander
 * 
 */
public class LoggerMPTest {

	@BeforeClass
	public static void setUpBeforeClass() {
		PlatformResetter.main(null);
	}

	@AfterClass
	public static void tearDownAfterClass() {
		PlatformResetter.main(null);
	}

	@Test
	public void mainTest() throws FileNotFoundException, IOException {
		// initialize test variables
		Calendar cal = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("yyyy_MM_dd");
		String filepath = Config.getInstance().getValue(
				Config.PARAM_FILE_PATH_SYSTEM)
				+ "/log/log_" + df.format(cal.getTime()) + ".txt";
		String errorPath = Config.getInstance().getValue(
				Config.PARAM_FILE_PATH_SYSTEM)
				+ "/log/error_" + df.format(cal.getTime()) + ".txt";
		df = new SimpleDateFormat("HH:mm:ss");
		String time = df.format(cal.getTime());

		// remove existing files
		FileMP.remove(filepath);
		FileMP.remove(errorPath);

		// write test log
		LoggerMP.writeNotice("TestLog");
		// check if the file has been created
		assertTrue(FileMP.exists(filepath));
		// check content
		assertEquals(time + " --- " + "TestLog\n", FileMP.getContent(filepath));

		// remove the file
		FileMP.remove(filepath);

		// write error log
		ExceptionMP exceptionMP = new ExceptionMP("ExceptionMP",
				new Exception());
		LoggerMP.writeError(exceptionMP);
		assertTrue(FileMP.exists(errorPath));
		// remove the file
		FileMP.remove(errorPath);
	}

}
