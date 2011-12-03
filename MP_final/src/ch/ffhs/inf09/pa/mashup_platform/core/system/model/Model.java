package ch.ffhs.inf09.pa.mashup_platform.core.system.model;

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
	
	protected Mashup mashup;
	protected int numberRecordsPerPage;
	
	public Model(ConfigMashup config)
	{
		mashup = new Mashup();
		mashup.setIdent(config.getValue(PARAM_IDENT));
		mashup.setName(config.getValue(PARAM_NAME));
		mashup.setUsername(config.getValue(PARAM_USERNAME));
		numberRecordsPerPage = Integer.parseInt(config.getValue(PARAM_NUMBER_RECORDS_PER_PAGE));
	}
	
	public abstract void setPageNr(int pagenr) throws ExceptionMP;
	
	public Mashup getMashup() { return mashup; }

}