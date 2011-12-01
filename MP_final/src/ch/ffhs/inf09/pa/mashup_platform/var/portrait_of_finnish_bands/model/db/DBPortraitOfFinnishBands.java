package ch.ffhs.inf09.pa.mashup_platform.var.portrait_of_finnish_bands.model.db;

import ch.ffhs.inf09.pa.mashup_platform.core.system.model.db.*;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import java.util.*;

public class DBPortraitOfFinnishBands extends DB
{
	public static final String DB_IDENT = "portrait_of_finnish_bands___DBPortraitOfFinnishBands";
	private static final int NUMBER_FLICKR_RESULTS = 3;
	private static final int NUMBER_GOOGLE_SEARCH_RESULTS = 5;
	
	public void fillIn(Content content, int start, int number) throws ExceptionMP
	{
		String identCache = DB.identCache(DB_IDENT, content, start, number);
		if ( !fillInFromCache(content, identCache) )
		{
			DBFinnishBands db1 = new DBFinnishBands();
			db1.fillIn(content, start, number);
			
			// process Flickr search
			DBFlickr dbFlickr = new DBFlickr();
			ContentSection section = content.getSection(DBFinnishBands.SECTION_IDENT);
			List<Content> parts = section.getParts();
			for (Content part: parts)
			{
				part.clearKeywords();
				part.addKeyword(part.getCaption());
				part.addKeyword("band");
				dbFlickr.fillIn(part, 0, NUMBER_FLICKR_RESULTS);
			}
			
			// process Google search
			DBGoogleSearch dbGoogleSearch = new DBGoogleSearch();
			section = content.getSection(DBFinnishBands.SECTION_IDENT);
			parts = section.getParts();
			for (Content part: parts)
			{
				part.clearKeywords();
				part.addKeyword(part.getCaption());
				part.addKeyword("finnish");
				part.addKeyword("band");
				dbGoogleSearch.fillIn(part, 0, NUMBER_GOOGLE_SEARCH_RESULTS);
			}
			
			storeToCache(content, identCache);
		}
	}
}