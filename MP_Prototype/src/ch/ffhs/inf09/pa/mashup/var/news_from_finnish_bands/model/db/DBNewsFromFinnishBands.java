package ch.ffhs.inf09.pa.mashup.var.news_from_finnish_bands.model.db;

import ch.ffhs.inf09.pa.mashup.system.model.db.*;
import ch.ffhs.inf09.pa.mashup.system.model.*;
import ch.ffhs.inf09.pa.mashup.system.util.*;

public class DBNewsFromFinnishBands extends DBGoogleNews
{

	public void fillIn(Content content, int start, int number) throws ExceptionMashup
	{
		DBFinnishBands db = new DBFinnishBands();
		db.fillIn(content, start, number);
		super.fillIn(content, 0, 4);
	}
}