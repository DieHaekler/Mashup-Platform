package ch.ffhs.inf09.pa.mashup_platform.core.system.model.fetcher.flickr;

import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.Config;

import ch.ffhs.inf09.pa.mashup_platform.core.PlatformResetter;
import ch.ffhs.inf09.pa.mashup_platform.core.system.config.ConfigDB;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.db.DBFlickr;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.fetcher.google_search.FetcherGoogleSearch;

import java.util.*;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * This is a test class for the class FetcherFlickr.
 * 
 * @author Alexander
 * 
 */
public class FetcherFlickrTest {

	@BeforeClass
	public static void setUpBeforeClass() {
		PlatformResetter.main(null);
	}

	@AfterClass
	public static void tearDownAfterClass() {
		PlatformResetter.main(null);
	}

	@Test
	public void mainTest() throws ExceptionMP, InterruptedException {
		// initialize key words
		ArrayList<String> keyWords = new ArrayList<String>(Arrays.asList(
				"22-Pistepirkko", "band"));

		// get file path and URL API
		String filepath = Config.getFilepathSystem()
				+ "/config/system/db/DBFlickr.properties";
		ConfigDB config = new ConfigDB(filepath);
		String urlAPI = config.getValue(DBFlickr.PARAM_URL_API);

		// fetch the results
		ArrayList<ResultFlickr> results = FetcherFlickr.fetchResults(urlAPI,
				keyWords);

		// check if the results are filled
		assertEquals(20, results.size());
		for (ResultFlickr result : results) {
			assertNotNull(result.getPublished());
			assertNotNull(result.getURL());
			assertNotNull(result.getTags());
			assertNotNull(result.getImgURL());
		}
	}

	@Test(expected = ExceptionMP.class)
	public void wrongQueryTest() throws ExceptionMP {
		FetcherGoogleSearch.fetchResults("WrongQuery", new ArrayList<String>());
	}
}