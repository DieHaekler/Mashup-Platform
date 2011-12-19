package ch.ffhs.inf09.pa.mashup_platform.core.system.config;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.ffhs.inf09.pa.mashup_platform.common.util.ExceptionMP;
import ch.ffhs.inf09.pa.mashup_platform.config.Config;
import ch.ffhs.inf09.pa.mashup_platform.core.PlatformResetter;

public class ConfigDBTest 
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
			+ "/config/db/DBFinnishBands.properties";	
		ConfigMashup config = new ConfigDB(filepath);		
		assertNotNull(config);
		assertNotNull(config.properties);
		assertEquals("http://en.wikipedia.org/wiki/List_of_bands_from_Finland", config.getValue("URL"));
	}
	
	@Test(expected=ExceptionMP.class)
    public void testWrongFilepathException() throws ExceptionMP {
		new ConfigDB("wrongFilepath");
    }	
}