package ch.ffhs.inf09.pa.mashup_platform.common.db;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import ch.ffhs.inf09.pa.mashup_platform.core.system.model.Content;

/**
 * This is a test class for the class MashupPage.
 * 
 * @author Alexander
 * 
 */
public class MashupPageTest {

	@Test
	public void mainTest() {
		// initialize test variables
		String name = "name";
		String ident = "ident";
		int pageNr = 1;
		Date creationDate = new Date();
		Date lastUpdated = new Date();
		String username = "username";
		Content content = new Content();

		// create MashupPage instance
		MashupPage page = new MashupPage();
		page.setCreatedAt(creationDate);
		page.setContent(content);
		page.setMashupIdent(ident);
		page.setName(name);
		page.setPageNr(pageNr);
		page.setUsername(username);
		page.setLastUpdated(lastUpdated);

		// check values
		assertEquals(creationDate, page.getCreatedAt());
		assertEquals(content, page.getContent());
		assertEquals(ident, page.getMashupIdent());
		assertEquals(name, page.getName());
		assertEquals(pageNr, page.getPageNr());
		assertEquals(username, page.getUsername());
		assertEquals(lastUpdated, page.getLastUpdated());
	}
}
