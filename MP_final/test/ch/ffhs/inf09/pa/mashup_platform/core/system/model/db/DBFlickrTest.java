package ch.ffhs.inf09.pa.mashup_platform.core.system.model.db;

import java.util.*;

import ch.ffhs.inf09.pa.mashup_platform.core.PlatformResetter;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.*;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.fetcher.flickr.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.Config;
import ch.ffhs.inf09.pa.mashup_platform.var.portrait_of_finnish_bands.model.db.DBFinnishBands;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class DBFlickrTest 
{
	
	private String keyWord1 = "22-Pistepirkko";
	private String keyWord2 = "band";
	
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
		DBFlickr dbFlickr = new DBFlickr(Config.getFilepathSystem()
				+ "/config/system/db/DBFlickr.properties");
		
		Content content = new Content();
		content.setCaption(keyWord1);
		content.addKeyword(content.getCaption());
		content.addKeyword(keyWord2);
		

		assertEquals("{\"caption\":\"22-Pistepirkko\",\"keywords\":[\"22-Pistepirkko\",\"band\"]}",content.getJSON());
		dbFlickr.fillIn(content, 0, 2);
		assertNotNull(content.getSection(DBFlickr.SECTION_IDENT));
		assertEquals(2, content.getSection(DBFlickr.SECTION_IDENT).getParts().size());
		assertEquals("{\"caption\":\"22-Pistepirkko\",\"keywords\":[\"22-Pistepirkko\",\"band\"],\"flickr\":{\"parts\":[{\"imgURL\":\"http://farm3.staticflickr.com/2106/5706206033_30d800f7bc_m.jpg\",\"url\":\"http://www.flickr.com/photos/pjotrpetronov/5706206033/\",\"keywords\":[\"city\",\"urban\",\"finland\",\"helsinki\",\"kallio\",\"band\",\"youandme\",\"youme\",\"22pistepirkko\",\"pistepirkko\"]},{\"imgURL\":\"http://farm4.staticflickr.com/3328/5706770706_299f505850_m.jpg\",\"url\":\"http://www.flickr.com/photos/pjotrpetronov/5706770706/\",\"keywords\":[\"city\",\"urban\",\"finland\",\"helsinki\",\"kallio\",\"band\",\"youandme\",\"youme\",\"22pistepirkko\",\"pistepirkko\"]}]}}", content.getJSON());	
	}
	
	@Test(expected=ExceptionMP.class)
    public void testWrongFilepathException() throws ExceptionMP {
		new DBFlickr("wrongFilepath");
    }	
}