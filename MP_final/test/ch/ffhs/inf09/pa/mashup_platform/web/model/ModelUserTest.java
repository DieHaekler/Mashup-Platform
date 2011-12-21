package ch.ffhs.inf09.pa.mashup_platform.web.model;

import ch.ffhs.inf09.pa.mashup_platform.common.util.ExceptionMP;
import ch.ffhs.inf09.pa.mashup_platform.config.Config;
import ch.ffhs.inf09.pa.mashup_platform.core.PlatformResetter;
import ch.ffhs.inf09.pa.mashup_platform.web.*;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

/**
 * This is a test class for the class ModelUser.
 * 
 * @author Alexander
 * 
 */
public class ModelUserTest {
	@BeforeClass
	public static void setUpBeforeClass() throws ExceptionMP {
		PlatformResetter.main(null);
	}

	@Test
	public void mainTest() throws ExceptionMP {
		// create Environment instance
		Environment env = new Environment(new MockHttpServletRequest(),
				Config.getFilepathSystem() + "WebContent");

		// create ModelUser instance and check values
		ModelUser model = new ModelUser(env, "admin", "admin");
		assertNotNull(model.getDB());
		assertNotNull(model.getEnvironment());
		assertNull(model.getUser());
		assertFalse(model.doesExist());
		env.close();
	}

}