package ch.ffhs.inf09.pa.mashup_platform.core.system.model.db;

import java.util.*;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.*;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.fetcher.google_search.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;

public class DBGoogleSearch extends DB
{
	public static final String DB_IDENT = "system___DBGoogleSearch";
	
	public void fillIn(Content content, int start, int number) throws ExceptionMP
	{
		String identCache = DB.identCache(DB_IDENT, content, start, number);
		if ( !fillInFromCache(content, identCache) )
		{
			ArrayList<Content> children = content.getChildren();
			if (start < 0) start = 0;
			int end = start + number;
			if (end > children.size()) end = children.size();
			if (end < 0) end = 0;
			List<Content> list = children.subList(start, end);
			for (Content child: list)
			{
				ArrayList<ResultGoogleSearch> results
					= FetcherGoogleSearch.fetchResults(child.getKeyWords());
				for (ResultGoogleSearch result: results)
				{
					Content contentResult = new Content(result.getTitle());
					contentResult.setUrl(result.getURL());
					contentResult.setBody(result.getContent());
					child.addChild(contentResult);
				}
			}
			storeToCache(content, identCache);
		}
	}

}