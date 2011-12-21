package ch.ffhs.inf09.pa.mashup_platform.common.db;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.ffhs.inf09.pa.mashup_platform.common.util.ExceptionMP;
import ch.ffhs.inf09.pa.mashup_platform.config.Config;
import ch.ffhs.inf09.pa.mashup_platform.core.PlatformResetter;

/**
 * This is a test class for the class DBOrient.
 * 
 * @author Alexander
 * 
 */
public class DBOrientTest {

	@BeforeClass
	public static void setUpBeforeClass() {
		PlatformResetter.main(null);
	}

	@AfterClass
	public static void tearDownAfterClass() {
		PlatformResetter.main(null);
	}

	@Test
	public void mainTest() throws ExceptionMP, InterruptedException {
		// initialize test variables
		Config config = Config.getInstance();
		String username = config.getValue(Config.PARAM_DB_USERNAME);
		String password = config.getValue(Config.PARAM_DB_PASSWORD);
		String newPassword = "newPassword";
		String mashupIdent = "ident";
		String mashupName = "name";
		String mashupUsername = "username";
		Date date = new Date();
		int numberPages = 5;

		// set up database
		DBOrient dbOrient = new DBOrient(username, password);

		for (int i = 0; i < numberPages; i++) {

			MashupPage page = new MashupPage();
			page.setMashupIdent(mashupIdent);
			page.setName(mashupName);
			page.setUsername(mashupUsername);
			page.setLastUpdated(date);
			page.setCreatedAt(date);
			page.setPageNr(i + 1);
			dbOrient.setPage(page);

			// compare the values of the page with the values of the database
			assertEquals(page.getMashupIdent(),
					dbOrient.getPage(mashupIdent, i + 1).getMashupIdent());
			assertEquals(page.getName(), dbOrient.getPage(mashupIdent, i + 1)
					.getName());
			assertEquals(page.getUsername(),
					dbOrient.getPage(mashupIdent, i + 1).getUsername());
			assertEquals(page.getLastUpdated(),
					dbOrient.getPage(mashupIdent, i + 1).getLastUpdated());
			assertEquals(page.getCreatedAt(),
					dbOrient.getPage(mashupIdent, i + 1).getCreatedAt());
			assertEquals(page.getPageNr(), dbOrient.getPage(mashupIdent, i + 1)
					.getPageNr());
		}

		// check existing user
		assertEquals(username, dbOrient.dbUsername);
		assertEquals(password, dbOrient.dbPassword);
		assertNotNull(dbOrient.getUser(username, password));

		// create new user
		User user = new User();
		user.setUsername(username);
		user.setPassword(newPassword);
		dbOrient.setUser(user);
		assertNotNull(dbOrient.getUser(username, newPassword));
		assertNull(dbOrient.getUser(username, password));

		// check MashupInfo values in the database
		MashupInfo info = dbOrient.getInfo(mashupIdent);
		assertNotNull(info);
		assertEquals(mashupIdent, info.getMashupIdent());
		assertEquals(mashupName, info.getName());
		assertEquals(mashupUsername, info.getUsername());
		assertEquals(date, info.getCreatedAt());
		assertEquals(date, info.getLastUpdated());
		assertEquals(numberPages, info.getNumberPages());

		// check MashupOverview values in the database
		MashupOverview overview = dbOrient.getOverview(0, 1);
		assertEquals(MashupOverview.SORTED_BY_NAME_ASC, overview.getSortedBy());
		assertEquals(1, overview.getList().size());
		assertEquals(mashupIdent, overview.getList().get(0).getMashupIdent());

		dbOrient.close();
	}

}
