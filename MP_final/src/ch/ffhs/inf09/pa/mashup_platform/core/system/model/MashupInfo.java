package ch.ffhs.inf09.pa.mashup_platform.core.system.model;

import java.io.*;
import java.util.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.*;

public class MashupInfo
{
	private String ident;
	private int status;
	private int pageNrProcessed;
	private long lastProcessed;
	private int maxNumberPages;
	private String filepathStatus;
	
	public MashupInfo(String filepath) throws ExceptionMP
	{
		Properties properties = new Properties();
		try
		{
			properties.load(new FileInputStream(filepath));
			ident = properties.getProperty(Model.PARAM_IDENT);
			status = parseInt(properties.getProperty(Model.PARAM_STATUS));
			maxNumberPages = parseInt(properties.getProperty(Model.PARAM_MAX_NUMBER_PAGES));
		} catch (IOException e)
		{
			throw new ExceptionMP("[MashupInfo] couldn't read " + filepath, e);
		}
		filepathStatus = Config.getFilepathSystem() + "/output/status/" + ident
			+ "_processed.status";
		if ( FileMP.exists(filepathStatus) )
		{
			lastProcessed = FileMP.getTimestamp(filepathStatus);
			try
			{
				pageNrProcessed = parseInt( FileMP.getContent(filepathStatus).trim() );
			} catch (FileNotFoundException e)
			{
				throw new ExceptionMP("[MashupInfo] couldn't find " + filepathStatus, e);
			} catch (IOException e)
			{
				throw new ExceptionMP("[MashupInfo] couldn't read " + filepathStatus, e);
			}
		}
	}
	
	public void setPageNrProcessed(int pagenr) throws ExceptionMP
	{
		try
		{
			FileMP.write(filepathStatus, pagenr + "", false);
		} catch (IOException e)
		{
			throw new ExceptionMP("[MashupInfo] couldn't write to " + filepathStatus, e);
		}
	}
	
	private static int parseInt(String text)
	{
		if (text == null) return 0;
		return Integer.parseInt(text.trim());
	}
	
	public String getIdent() { return ident; }
	public int getStatus() { return status; }
	public int getPageNrProcessed() { return pageNrProcessed; }
	public long getLastProcessed() { return lastProcessed; }
	public int getMaxNumberPages() { return maxNumberPages; }
}