package ch.ffhs.inf09.pa.mashup_platform.web.controller;

import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.Config;
import ch.ffhs.inf09.pa.mashup_platform.web.*;

import static org.junit.Assert.*;

import org.springframework.mock.web.MockHttpServletRequest;

/**
 * This is a test class for the class ControllerMashupOverview.
 * 
 * @author Alexander
 * 
 */
public class ControllerMashupOverviewTest {
	public void mainTest() throws ExceptionMP {
		// create MockHttpServletRequest instance
		MockHttpServletRequest request = new MockHttpServletRequest();

		// create Environment instance
		Environment env = new Environment(request, Config.getFilepathSystem()
				+ "WebContent");

		// create ControllerMashupOverview instance and check values
		ControllerMashupOverview controller = new ControllerMashupOverview(env);
		assertNotNull(controller.environment);
		assertNotNull(controller.getView());
		env.close();
	}

}