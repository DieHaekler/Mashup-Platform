package ch.ffhs.inf09.pa.mashup_platform.core;

import java.util.Set;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * This is a test class for the class BotStopper.
 * 
 * @author Alexander
 * 
 */
public class BotStopperTest {
	@BeforeClass
	public static void setUpBeforeClass() {
		PlatformResetter.main(null);
		BotStarter.main(null);

	}

	@AfterClass
	public static void tearDownAfterClass() {
		PlatformResetter.main(null);
	}

	@Test
	public void mainTest() throws InterruptedException {
		boolean bot = true;
		boolean dbFeeder = true;

		// stop threads
		while (bot || dbFeeder) {
			BotStopper.main(null);
			bot = false;
			dbFeeder = false;
			Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
			for (Thread t : threadSet) {
				if (t.getName().equals("bot")) {
					bot = true;
				}
				if (t.getName().equals("db_feeder")) {
					dbFeeder = true;
				}
			}
			Thread.sleep(1000);
		}

		// check if the threads have been stopped
		assertFalse(bot);
		assertFalse(dbFeeder);
	}
}