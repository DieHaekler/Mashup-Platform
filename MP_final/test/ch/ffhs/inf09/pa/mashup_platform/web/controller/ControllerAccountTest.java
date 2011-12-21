package ch.ffhs.inf09.pa.mashup_platform.web.controller;

import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.Config;
import ch.ffhs.inf09.pa.mashup_platform.web.*;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

/**
 * This is a test class for the class ControllerAccount.
 * 
 * @author Alexander
 * 
 */
public class ControllerAccountTest {
	@Test
	public void mainTest() throws ExceptionMP {
		// create MockHttpServletRequest instance
		MockHttpServletRequest request = new MockHttpServletRequest();

		// create Environment instance
		Environment env = new Environment(request, Config.getFilepathSystem()
				+ "WebContent");

		// create ControllerAccount instance and check values
		ControllerAccount controller = new ControllerAccount(env);
		assertNotNull(controller.environment);
		assertNotNull(controller.getView());
		env.close();
	}
}