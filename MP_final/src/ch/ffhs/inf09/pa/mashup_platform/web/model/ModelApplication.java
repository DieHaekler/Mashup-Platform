package ch.ffhs.inf09.pa.mashup_platform.web.model;

import ch.ffhs.inf09.pa.mashup_platform.common.db.*;

public abstract class ModelApplication
{
	protected DBLocal db;
	
	public ModelApplication(DBLocal db)
	{
		this.db = db;
	}
}