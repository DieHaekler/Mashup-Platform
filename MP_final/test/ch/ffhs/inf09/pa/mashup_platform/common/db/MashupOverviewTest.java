package ch.ffhs.inf09.pa.mashup_platform.common.db;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;


public class MashupOverviewTest {

	private String name = "name";
	private String ident = "ident";
	private String username = "username";
	private Date createdAt = new Date();
	private Date lastUpdated = new Date();
	private int numberPages = 1;
	
	@Test
	public void mainTest(){
		MashupOverview overview = new MashupOverview(MashupOverview.SORTED_BY_DATE_ASC);
		MashupInfo info = new MashupInfo(ident);
		info.setName(name);
		info.setUsername(username);
		info.setCreatedAt(createdAt);
		info.setLastUpdated(lastUpdated);
		info.setNumberPages(numberPages);
		overview.add(info);
				
		assertEquals(MashupOverview.SORTED_BY_DATE_ASC, overview.getSortedBy());
		assertEquals(1, overview.getList().size());
		assertEquals(overview.getList().get(0), info);	
		DateFormat df1 = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		DateFormat df2 = new SimpleDateFormat("HH:mm:ss");
		assertEquals("{\"mashups\":[{\"id\":\"" + ident + "\",\"name\":\"" + name + "\",\"username\":\"" + username + "\",\"lastUpdated\":\"" + df1.format(lastUpdated) + "\",\"createdAt\":\"" + df2.format(createdAt) + "\",\"numberPages\":\"" + numberPages + "\"}]}", overview.getJSON());		
	}	
}

