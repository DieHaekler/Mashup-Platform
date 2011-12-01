package ch.ffhs.inf09.pa.mashup_platform.common.db.test;

import ch.ffhs.inf09.pa.mashup_platform.common.db.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.DBConfig;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.*;

public class Test1
{
	public static void main(String[] args)
	{
		Mashup mashup = new Mashup();
		mashup.setIdent("portrait_of_finnish_bands");
		mashup.setStart(0);
		mashup.setNumber(3);
		Content content = new Content();
		content.setCaption("Portrait of Finnish Bands");
		mashup.setContent(content);
		
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
			db.setMashup(mashup);
			System.out.println("mashup has been stored to OrientDB");
		}
	}
}