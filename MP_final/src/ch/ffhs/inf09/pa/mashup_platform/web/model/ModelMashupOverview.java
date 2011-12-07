package ch.ffhs.inf09.pa.mashup_platform.web.model;

import ch.ffhs.inf09.pa.mashup_platform.common.db.*;
import ch.ffhs.inf09.pa.mashup_platform.web.*;

public class ModelMashupOverview extends ModelApplication
{	
	public ModelMashupOverview(Environment environment)
	{
		super(environment);
	}
	
	public MashupOverview get(int start, int number)
	{
		return environment.getDB().getOverview(start, number);
	}
}