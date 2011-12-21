package ch.ffhs.inf09.pa.mashup_platform.common.db;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

/**
 * This is a test class for the class MashupOverview.
 * 
 * @author Alexander
 * 
 */
public class MashupOverviewTest {

	@Test
	public void mainTest() {
		// initialize test variables
		String name = "name";
		String ident = "ident";
		String username = "username";
		Date createdAt = new Date();
		Date lastUpdated = new Date();
		int numberPages = 1;

		// create MashupInfo instance
		MashupInfo info = new MashupInfo(ident);
		info.setName(name);
		info.setUsername(username);
		info.setCreatedAt(createdAt);
		info.setLastUpdated(lastUpdated);
		info.setNumberPages(numberPages);

		// create MashupOverview instance
		MashupOverview overview = new MashupOverview(
				MashupOverview.SORTED_BY_DATE_ASC);
		overview.add(info);

		// check values
		assertEquals(MashupOverview.SORTED_BY_DATE_ASC, overview.getSortedBy());
		assertEquals(1, overview.getList().size());
		assertEquals(overview.getList().get(0), info);
		DateFormat df1 = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		DateFormat df2 = new SimpleDateFormat("HH:mm:ss");
		assertEquals("{\"mashups\":[{\"id\":\"" + ident + "\",\"name\":\""
				+ name + "\",\"username\":\"" + username
				+ "\",\"lastUpdated\":\"" + df1.format(lastUpdated)
				+ "\",\"createdAt\":\"" + df2.format(createdAt)
				+ "\",\"numberPages\":\"" + numberPages + "\"}]}",
				overview.getJSON());
	}
}
