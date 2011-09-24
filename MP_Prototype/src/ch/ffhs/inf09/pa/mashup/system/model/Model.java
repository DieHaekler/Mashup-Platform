package ch.ffhs.inf09.pa.mashup.system.model;

import ch.ffhs.inf09.pa.mashup.system.util.*;

public abstract class Model
{
	protected Content content = null;
	
	public Model(String caption)
	{
		this.content = new Content(caption);
	}
	
	public abstract void setRange(int start, int number) throws ExceptionMashup;
	
	public Content getContent()
	{
		return content;
	}
}