package ch.ffhs.inf09.pa.mashup_platform.common.db;

import ch.ffhs.inf09.pa.mashup_platform.core.system.model.*;

public class MashupPage
{
	private Content content;
	private int pagenr;
	
	public void setContent(Content content, int pagenr)
	{
		this.content = content;
		this.pagenr = pagenr;
	}
	
	public Content getContent()
	{
		return content;
	}
	
	public int getPageNr()
	{
		return pagenr;
	}
}