package ch.ffhs.inf09.pa.mashup_platform.core.system.model.cache;

import ch.ffhs.inf09.pa.mashup_platform.config.*;
import ch.ffhs.inf09.pa.mashup_platform.core.PlatformResetter;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.Content;

import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import java.io.IOException;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class CacheFileTest 
{
	private String ident = "Portrait of Finnish Bands";
	
	@BeforeClass
    public static void setUpBeforeClass() {
       PlatformResetter.main(null);
    }

    @AfterClass
    public static void tearDownAfterClass() {
        PlatformResetter.main(null);
    }
	
	@Test
	public void mainTest() throws IOException, InterruptedException{
		CacheFile file = new CacheFile();
		long now = System.currentTimeMillis();
		Content content = new Content();
		content.setCaption(ident);
		file.put(ident, content);
		Content con = (Content)file.get(ident);
		assertEquals(content.getCaption(), con.getCaption());
		assertNull(file.get("wrongIdent"));
		//assertTrue(file.getTimestamp(ident)>now);
		Thread.sleep(2000);
		Content cont = (Content)file.getRecord(ident, 1);
		assertNull(cont);
		cont = (Content)file.getRecord(ident, 3);
		assertNull(file.getRecord(ident, -1));
		
	}
}