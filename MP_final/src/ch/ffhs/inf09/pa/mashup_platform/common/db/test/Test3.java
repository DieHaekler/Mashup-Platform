package ch.ffhs.inf09.pa.mashup_platform.common.db.test;

import java.util.*;
import ch.ffhs.inf09.pa.mashup_platform.common.db.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.Config;
import ch.ffhs.inf09.pa.mashup_platform.config.*;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.*;

public class Test3
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
			Mashups mashups = db.getMashups(0, 100);
			if (mashups != null)
			{
				List<Mashup> list = mashups.getList();
				for (Mashup mashup: list)
				{
					System.out.println(mashup.getIdent());
					System.out.println(mashup.getName());
				}
			}
		}
	}
}