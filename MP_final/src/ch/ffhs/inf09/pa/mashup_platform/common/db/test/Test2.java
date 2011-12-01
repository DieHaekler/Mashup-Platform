package ch.ffhs.inf09.pa.mashup_platform.common.db.test;

import ch.ffhs.inf09.pa.mashup_platform.common.db.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.DBConfig;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.*;

public class Test2
{
	public static void main(String[] args)
	{	
		DBLocal db = null;
		try
		{
			db = new DBOrient(DBConfig.DB_USERNAME, DBConfig.DB_PASSWORD);
		} catch (ExceptionMP e)
		{
			e.printStackTrace();
		}
		if (db != null)
		{
			Mashup mashup = db.getMashup("portrait_of_finnish_bands", 0, 3);
			if (mashup != null)
			{
				Content content = mashup.getContent();
				System.out.println(content.getCaption());
			}
		}
	}
}