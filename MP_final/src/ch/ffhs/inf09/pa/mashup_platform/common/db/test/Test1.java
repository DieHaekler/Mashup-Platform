package ch.ffhs.inf09.pa.mashup_platform.common.db.test;

import ch.ffhs.inf09.pa.mashup_platform.common.db.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.*;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.*;

public class Test1
{
	public static void main(String[] args)
	{
		Mashup mashup = new Mashup();
		mashup.setIdent("portrait_of_finnish_bands");
		mashup.setStatus(Mashup.STATUS_ACTIVE);
		MashupPage page = new MashupPage();
		Content content = new Content();
		content.setCaption("Portrait of Finnish Bands");
		page.setContent(content, 12);
		mashup.setPage(page);
		
		DBLocal db = null;
		Config config = Config.getInstance();
		try
		{
			db = new DBOrient(config.getValue(Config.PARAM_DB_USERNAME),
				config.getValue(Config.PARAM_DB_PASSWORD));
		} catch (ExceptionMP e)
		{
			e.printStackTrace();
		}
		if (db != null)
		{
			db.setMashup(mashup);
			System.out.println("mashup has been stored to OrientDB");
		}
	}
}

