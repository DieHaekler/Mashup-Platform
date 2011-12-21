package ch.ffhs.inf09.pa.mashup_platform.web.controller;

import ch.ffhs.inf09.pa.mashup_platform.web.Environment;

import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.Config;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

/**
 * This is a test class for the class ControllerLogin.
 * 
 * @author Alexander
 * 
 */
public class ControllerLoginTest {
	@Test
	public void mainTest() throws ExceptionMP {
		// create MockHttpServletRequest instance
		MockHttpServletRequest request = new MockHttpServletRequest();

		// create Environment instance
		Environment env = new Environment(request, Config.getFilepathSystem()
				+ "WebContent");

		// create ControllerLogin instance and check values
		ControllerLogin controller = new ControllerLogin(env);
		assertNotNull(controller.environment);
		assertNotNull(controller.getView());
		assertTrue(controller.getView().getContent().contains("admin"));
		env.close();
	}
}