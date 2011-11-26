package ch.ffhs.inf09.pa.mashup_platform.var.portrait_of_finnish_bands.model.db;

import ch.ffhs.inf09.pa.mashup_platform.core.system.model.db.*;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import java.util.ArrayList;

public class DBPortraitOfFinnishBands extends DBGoogleSearch
{
	public static final String DB_IDENT = "portrait_of_finnish_bands___DBPortraitOfFinnishBands";
	private static final int NUMBER_RESULTS = 5;
	
	public void fillIn(Content content, int start, int number) throws ExceptionMP
	{
		String identCache = DB.identCache(DB_IDENT, content, start, number);
		if ( !fillInFromCache(content, identCache) )
		{
			DBFinnishBands db = new DBFinnishBands();
			db.fillIn(content, start, number);
			ArrayList<Content> children = content.getChildren();
			for (Content child: children)
			{
				child.addKeyword(child.getCaption());
				child.addKeyword("finnish band");
			}
			super.fillIn(content, 0, NUMBER_RESULTS);
			storeToCache(content, identCache);
		}
	}
}