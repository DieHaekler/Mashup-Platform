package ch.ffhs.inf09.pa.mashup_platform.web.model;

import ch.ffhs.inf09.pa.mashup_platform.common.db.*;

public class ModelMashupOverview extends ModelApplication
{	
	public ModelMashupOverview()
	{
		super();
	}
	
	public MashupOverview get(int start, int number)
	{
		return db.getOverview(start, number);
	}
}