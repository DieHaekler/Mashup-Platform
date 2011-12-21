package ch.ffhs.inf09.pa.mashup_platform.var.portrait_of_finnish_bands.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ch.ffhs.inf09.pa.mashup_platform.core.PlatformResetter;
import ch.ffhs.inf09.pa.mashup_platform.core.system.config.*;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.*;
import ch.ffhs.inf09.pa.mashup_platform.common.db.MashupPage;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.*;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * This is a test class for the class ModelMain.
 * 
 * @author Alexander
 * 
 */
public class ModelMainTest {
	@BeforeClass
	public static void setUpBeforeClass() {
		PlatformResetter.main(null);
	}

	@AfterClass
	public static void tearDownAfterClass() {
		PlatformResetter.main(null);
	}

	@Test
	public void mainTest() throws ExceptionMP, ParseException {
		// initialize test variables
		String ident = "portrait_of_finnish_bands";
		String filepath = Config.getFilepathVar() + "/" + ident
				+ "/config/config.properties";
		ConfigMashup config = new ConfigMashup(filepath);

		// create new ModelMain instance and set page number
		ModelMain model = new ModelMain(config);
		model.setPageNr(1);

		// get the created MashupPage instance and check values
		MashupPage page = model.getPage();
		assertEquals(1, page.getPageNr());
		assertEquals(config.getValue(Model.PARAM_IDENT).trim(),
				page.getMashupIdent());
		assertEquals(config.getValue(Model.PARAM_NAME).trim(), page.getName());
		assertEquals(config.getValue(Model.PARAM_USERNAME).trim(),
				page.getUsername());
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yy");
		Date date = format
				.parse(config.getValue(Model.PARAM_CREATED_AT).trim());
		assertEquals(date, page.getCreatedAt());
	}
}