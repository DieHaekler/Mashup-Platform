package ch.ffhs.inf09.pa.mashup_platform.core.system.model.fetcher.flickr;

import static org.junit.Assert.*;

import org.junit.Test;


/**
 * This is a test class for the class ResultFlickr.
 * 
 * @author Alexander
 * 
 */
public class ResultFlickrTest {

	@Test
	public void mainTest() {
		// initialize test variables
		String published = "2012/12/11";
		String url = "url";
		String imgURL = "imgURL";
		String tags = "tags";

		// create new ResultFlickr instance and set values
		ResultFlickr result = new ResultFlickr();
		result.setImgURL(imgURL);
		result.setURL(url);
		result.setPublished(published);
		result.setTags(tags);

		// check values
		assertEquals(url, result.getURL());
		assertEquals(imgURL, result.getImgURL());
		assertEquals(published, result.getPublished());
		assertEquals(tags, result.getTags());
	}
}