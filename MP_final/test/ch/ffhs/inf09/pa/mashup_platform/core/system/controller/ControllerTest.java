package ch.ffhs.inf09.pa.mashup_platform.core.system.controller;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.core.PlatformResetter;

/**
 * This is a test class for the class Controller.
 * 
 * @author Alexander
 * 
 */
public class ControllerTest {
	private String ident = "portrait_of_finnish_bands";

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
		// create controller instance
		Controller controller = new Controller(ident, 0);

		// check if a mashup page has been created
		assertNotNull(controller.getPage());

		// check the mashup page number
		assertEquals(0, controller.getPage().getPageNr());
	}

	@Test(expected = ExceptionMP.class)
	public void testWrongIdentException() throws ExceptionMP {
		new Controller("wrongIdent", 0);
	}

	@Test(expected = ExceptionMP.class)
	public void testWrongPageNrException() throws ExceptionMP {
		new Controller(ident, -1);
	}

}