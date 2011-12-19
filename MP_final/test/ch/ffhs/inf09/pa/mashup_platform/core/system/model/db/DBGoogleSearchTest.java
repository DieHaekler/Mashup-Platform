package ch.ffhs.inf09.pa.mashup_platform.core.system.model.db;

import java.util.*;

import ch.ffhs.inf09.pa.mashup_platform.core.PlatformResetter;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.*;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.fetcher.google_search.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.Config;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class DBGoogleSearchTest
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
		DBGoogleSearch dbGoogleSearch = new DBGoogleSearch(Config.getFilepathSystem()
				+ "/config/system/db/DBGoogleSearch.properties");
		
		Content content = new Content();
		content.setCaption(keyWord1);
		content.addKeyword(content.getCaption());
		content.addKeyword(keyWord2);
		

		assertEquals("{\"caption\":\"22-Pistepirkko\",\"keywords\":[\"22-Pistepirkko\",\"band\"]}",content.getJSON());
		dbGoogleSearch.fillIn(content, 0, 2);
		assertNotNull(content.getSection(DBGoogleSearch.SECTION_IDENT));
		assertEquals(2, content.getSection(DBGoogleSearch.SECTION_IDENT).getParts().size());
		assertEquals("{\"caption\":\"22-Pistepirkko\",\"keywords\":[\"22-Pistepirkko\",\"band\"],\"google_search\":{\"parts\":[{\"caption\":\"<b>22</b>-<b>Pistepirkko</b> - Wikipedia, the free encyclopedia\",\"body\":\"<b>22</b>-<b>Pistepirkko</b> is a Finnish popular music <b>band</b> formed in 1980. It was formed in a   small rural village of Utajärvi in Northern Finland but moved to Helsinki, the <b>...</b>\",\"url\":\"http://en.wikipedia.org/wiki/22-Pistepirkko\"},{\"caption\":\"Eleven (<b>22</b>-<b>Pistepirkko</b> album) - Wikipedia, the free encyclopedia\",\"body\":\"Released, 1998. <b>22</b>-<b>Pistepirkko</b> chronology <b>...</b> Eleven (also known as Ele∀en) is   a music album by the Finnish <b>band 22</b>-<b>Pistepirkko</b>. It was released in 1998. <b>...</b>\",\"url\":\"http://en.wikipedia.org/wiki/Eleven_(22-Pistepirkko_album)\"}]}}", content.getJSON());
	}

	@Test(expected=ExceptionMP.class)
    public void testWrongFilepathException() throws ExceptionMP {
		new DBGoogleSearch("wrongFilepath");
    }	
}