package ch.ffhs.inf09.pa.mashup_platform.core.system.controller.background;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.Config;
import ch.ffhs.inf09.pa.mashup_platform.core.PlatformResetter;


/**
 * This is a test class for the class Bot.
 * 
 * @author Alexander
 * 
 */
public class BotTest {
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
		// create bot instance and start thread
		Bot bot = new Bot();
		Thread t = new Thread(bot);
		t.start();
		Thread.sleep(10000);

		// check if the file has been created
		boolean fileCreated = false;
		File file = new File(Config.getFilepathSystem() + "/output/");
		File[] files = file.listFiles();
		for (File f : files) {
			if (f.getPath().contains("portrait_of_finnish_bands")) {
				fileCreated = true;
			}
		}
		assertTrue(fileCreated);
	}

}