package ch.ffhs.inf09.pa.mashup_platform.common.db.test;

import ch.ffhs.inf09.pa.mashup_platform.common.db.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.Config;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.*;

public class Test2
{
	public static void main(String[] args)
	{	
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
			MashupPage page = db.getPage("portrait_of_finnish_bands", 1);
			if (page != null)
			{
				Content content = page.getContent();
				if (content != null)
				{
					System.out.println(content.getJSON());
				}
			}
			db.close();
		}
	}
}