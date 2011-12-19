package ch.ffhs.inf09.pa.mashup_platform.core.system.model.fetcher.flickr;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.ffhs.inf09.pa.mashup_platform.core.PlatformResetter;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.fetcher.google_search.ResultGoogleSearch;

public class ResultFlickrTest
{
	
	@Test
	public void mainTest(){
		String published = "2012/12/11";
		String url = "url";
		String imgURL = "imgURL";
		String tags = "tags";
	
		ResultFlickr result = new ResultFlickr();
		result.setImgURL(imgURL);
		result.setURL(url);
		result.setPublished(published);
		result.setTags(tags);
		assertEquals(url, result.getURL());	
		assertEquals(imgURL, result.getImgURL());	
		assertEquals(published, result.getPublished());	
		assertEquals(tags, result.getTags());	
	}
}