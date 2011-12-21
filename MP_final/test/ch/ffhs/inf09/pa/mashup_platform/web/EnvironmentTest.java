package ch.ffhs.inf09.pa.mashup_platform.web;

import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.*;
import ch.ffhs.inf09.pa.mashup_platform.core.PlatformResetter;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

/**
 * This is a test class for the class Environment.
 * 
 * @author Alexander
 * 
 */
public class EnvironmentTest {

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
		// create MockHttpServletRequest instance and set parameters
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("name", "value");

		// create Environment instance
		Environment env = new Environment(request, Config.getFilepathSystem()
				+ "WebContent");

		// check values
		assertEquals("value", env.getValuePost("name"));
		assertNotNull(env.getConfig());
		assertNotNull(env.getDB());

		// test login with a wrong password
		env.login("admin", "wrongPassword");
		assertNull(env.getUsername());
		assertFalse(env.isUserLoggedIn());

		// test login with the right username and password
		env.login("admin", "admin");
		assertEquals("admin", env.getUsername());
		assertTrue(env.isUserLoggedIn());

		// test logout
		env.logout();
		assertNull(env.getUsername());
		assertFalse(env.isUserLoggedIn());
		env.close();
	}
}
