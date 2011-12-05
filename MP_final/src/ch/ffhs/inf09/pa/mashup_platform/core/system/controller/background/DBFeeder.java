package ch.ffhs.inf09.pa.mashup_platform.core.system.controller.background;

import java.util.*;
import java.io.*;
import ch.ffhs.inf09.pa.mashup_platform.common.db.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.Config;

public class DBFeeder extends Task
{
	public static final String PARAM_SLEEP_TIME = "SLEEP_TIME";
	
	public DBFeeder() throws ExceptionMP
	{
		super("db_feeder");
	}
	
	public void run()
	{
		LoggerMP.writeNotice("[DBFeeder] DB Feeder started");
		int sleepTime = parseInt( properties.getProperty(PARAM_SLEEP_TIME) );
		while ( !isKilled() )
		{
			process();
			try
			{
				Thread.sleep(sleepTime);
			} catch (InterruptedException e)
			{
			}
		}
		cleanup();
		LoggerMP.writeNotice("[DBFeeder] DB Feeder killed");
	}
	
	private static synchronized void process()
	{
		String folderpathOutput = Config.getFilepathSystem() + "/output/";
		ArrayList<String> filenames = FileMP.getFilenames(folderpathOutput, "\\.output$");
		for (String filename: filenames)
		{
			String filepath = folderpathOutput + "/" + filename;
			String filepathQueue = folderpathOutput + "/queue/" + filename;
			String filepathDone = folderpathOutput + "/done/" + filename;
			if ( FileMP.move(filepath, filepathQueue, true) )
			{
				try
				{
					Mashup mashup = (Mashup)FileMP.get(filepathQueue);
					if (mashup != null)
					{
						MashupPage page = mashup.getPage();
						if (page != null)
						{
							DBLocal db = null;
							Config config = Config.getInstance();
							try
							{
								db = new DBOrient(config.getValue(Config.PARAM_DB_USERNAME),
									config.getValue(Config.PARAM_DB_PASSWORD));
							} catch (ExceptionMP e)
							{
								LoggerMP.writeError(e);
							}
							if (db != null)
							{
								String ident = mashup.getIdent() + "_" + page.getPageNr();
								mashup.setIdent(ident);
								db.setMashup(mashup);
								LoggerMP.writeNotice("[DBFeeder] Mashup '" + ident + "' stored to DB");
								FileMP.move(filepathQueue, filepathDone, true);
								//db.close();
							}
						}
					}
				} catch (IOException e)
				{
					LoggerMP.writeError("[DBFeeder] couldn't read " + filepathQueue);
				} catch (ClassNotFoundException e)
				{
					LoggerMP.writeError("[DBFeeder] couldn't find " + filepathQueue);
				}
			}
		}
	}

}