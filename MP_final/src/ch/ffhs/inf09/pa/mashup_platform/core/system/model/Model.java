package ch.ffhs.inf09.pa.mashup_platform.core.system.model;

import java.text.*;
import java.util.*;
import ch.ffhs.inf09.pa.mashup_platform.common.db.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.core.system.config.*;

public abstract class Model
{
	public static String PARAM_IDENT = "IDENT";
	public static String PARAM_NAME = "NAME";
	public static String PARAM_USERNAME = "USERNAME";
	public static String PARAM_CREATED_AT = "CREATED_AT";
	public static String PARAM_STATUS = "STATUS";
	public static String PARAM_NUMBER_RECORDS_PER_PAGE = "NUMBER_RECORDS_PER_PAGE";
	public static String PARAM_MAX_NUMBER_PAGES = "MAX_NUMBER_PAGES";
	
	protected MashupPage page;
	protected int numberRecordsPerPage;
	
	public Model(ConfigMashup config)
	{
		page = new MashupPage();
		page.setMashupIdent(config.getValue(PARAM_IDENT).trim());
		page.setName(config.getValue(PARAM_NAME).trim());
		page.setUsername(config.getValue(PARAM_USERNAME).trim());
		String createdAt = config.getValue(PARAM_CREATED_AT).trim();
		if (createdAt != null)
		{
			SimpleDateFormat format = new SimpleDateFormat("dd.MM.yy");
			try
			{
				Date date = format.parse(createdAt);
				page.setCreatedAt(date);
			} catch (ParseException e)
			{
				LoggerMP.writeError("[Model] couldn't parse config param '" + Model.PARAM_CREATED_AT + "'"
					+ " for mashup " + page.getMashupIdent());
			}
		}
		
		numberRecordsPerPage = parseInt(config.getValue(PARAM_NUMBER_RECORDS_PER_PAGE));
	}
	
	public void setPageNr(int pagenr) throws ExceptionMP
	{
		page.setPageNr(pagenr);
	}
	
	public MashupPage getPage() { return page; }
	
	private static int parseInt(String text)
	{
		if (text == null) return 0;
		return Integer.parseInt(text.trim());
	}
}