package ch.ffhs.inf09.pa.mashup_platform.common.db.test;

import ch.ffhs.inf09.pa.mashup_platform.common.db.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.*;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.*;

public class Test1
{
	public static void main(String[] args)
	{
		// test double connections
		DBLocal db1 = null;
		DBLocal db2 = null;
		Config config = Config.getInstance();
		try
		{
			db1 = new DBOrient(config.getValue(Config.PARAM_DB_USERNAME),
				config.getValue(Config.PARAM_DB_PASSWORD));
			db2 = new DBOrient(config.getValue(Config.PARAM_DB_USERNAME),
					config.getValue(Config.PARAM_DB_PASSWORD));
		} catch (ExceptionMP e)
		{
			e.printStackTrace();
		}
		if ((db1 != null) && (db2 != null))
		{
			for (int i = 0; i < 10; i++)
			{
				MashupPage page = new MashupPage();
				page.setPageNr(i * 2);
				page.setMashupIdent("portrait");
				Content content = new Content();
				content.setCaption("p_" + page.getPageNr());
				page.setContent(content);
				if (db1 != null)
				{
					db1.setPage(page);
					System.out.println("page " + page.getPageNr() + " has been stored to OrientDB");
				}
				page = new MashupPage();
				page.setPageNr(i * 2 + 1);
				page.setMashupIdent("portrait");
				content = new Content();
				content.setCaption("p" + page.getPageNr());
				page.setContent(content);
				if (db2 != null)
				{
					db2.setPage(page);
					System.out.println("page " + page.getPageNr() + " has been stored to OrientDB");
				}
			}
			db1.close();
			db2.close();
		}
	}
}

