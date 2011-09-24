package ch.ffhs.inf09.pa.mashup.var.news_from_finnish_bands.model;

import ch.ffhs.inf09.pa.mashup.system.model.*;
import ch.ffhs.inf09.pa.mashup.system.model.db.DB;
import ch.ffhs.inf09.pa.mashup.var.news_from_finnish_bands.model.db.*;
import ch.ffhs.inf09.pa.mashup.system.util.*;

public class ModelMain extends Model
{	
	public ModelMain()
	{
		super("News from Finnish Bands");
	}
	
	public void setRange(int start, int number) throws ExceptionMashup
	{
		DB db = new DBNewsFromFinnishBands();
		db.fillIn(content, start, number);
	}
}