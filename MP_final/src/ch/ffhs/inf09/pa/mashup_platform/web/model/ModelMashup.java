package ch.ffhs.inf09.pa.mashup_platform.web.model;

import ch.ffhs.inf09.pa.mashup_platform.common.db.*;
import ch.ffhs.inf09.pa.mashup_platform.web.*;

public class ModelMashup extends ModelApplication
{
	private String ident;
	private int pageNr;
	
	public ModelMashup(Environment environment, String ident, int pageNr)
	{
		super(environment);
		this.ident = ident;
		this.pageNr = pageNr;
	}
	
	public MashupPage getPage()
	{
		return environment.getDB().getPage(ident, pageNr);
	}
}