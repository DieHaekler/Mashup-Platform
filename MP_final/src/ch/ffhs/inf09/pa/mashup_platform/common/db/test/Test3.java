package ch.ffhs.inf09.pa.mashup_platform.common.db.test;

import java.util.*;
import ch.ffhs.inf09.pa.mashup_platform.common.db.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.Config;

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
			MashupOverview overview = db.getOverview(0, 100);
			if (overview != null)
			{
				List<MashupInfo> list = overview.getList();
				for (MashupInfo info: list)
				{
					System.out.println(info.getNumberPages());
				}
			}
		}
	}
}