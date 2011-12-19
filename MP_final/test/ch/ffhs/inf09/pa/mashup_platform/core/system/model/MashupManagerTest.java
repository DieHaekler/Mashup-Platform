package ch.ffhs.inf09.pa.mashup_platform.core.system.model;

import static org.junit.Assert.*;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;
import java.io.*;

import ch.ffhs.inf09.pa.mashup_platform.common.db.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.*;
import ch.ffhs.inf09.pa.mashup_platform.core.*;

public class MashupManagerTest
{
	private String ident = "portrait_of_finnish_bands";
	
	@BeforeClass
    public static void setUpBeforeClass() {
       PlatformResetter.main(null);
    }

    @AfterClass
    public static void tearDownAfterClass() {
        PlatformResetter.main(null);
    }
	
	@Test
	public void mainTest() throws ExceptionMP, InterruptedException, ClassNotFoundException, IOException{	
		String filepath = Config.getFilepathVar() + "/" + ident + "/config/config.properties";
		MashupInfo info = new MashupInfo(filepath);
		info.setPageNrProcessed(1);
		info = new MashupInfo(filepath);
		Thread.sleep(2000);
		assertNull(MashupManager.pick(3));
		assertNotNull(MashupManager.pick(1));
		MashupInfo mashupInfo = MashupManager.pick(0);
		assertEquals(info.getIdent(), mashupInfo.getIdent());
		assertEquals(info.getLastProcessed(), mashupInfo.getLastProcessed());
		
		MashupPage page = new MashupPage();
		page.setContent(new Content());
		page.setMashupIdent(ident);
		
		
		String folderpathOutput = Config.getFilepathSystem() + "output/";
		page.setLastUpdated(new Date());
		Content content = page.getContent();
		String hash = content.getHashCode();
		String filename = page.getMashupIdent() + "_" + page.getPageNr() + "_" + hash + ".output";
		String filepathOutput = folderpathOutput + filename;		
		//assertFalse(FileMP.exists(filepathOutput));
		MashupManager.store(page);
		assertTrue(FileMP.exists(filepathOutput));
		MashupPage mp = (MashupPage)FileMP.get(filepathOutput);
		assertEquals(ident,mp.getMashupIdent());
		
		
		
	}
}