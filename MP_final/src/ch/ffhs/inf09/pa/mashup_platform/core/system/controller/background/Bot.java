package ch.ffhs.inf09.pa.mashup_platform.core.system.controller.background;

import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.*;
import ch.ffhs.inf09.pa.mashup_platform.core.system.controller.*;

public class Bot extends Task
{
	public static final String PARAM_SLEEP_TIME = "SLEEP_TIME";
	public static final String PARAM_MIN_TIME_INTERVAL_PER_MASHUP = "MIN_TIME_INTERVAL_PER_MASHUP";
	
	public Bot() throws ExceptionMP
	{
		super("bot");
	}
	
	public void run()
	{
		LoggerMP.writeNotice("[Bot] bot started");
		int sleepTime = 5000;
		int minTimeInterval = 60;
		sleepTime = parseInt( properties.getProperty(PARAM_SLEEP_TIME) );
		minTimeInterval = parseInt( properties.getProperty(PARAM_MIN_TIME_INTERVAL_PER_MASHUP) );
		while ( !isKilled() )
		{
			process(minTimeInterval);
			try
			{
				Thread.sleep(sleepTime);
			} catch (InterruptedException e)
			{
			}
		}
		cleanup();
		LoggerMP.writeNotice("[Bot] killed");
	}
	
	private static synchronized void process(int minTimeInterval)
	{
		try
		{
			MashupInfo mashupInfo = MashupManager.pick(minTimeInterval);
			if (mashupInfo != null)
			{
				int max = mashupInfo.getMaxNumberPages();
				int pagenr = (mashupInfo.getPageNrProcessed() + 1) % max;
				mashupInfo.setPageNrProcessed(pagenr);
				String ident = mashupInfo.getIdent();
				LoggerMP.writeNotice("[Bot] processing mashup '" + ident
					+ "' (page nr: " + pagenr + ")");
				try
				{
					Controller controller = new Controller(ident, pagenr);
					LoggerMP.writeNotice("[Bot] main controller instantiated");
					MashupManager.store(controller.getMashup());
				} catch (ExceptionMP e)
				{
					LoggerMP.writeError(e);
				}
				LoggerMP.writeNotice("[Bot] mashup '" + ident + "' processed");
			}
		} catch (ExceptionMP e)
		{
			LoggerMP.writeError(e);
		}
	}

}