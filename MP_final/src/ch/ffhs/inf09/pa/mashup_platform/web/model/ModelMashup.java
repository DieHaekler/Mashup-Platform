package ch.ffhs.inf09.pa.mashup_platform.web.model;

import ch.ffhs.inf09.pa.mashup_platform.common.db.*;

public class ModelMashup extends ModelApplication
{	
	public ModelMashup()
	{
		super();
	}
	
	public Mashup get(String ident, int pagenr)
	{
		return db.getMashup(ident, pagenr);
	}
}