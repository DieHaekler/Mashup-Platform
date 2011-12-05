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
	
	protected Mashup mashup;
	protected int numberRecordsPerPage;
	
	public Model(ConfigMashup config)
	{
		mashup = new Mashup();
		mashup.setIdent(config.getValue(PARAM_IDENT).trim());
		mashup.setName(config.getValue(PARAM_NAME).trim());
		mashup.setUsername(config.getValue(PARAM_USERNAME).trim());
		mashup.setStatus( parseInt(config.getValue(PARAM_STATUS)) );
		String createdAt = config.getValue(PARAM_CREATED_AT).trim();
		if (createdAt != null)
		{
			SimpleDateFormat format = new SimpleDateFormat("dd.MM.yy");
			try
			{
				Date date = format.parse(createdAt);
				mashup.setCreatedAt(date);
			} catch (ParseException e)
			{
				LoggerMP.writeError("[Model] couldn't parse config param '" + Model.PARAM_CREATED_AT + "'"
					+ " for mashup " + mashup.getIdent());
			}
		}
		
		numberRecordsPerPage = parseInt(config.getValue(PARAM_NUMBER_RECORDS_PER_PAGE));
	}
	
	public abstract void setPageNr(int pagenr) throws ExceptionMP;
	public Mashup getMashup() { return mashup; }
	
	private static int parseInt(String text)
	{
		if (text == null) return 0;
		return Integer.parseInt(text.trim());
	}
}