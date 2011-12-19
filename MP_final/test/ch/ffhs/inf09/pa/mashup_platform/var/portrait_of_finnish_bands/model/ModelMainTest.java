package ch.ffhs.inf09.pa.mashup_platform.var.portrait_of_finnish_bands.model;

import java.lang.reflect.Constructor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ch.ffhs.inf09.pa.mashup_platform.core.PlatformResetter;
import ch.ffhs.inf09.pa.mashup_platform.core.system.config.*;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.*;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.db.*;
import ch.ffhs.inf09.pa.mashup_platform.var.portrait_of_finnish_bands.model.db.*;
import ch.ffhs.inf09.pa.mashup_platform.common.db.MashupPage;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.*;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ModelMainTest 
{	
	@Test
	public void mainTest(){
		String ident = "portrait_of_finnish_bands";				
		String filepath = Config.getFilepathVar() + "/" + ident
			+ "/config/config.properties";
		ConfigMashup config = null;
		try {
			config = new ConfigMashup(filepath);
		} catch (ExceptionMP e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelMain model = new ModelMain(config);
		try {
			model.setPageNr(1);
		} catch (ExceptionMP e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MashupPage page = model.getPage();
		assertNotNull(page);
		assertEquals(1, page.getPageNr());
		assertEquals(config.getValue(Model.PARAM_IDENT).trim(), page.getMashupIdent());
		assertEquals(config.getValue(Model.PARAM_NAME).trim(),page.getName());
		assertEquals(config.getValue(Model.PARAM_USERNAME).trim(),page.getUsername());
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yy");
		Date date = null;
		try {
			date = format.parse(config.getValue(Model.PARAM_CREATED_AT).trim());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(date,page.getCreatedAt());
		//System.out.println(page.getContent().getCaption());
		//assertEquals(config.getValue(Model.PARAM_NAME).trim(), page.getContent().getCaption());

		/*if (createdAt != null)
		{
			SimpleDateFormat format = new SimpleDateFormat("dd.MM.yy");
			try
			{
				Date date = format.parse(createdAt);
				page.setCreatedAt(date);
			} catch (ParseException e)
			{
				LoggerMP.writeError("[Model] couldn't parse config param '" + Model.PARAM_CREATED_AT + "'"
					+ " for mashup " + page.getMashupIdent());
			}
		}*/
		
		
	}
}