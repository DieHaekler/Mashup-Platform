package ch.ffhs.inf09.pa.mashup_platform.common.db;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

/**
 * This is a test class for the class MashupInfo.
 * 
 * @author Alexander
 * 
 */
public class MashupInfoTest {

	@Test
	public void mainTest() {
		// initialize test variables
		String ident = "ident";
		int numberPages = 1;
		Date creationDate = new Date();
		Date lastUpdated = new Date();
		String name = "name";
		String username = "username";

		// create MashupInfo instance
		MashupInfo info = new MashupInfo(ident);
		info.setCreatedAt(creationDate);
		info.setName(name);
		info.setNumberPages(numberPages);
		info.setUsername(username);
		info.setLastUpdated(lastUpdated);

		// check values
		assertEquals(ident, info.getMashupIdent());
		assertEquals(creationDate, info.getCreatedAt());
		assertEquals(name, info.getName());
		assertEquals(numberPages, info.getNumberPages());
		assertEquals(username, info.getUsername());
		assertEquals(lastUpdated, info.getLastUpdated());
	}

}
