package ch.ffhs.inf09.pa.mashup_platform.core.system.model.fetcher.google_search;

import static org.junit.Assert.*;

import org.junit.Test;

public class ResultGoogleSearchTest
{
	@Test
	public void mainTest(){
		String content = "Content";
		String title = "title";
		String url = "url";
	
		ResultGoogleSearch result = new ResultGoogleSearch();
		result.setContent(content);
		result.setTitle(title);
		result.setURL(url);
		assertEquals(content, result.getContent());
		assertEquals(title, result.getTitle());
		assertEquals(url, result.getURL());		
	}

}