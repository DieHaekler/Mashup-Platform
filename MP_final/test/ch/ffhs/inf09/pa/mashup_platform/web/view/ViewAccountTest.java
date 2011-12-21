package ch.ffhs.inf09.pa.mashup_platform.web.view;

import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.Config;
import ch.ffhs.inf09.pa.mashup_platform.core.PlatformResetter;
import ch.ffhs.inf09.pa.mashup_platform.web.Environment;
import ch.ffhs.inf09.pa.mashup_platform.web.model.*;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import org.springframework.mock.web.MockHttpServletRequest;

/**
 * This is a test class for the class ViewAccount.
 * 
 * @author Alexander
 * 
 */
public class ViewAccountTest {
	@BeforeClass
	public static void setUpBeforeClass() throws ExceptionMP {
		PlatformResetter.main(null);
	}

	@Test
	public void mainTest() throws ExceptionMP {
		// create MockHttpServletRequest instance
		MockHttpServletRequest request = new MockHttpServletRequest();

		// create Environment instance
		Environment env = new Environment(request, Config.getFilepathSystem()
				+ "WebContent");

		// create ViewAccount instance and check values
		ViewAccount view = new ViewAccount(new ModelUser(env, "admin", "admin"));
		assertNotNull(view);
		env.close();
	}
}