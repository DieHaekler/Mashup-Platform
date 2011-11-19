package ch.ffhs.inf09.pa.mashup_platform.core.system.model;

import ch.ffhs.inf09.pa.mashup_platform.common.util.*;

public abstract class Model
{
	protected Content content = null;
	
	public Model(String caption)
	{
		content = new Content(caption);
	}
	
	public abstract void setRange(int start, int number) throws ExceptionMP;
	
	public Content getContent()
	{
		return content;
	}
}