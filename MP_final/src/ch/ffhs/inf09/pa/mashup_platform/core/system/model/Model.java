package ch.ffhs.inf09.pa.mashup_platform.core.system.model;

import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.DBConfig;

public abstract class Model
{
	protected Content content = null;
	
	public Model(String caption)
	{
		content = new Content();
		content.setCaption(caption);
		content.setPublisher(DBConfig.DB_USERNAME);
	}
	
	public abstract void setRange(int start, int number) throws ExceptionMP;
	
	public Content getContent()
	{
		return content;
	}
}