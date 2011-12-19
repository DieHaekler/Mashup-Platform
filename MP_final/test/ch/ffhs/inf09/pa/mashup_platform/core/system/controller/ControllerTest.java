package ch.ffhs.inf09.pa.mashup_platform.core.system.controller;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.core.PlatformResetter;
import ch.ffhs.inf09.pa.mashup_platform.core.system.config.ConfigDB;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.*;



public class ControllerTest
{
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
	public void mainTest() throws ExceptionMP
	{
		Controller controller = new Controller(ident, 0);		
		assertNotNull(controller.getPage());
		assertEquals(0, controller.getPage().getPageNr());		
	}
	
	@Test(expected=ExceptionMP.class)
    public void testWrongIdentException() throws ExceptionMP {
		new Controller("wrongIdent", 0);
    }	
	
	@Test(expected=ExceptionMP.class)
    public void testWrongPageNrException() throws ExceptionMP {
		new Controller(ident, -1);
    }
	
}