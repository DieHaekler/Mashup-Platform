package ch.ffhs.inf09.pa.mashup_platform.common.db.test;

import java.util.*;
import ch.ffhs.inf09.pa.mashup_platform.common.db.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.DBConfig;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.*;

public class Test3
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
			Mashups mashups = db.getMashups(0, 100);
			if (mashups != null)
			{
				List<Mashup> list = mashups.getList();
				for (Mashup mashup: list)
				{
					System.out.println(mashup.getIdent());
					Content content = mashup.getContent();
					System.out.println(content.getCaption());
				}
			}
		}
	}
}