package ch.ffhs.inf09.pa.mashup_platform.core.system.model.fetcher.google_search;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This is a test class for the class ResultGoogleSearch.
 * 
 * @author Alexander
 * 
 */
public class ResultGoogleSearchTest {
	@Test
	public void mainTest() {
		// initialize test variables
		String content = "Content";
		String title = "title";
		String url = "url";

		// create new ResultFlickr instance and set values
		ResultGoogleSearch result = new ResultGoogleSearch();
		result.setContent(content);
		result.setTitle(title);
		result.setURL(url);

		// check values
		assertEquals(content, result.getContent());
		assertEquals(title, result.getTitle());
		assertEquals(url, result.getURL());
	}

}