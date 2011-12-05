package ch.ffhs.inf09.pa.mashup_platform.common.db;

import ch.ffhs.inf09.pa.mashup_platform.core.system.model.*;
import java.io.*;

public class MashupPage implements Serializable
{
	private static final long serialVersionUID = 1L;
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