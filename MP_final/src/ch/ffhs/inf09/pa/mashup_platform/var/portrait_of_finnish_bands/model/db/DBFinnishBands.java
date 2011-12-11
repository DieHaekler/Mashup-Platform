package ch.ffhs.inf09.pa.mashup_platform.var.portrait_of_finnish_bands.model.db;

import ch.ffhs.inf09.pa.mashup_platform.core.system.model.db.*;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.*;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.fetcher.*;
import java.util.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;

public class DBFinnishBands extends DB
{
	public static final String DB_IDENT = "portrait_of_finnish_bands___DBFinnishBands";
	public static final String SECTION_IDENT = "finnish_bands";
	public static final String PARAM_URL = "URL";
	public static final String PARAM_REGEX = "REGEX";
	public static final String PARAM_CAPTION = "CAPTION";
	
	public DBFinnishBands(String filepath) throws ExceptionMP
	{
		super(filepath);
	}
	
	public void fillIn(Content content, int start, int number) throws ExceptionMP
	{
		String identCache = DB.identCache(DB_IDENT, content, start, number);
		if ( !fillInFromCache(content, identCache) )
		{
			ArrayList<String> words = Fetcher.fetch(config.getValue(PARAM_URL),
				config.getValue(PARAM_REGEX));
			int end = start + number;
			if (end >= words.size()) end = words.size() - 1;
			List<String> list = words.subList(start, end);
			ContentSection section = new ContentSection();
			section.setCaption(config.getValue(PARAM_CAPTION));
			for (String word: list)
			{
				Content part = new Content();
				part.setCaption(word);
				section.addPart(part);
				content.addSection(SECTION_IDENT, section);
			}
			storeToCache(content, identCache);
		}
		
	}
}