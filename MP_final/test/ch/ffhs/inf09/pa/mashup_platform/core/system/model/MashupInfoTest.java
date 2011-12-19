package ch.ffhs.inf09.pa.mashup_platform.core.system.model;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.util.*;

import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.*;
import ch.ffhs.inf09.pa.mashup_platform.core.PlatformResetter;
import ch.ffhs.inf09.pa.mashup_platform.core.system.config.ConfigMashup;
import ch.ffhs.inf09.pa.mashup_platform.core.system.controller.Controller;

public class MashupInfoTest
{
	private String testFolder = "portrait_of_finnish_bands";
	private int nextPageNr = 2;
	
	@BeforeClass
    public static void setUpBeforeClass() {
       PlatformResetter.main(null);
    }

    @AfterClass
    public static void tearDownAfterClass() {
        PlatformResetter.main(null);
    }
	
	@Test
	public void mainTest() throws ExceptionMP, NumberFormatException, FileNotFoundException, IOException, InterruptedException{	
		String filepath = Config.getFilepathVar() + "/" + testFolder + "/config/config.properties";
		MashupInfo info = new MashupInfo(filepath);
		assertNotNull(info);
		ConfigMashup config = new ConfigMashup(filepath);
		assertEquals(config.getValue("IDENT"), info.getIdent());
		assertEquals(config.getValue("MAX_NUMBER_PAGES"),Integer.toString(info.getMaxNumberPages()));
		String filepathStatus = Config.getFilepathSystem() + "/output/status/" + testFolder
				+ "_processed.status";
		long lastProcessed = FileMP.getTimestamp(filepathStatus);
		assertEquals(lastProcessed, info.getLastProcessed());
		//int pageNrProcessed = Integer.parseInt(FileMP.getContent(filepathStatus).trim());
		//assertEquals(pageNrProcessed, info.getPageNrProcessed());
		assertEquals(0, info.getStatus());
		info.setPageNrProcessed(nextPageNr);
		assertEquals(nextPageNr, info.getPageNrProcessed());
		
		//Properties properties = new Properties();
		
		//properties.load(new FileInputStream(filepath));

		//int status = Integer.parseInt(properties.getProperty(Model.PARAM_STATUS).trim());
		
		
		
		
		/*IDENT = portrait_of_finnish_bands
				NAME = Portrait of Finnish bands
				USERNAME = Die Häkler
				CREATED_AT = 03.12.2011
				NUMBER_RECORDS_PER_PAGE = 3
				MAX_NUMBER_PAGES = 10*/
		/*if (mashupInfo != null)
		{
			int max = mashupInfo.getMaxNumberPages();
			int pagenr = (mashupInfo.getPageNrProcessed() + 1) % max;
			mashupInfo.setPageNrProcessed(pagenr);
			String ident = mashupInfo.getIdent();
	}*/
	}
	
	@Test(expected=ExceptionMP.class)
    public void testWrongFilepathException() throws ExceptionMP {
		new MashupInfo("wrongFilepath");
    }	
	
	
	
	
	
	
}