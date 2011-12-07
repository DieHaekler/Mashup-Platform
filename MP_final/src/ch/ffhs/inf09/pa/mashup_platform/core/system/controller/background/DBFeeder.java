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
		cleanup();
		LoggerMP.writeNotice("[DBFeeder] DB Feeder started");
		int sleepTime = parseInt( properties.getProperty(PARAM_SLEEP_TIME) );
		if (sleepTime <= 0) sleepTime = 10000;
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
		LoggerMP.writeNotice("[DBFeeder] DB Feeder killed");
		cleanup();
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
					MashupPage page = (MashupPage)FileMP.get(filepathQueue);
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
							db.setPage(page);
							LoggerMP.writeNotice("[DBFeeder] Mashup '" + page.getMashupIdent()
								+ "' (page: " + page.getPageNr() + ") stored to DB");
							FileMP.move(filepathQueue, filepathDone, true);
							//db.close();
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