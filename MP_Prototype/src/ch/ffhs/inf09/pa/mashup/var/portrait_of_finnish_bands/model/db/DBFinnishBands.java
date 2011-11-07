package ch.ffhs.inf09.pa.mashup.var.portrait_of_finnish_bands.model.db;

import ch.ffhs.inf09.pa.mashup.system.model.db.*;
import ch.ffhs.inf09.pa.mashup.system.model.*;
import ch.ffhs.inf09.pa.mashup.system.model.fetcher.*;
import java.util.*;
import ch.ffhs.inf09.pa.mashup.system.util.*;

public class DBFinnishBands extends DB
{
	public static final String DB_IDENT = "portrait_of_finnish_bands___DBFinnishBands";
	private static final String URL =
		"http://en.wikipedia.org/wiki/List_of_bands_from_Finland";
	private static final String REGEX = "<li><a href=\"" + "[^\"]+" + "\"[^>]*>"
			+ "([^<]+)" + "</a>.*?</li>";
	
	public void fillIn(Content content, int start, int number) throws ExceptionMashup
	{
		String identCache = DB.identCache(DB_IDENT, content, start, number);
		if ( !fillInFromCache(content, identCache) )
		{
			ArrayList<String> words = Fetcher.fetch(URL, REGEX);
			int end = start + number;
			if (end >= words.size()) end = words.size() - 1;
			List<String> list = words.subList(start, end);
			for (String word: list)
			{
				Content child = new Content(word);
				content.addChild(child);
			}
			storeToCache(content, identCache);
		}
		
	}
}