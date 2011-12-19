package ch.ffhs.inf09.pa.mashup_platform.var.portrait_of_finnish_bands.model.db;

import ch.ffhs.inf09.pa.mashup_platform.core.PlatformResetter;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.db.*;

import ch.ffhs.inf09.pa.mashup_platform.core.system.model.*;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.fetcher.*;
import java.util.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.Config;
import ch.ffhs.inf09.pa.mashup_platform.core.system.config.*;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class DBFinnishBandsTest
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
	public void mainTest() throws ExceptionMP{
		int start = 0;
		int number = 2;
		
		DBFinnishBands dbFinnishBands = new DBFinnishBands(Config.getFilepathVar()
				+ "/portrait_of_finnish_bands/config/db/DBFinnishBands.properties");
		
		Content content = new Content();
		content.setCaption("Portrait of Finnish Bands");

		assertEquals("{\"caption\":\"Portrait of Finnish Bands\"}",content.getJSON());
		dbFinnishBands.fillIn(content, start, number);
		assertNotNull(content.getSection(DBFinnishBands.SECTION_IDENT));
		assertEquals(2, content.getSection(DBFinnishBands.SECTION_IDENT).getParts().size());
		assertEquals("{\"caption\":\"Portrait of Finnish Bands\",\"finnish_bands\":{\"parts\":[{\"caption\":\"22-Pistepirkko\"},{\"caption\":\"The 69 Eyes\"}]}}", content.getJSON());
	}	
}