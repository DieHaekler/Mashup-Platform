package ch.ffhs.inf09.pa.mashup_platform.core.var.portrait_of_finnish_bands.model;

import ch.ffhs.inf09.pa.mashup_platform.core.system.model.*;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.db.DB;
import ch.ffhs.inf09.pa.mashup_platform.core.var.portrait_of_finnish_bands.model.db.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;

public class ModelMain extends Model
{	
	public ModelMain()
	{
		super("Portrait of Finnish Bands");
	}
	
	public void setRange(int start, int number) throws ExceptionMP
	{
		DB db = new DBPortraitOfFinnishBands();
		db.fillIn(content, start, number);
	}
}