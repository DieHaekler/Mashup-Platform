package ch.ffhs.inf09.pa.mashup_platform.web;

import javax.servlet.*;

import javax.servlet.http.*;

import ch.ffhs.inf09.pa.mashup_platform.common.db.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.*;
import ch.ffhs.inf09.pa.mashup_platform.core.PlatformResetter;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

public class EnvironmentTest
{
	private MockHttpServletRequest request;
	
	@BeforeClass
    public static void setUpBeforeClass() {
       PlatformResetter.main(null);
    }

    @AfterClass
    public static void tearDownAfterClass() {
        PlatformResetter.main(null);
    }
	
	@Test
	public void mainTest() throws ExceptionMP{
		request = new MockHttpServletRequest();
		request.setParameter("name", "value");
		Environment env = new Environment(request, Config.getFilepathSystem() + "WebContent");
		assertEquals("value",env.getValuePost("name"));
		assertNotNull(env.getConfig());
		assertNotNull(env.getDB());
		env.login("admin", "wrongPassword");
		assertNull(env.getUsername());
		assertFalse(env.isUserLoggedIn());
		env.login("admin", "admin");
		assertEquals("admin",env.getUsername());
		assertTrue(env.isUserLoggedIn());
		env.logout();
		assertNull(env.getUsername());
		assertFalse(env.isUserLoggedIn());
		
		
		env.close();
	}
}
