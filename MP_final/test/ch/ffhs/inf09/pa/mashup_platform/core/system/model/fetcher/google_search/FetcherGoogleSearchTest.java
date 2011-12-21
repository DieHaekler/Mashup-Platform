package ch.ffhs.inf09.pa.mashup_platform.core.system.model.fetcher.google_search;

import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.Config;

import ch.ffhs.inf09.pa.mashup_platform.core.PlatformResetter;
import ch.ffhs.inf09.pa.mashup_platform.core.system.config.ConfigDB;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.db.DBGoogleSearch;
import java.util.*;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * This is a test class for the class FetcherGoogleSearch.
 * 
 * @author Alexander
 * 
 */
public class FetcherGoogleSearchTest {
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
				"Ajattara", "finnish", "band"));

		// get file path and URL API
		String filepath = Config.getFilepathSystem()
				+ "/config/system/db/DBGoogleSearch.properties";
		ConfigDB config = new ConfigDB(filepath);
		String urlAPI = config.getValue(DBGoogleSearch.PARAM_URL_API);

		// fetch the results
		ArrayList<ResultGoogleSearch> results = FetcherGoogleSearch
				.fetchResults(urlAPI, keyWords);

		// check if the results are filled
		assertEquals(4, results.size());
		for (ResultGoogleSearch result : results) {
			assertNotNull(result.getContent());
			assertNotNull(result.getTitle());
			assertNotNull(result.getURL());
		}
	}

	@Test(expected = ExceptionMP.class)
	public void wrongQueryTest() throws ExceptionMP {
		FetcherGoogleSearch.fetchResults("WrongQuery", new ArrayList<String>());
	}

}