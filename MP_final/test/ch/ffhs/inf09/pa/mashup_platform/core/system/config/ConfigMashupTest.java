package ch.ffhs.inf09.pa.mashup_platform.core.system.config;

import java.util.*;
import java.io.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.Config;
import ch.ffhs.inf09.pa.mashup_platform.core.PlatformResetter;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ConfigMashupTest
{
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
		String filepath = Config.getFilepathVar() + "/" + "portrait_of_finnish_bands"
			+ "/config/config.properties";	
		ConfigMashup config = new ConfigMashup(filepath);		
		assertNotNull(config);
		assertNotNull(config.properties);
		assertEquals("3", config.getValue("NUMBER_RECORDS_PER_PAGE"));
	}
	
	@Test(expected=ExceptionMP.class)
    public void testWrongFilepathException() throws ExceptionMP {
		new ConfigMashup("wrongFilepath");
    }	
}