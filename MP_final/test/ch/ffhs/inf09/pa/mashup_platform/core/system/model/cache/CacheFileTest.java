package ch.ffhs.inf09.pa.mashup_platform.core.system.model.cache;

import ch.ffhs.inf09.pa.mashup_platform.core.PlatformResetter;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.Content;

import java.io.IOException;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * This is a test class for the class CacheFile.
 * 
 * @author Alexander
 * 
 */
public class CacheFileTest {
	@BeforeClass
	public static void setUpBeforeClass() {
		PlatformResetter.main(null);
	}

	@AfterClass
	public static void tearDownAfterClass() {
		PlatformResetter.main(null);
	}

	@Test
	public void mainTest() throws IOException, InterruptedException {
		String ident = "Portrait of Finnish Bands";

		// create new CacheFile instance
		CacheFile file = new CacheFile();

		Content content = new Content();
		content.setCaption(ident);
		file.put(ident, content);
		assertNull(file.get("wrongIdent"));

		// deserialize Content instance and compare idents
		Content con = (Content) file.get(ident);
		assertEquals(content.getCaption(), con.getCaption());
		Thread.sleep(2000);

		// check if the cache entry is returned with a higer maximum age than 2
		// seconds
		con = (Content) file.getRecord(ident, 3);
		assertNotNull(con);

		// check if no cache entry is returned with a lower maximum age than 2
		// seconds
		con = (Content) file.getRecord(ident, 1);
		assertNull(con);
		
		// check with wrong maximum age
		assertNull(file.getRecord(ident, -1));
	}
}