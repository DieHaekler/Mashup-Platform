package ch.ffhs.inf09.pa.mashup_platform.var.portrait_of_finnish_bands.model.db;

import ch.ffhs.inf09.pa.mashup_platform.core.PlatformResetter;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.db.*;

import ch.ffhs.inf09.pa.mashup_platform.core.system.model.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.Config;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * This is a test class for the class DBPortraitOfFinnishBands.
 * 
 * @author Alexander
 * 
 */
public class DBPortraitOfFinnishBandsTest {
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
		// initialize test variables
		int start = 0;
		int numberRecordsPerPage = 2;
		Content content = new Content();
		content.setCaption("Portrait of Finnish Bands");
		String filepath = Config.getFilepathVar()
				+ "/portrait_of_finnish_bands/config/db/DBFinnishBands.properties";

		// create new DBPortraitOfFinnishBands instance
		DB db = new DBPortraitOfFinnishBands(filepath);

		// check if the content is only filled with the caption
		assertEquals("{\"caption\":\"Portrait of Finnish Bands\"}",
				content.getJSON());

		// fill in
		db.fillIn(content, start, numberRecordsPerPage);

		// check if the content is filled now
		assertNotNull(content.getSection(DBFinnishBands.SECTION_IDENT));
		assertEquals(2, content.getSection(DBFinnishBands.SECTION_IDENT)
				.getParts().size());
		assertEquals(
				"{\"caption\":\"Portrait of Finnish Bands\",\"finnish_bands\":{\"parts\":[{\"caption\":\"22-Pistepirkko\",\"keywords\":[\"22-Pistepirkko\",\"finnish\",\"band\"],\"flickr\":{\"parts\":[{\"imgURL\":\"http://farm3.staticflickr.com/2106/5706206033_30d800f7bc_m.jpg\",\"url\":\"http://www.flickr.com/photos/pjotrpetronov/5706206033/\",\"keywords\":[\"city\",\"urban\",\"finland\",\"helsinki\",\"kallio\",\"band\",\"youandme\",\"youme\",\"22pistepirkko\",\"pistepirkko\"]},{\"imgURL\":\"http://farm4.staticflickr.com/3328/5706770706_299f505850_m.jpg\",\"url\":\"http://www.flickr.com/photos/pjotrpetronov/5706770706/\",\"keywords\":[\"city\",\"urban\",\"finland\",\"helsinki\",\"kallio\",\"band\",\"youandme\",\"youme\",\"22pistepirkko\",\"pistepirkko\"]},{\"imgURL\":\"http://farm6.staticflickr.com/5255/5550144638_6356311b56_m.jpg\",\"url\":\"http://www.flickr.com/photos/elmah/5550144638/\",\"keywords\":[\"music\",\"sepia\",\"band\",\"singer\",\"22pistepirkko\"]}]},\"google_search\":{\"parts\":[{\"caption\":\"<b>22</b>-<b>Pistepirkko</b> - Wikipedia, the free encyclopedia\",\"body\":\"<b>22</b>-<b>Pistepirkko</b> is a <b>Finnish</b> popular music <b>band</b> formed in 1980. It was formed in a   small rural village of Utajärvi in Northern <b>Finland</b> but moved to Helsinki, the <b>...</b>\",\"url\":\"http://en.wikipedia.org/wiki/22-Pistepirkko\"},{\"caption\":\"Eleven (<b>22</b>-<b>Pistepirkko</b> album) - Wikipedia, the free encyclopedia\",\"body\":\"Released, 1998. <b>22</b>-<b>Pistepirkko</b> chronology <b>...</b> Eleven (also known as Ele∀en) is   a music album by the <b>Finnish band 22</b>-<b>Pistepirkko</b>. It was released in 1998. <b>...</b>\",\"url\":\"http://en.wikipedia.org/wiki/Eleven_(22-Pistepirkko_album)\"},{\"caption\":\"<b>22</b>-<b>Pistepirkko</b> – Free listening, videos, concerts, stats, &amp; pictures at <b>...</b>\",\"body\":\"<b>22</b>-<b>Pistepirkko</b> is a <b>Finnish</b> popular music <b>band</b> formed in 1980 at Utajärvi,   <b>Finland</b>. The name “<b>22</b>-<b>Pistepirkko</b>” means a 22-spotted ladybug. The three   members <b>...</b>\",\"url\":\"http://www.last.fm/music/22-Pistepirkko\"},{\"caption\":\"<b>22 Pistepirkko</b> - This Time - YouTube\",\"body\":\"Aug 11, 2008 <b>...</b> <b>Band</b>: <b>22 Pistepirkko</b> Song: This Time Directed: Mika Taanila (2001) <b>...</b> Its   recorded from <b>finnish</b> Voice TV and &quot;phallic thing&quot; ;) is the sign﻿ for <b>...</b>\",\"url\":\"http://www.youtube.com/watch%3Fv%3Dr-oiX3iIpyM\"}]}},{\"caption\":\"The 69 Eyes\",\"keywords\":[\"The 69 Eyes\",\"finnish\",\"band\"],\"flickr\":{\"parts\":[{\"imgURL\":\"http://farm5.staticflickr.com/4058/4640781356_18ff568f36_m.jpg\",\"url\":\"http://www.flickr.com/photos/carolfreddi/4640781356/\",\"keywords\":[\"show\",\"blue\",\"light\",\"concert\",\"helsinki\",\"guitar\",\"devils\",\"band\",\"drummer\",\"vampires\",\"vampiros\",\"jussi\",\"finlandia\",\"baterista\",\"the69eyes\"]},{\"imgURL\":\"http://farm3.staticflickr.com/2728/4084455071_039707ffd5_m.jpg\",\"url\":\"http://www.flickr.com/photos/29817599@N05/4084455071/\",\"keywords\":[\"ohio\",\"concert\",\"cleveland\",\"band\",\"drummer\",\"peabodys\",\"the69eyes\",\"nikond60\",\"jussi69\"]},{\"imgURL\":\"http://farm3.staticflickr.com/2529/4085212324_f01d65b8a6_m.jpg\",\"url\":\"http://www.flickr.com/photos/29817599@N05/4085212324/\",\"keywords\":[\"ohio\",\"concert\",\"cleveland\",\"band\",\"leadsinger\",\"jyrki69\",\"peabodys\",\"the69eyes\",\"nikond60\"]}]},\"google_search\":{\"parts\":[{\"caption\":\"<b>The 69 Eyes</b> † Official website\",\"body\":\"Jun 7, 2011 <b>...</b> The <b>band</b> still plays some shows during the summer before heading to <b>...</b> winning   summer show on Radio Rock with other <b>Finnish</b> rock stars. <b>...</b>\",\"url\":\"http://www.69eyes.com/\"},{\"caption\":\"<b>The 69 Eyes</b> - Wikipedia, the free encyclopedia\",\"body\":\"Formed in the bars of Helsinki, <b>Finland</b> in the summer of 1989 by <b>...</b> of Syyskuu),   Timo-Timo, Lotto and Bazie; <b>The 69 Eyes</b> originally <b>...</b>\",\"url\":\"http://en.wikipedia.org/wiki/The_69_Eyes\"},{\"caption\":\"<b>The 69 Eyes</b> at Metal from <b>Finland</b>\",\"body\":\"Sonic Shocks conducted an interview with singer Jyrki 69 of <b>Finnish</b> goth   metallers <b>THE 69 EYES</b> when the <b>band</b> played in Norwich, England on March 25,   2011 <b>...</b>\",\"url\":\"http://www.metalfromfinland.com/The%2B69%2BEyes\"},{\"caption\":\"<b>The 69 Eyes</b> could be your next Halloween <b>band</b>\",\"body\":\"<b>The 69 Eyes</b> could be your next Halloween <b>band</b>. By Feature Writer | Published:   October 24, 2009. <b>Finnish</b> rockers <b>The 69 Eyes</b> have been around since the <b>...</b>\",\"url\":\"http://globalcomment.com/2009/the-69-eyes-could-be-your-next-halloween-band/\"}]}}]}}",
				content.getJSON());
	}

	@Test(expected = ExceptionMP.class)
	public void testWrongFilepathException() throws ExceptionMP {
		new DBPortraitOfFinnishBands("wrongFilepath");
	}
}