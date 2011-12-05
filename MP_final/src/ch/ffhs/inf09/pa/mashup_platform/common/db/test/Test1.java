package ch.ffhs.inf09.pa.mashup_platform.common.db.test;

import ch.ffhs.inf09.pa.mashup_platform.common.db.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.*;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.*;

public class Test1
{
	public static void main(String[] args)
	{
		for (int i = 0; i < 10; i++)
		{
			Mashup mashup = new Mashup();
			String ident = "portrait_" + i;
			mashup.setIdent(ident);
			mashup.setStatus(Mashup.STATUS_ACTIVE);
			MashupPage page = new MashupPage();
			Content content = new Content();
			content.setCaption("Portrait of Finnish Bands");
			page.setContent(content, i);
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
				System.out.println("mashup " + ident + " has been stored to OrientDB");
				//db.close();
			}
		}
	}
}

