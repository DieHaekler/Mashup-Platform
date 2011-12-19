package ch.ffhs.inf09.pa.mashup_platform.common.db;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import ch.ffhs.inf09.pa.mashup_platform.core.system.model.Content;

public class MashupPageTest {

	private String name = "name";
	private String ident = "ident";
	private int pageNr = 1;
	private Date creationDate = new Date();
	private Date lastUpdated = new Date();
	private String username = "username";
	private Content content = new Content();
	
	@Test
	public void mainTest(){
		MashupPage page = new MashupPage();
		page.setCreatedAt(creationDate);
		page.setContent(content);
		page.setMashupIdent(ident);
		page.setName(name);
		page.setPageNr(pageNr);
		page.setUsername(username);
		page.setLastUpdated(lastUpdated);
		
		assertEquals(creationDate, page.getCreatedAt());
		assertEquals(content, page.getContent());
		assertEquals(ident, page.getMashupIdent());
		assertEquals(name, page.getName());
		assertEquals(pageNr, page.getPageNr());
		assertEquals(username, page.getUsername());
		assertEquals(lastUpdated, page.getLastUpdated());
	}	
}
