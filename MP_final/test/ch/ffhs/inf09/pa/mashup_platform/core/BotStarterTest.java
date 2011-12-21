package ch.ffhs.inf09.pa.mashup_platform.core;

import java.util.Set;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * This is a test class for the class BotStarter.
 * 
 * @author Alexander
 * 
 */
public class BotStarterTest {
	@BeforeClass
	public static void setUpBeforeClass() {
		PlatformResetter.main(null);
	}

	@AfterClass
	public static void tearDownAfterClass() {
		BotStopper.main(null);
		PlatformResetter.main(null);
	}

	@Test
	public void mainTest() {
		// run BotStarter
		BotStarter.main(null);

		// check if the threads have been started
		Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
		boolean bot = false;
		boolean dbFeeder = false;

		for (Thread t : threadSet) {
			if (t.getName().equals("bot")) {
				bot = true;
			}
			if (t.getName().equals("db_feeder")) {
				dbFeeder = true;
			}
		}

		assertTrue(bot);
		assertTrue(dbFeeder);
	}
}