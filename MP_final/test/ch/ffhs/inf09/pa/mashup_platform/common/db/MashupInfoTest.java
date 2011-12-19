package ch.ffhs.inf09.pa.mashup_platform.common.db;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;


public class MashupInfoTest {

	private String ident = "ident";
	private int numberPages = 1;
	private Date creationDate = new Date();
	private Date lastUpdated = new Date();
	private String name = "name";
	private String username = "username";
	
	@Test
	public void mainTest(){
		MashupInfo info = new MashupInfo(ident);
		info.setCreatedAt(creationDate);
		info.setName(name);
		info.setNumberPages(numberPages);
		info.setUsername(username);
		info.setLastUpdated(lastUpdated);
		
		assertEquals(ident, info.getMashupIdent());
		assertEquals(creationDate, info.getCreatedAt());
		assertEquals(name, info.getName());
		assertEquals(numberPages, info.getNumberPages());
		assertEquals(username, info.getUsername());
		assertEquals(lastUpdated, info.getLastUpdated());				
	}	
	
}
