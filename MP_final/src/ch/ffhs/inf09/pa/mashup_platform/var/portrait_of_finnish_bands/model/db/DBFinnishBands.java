package ch.ffhs.inf09.pa.mashup_platform.var.portrait_of_finnish_bands.model.db;

import ch.ffhs.inf09.pa.mashup_platform.core.system.model.db.*;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.*;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.fetcher.*;
import java.io.*;
import java.util.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.*;

public class DBFinnishBands extends DB
{
	public static final String DB_IDENT = "portrait_of_finnish_bands___DBFinnishBands";
	public static final String SECTION_IDENT = "finnish_bands";
	public static final String PARAM_URL = "URL";
	public static final String PARAM_REGEX = "REGEX";
	public static final String PARAM_CAPTION = "CAPTION";
	private HashMap<String, String> map = new HashMap<String, String>();
	
	public DBFinnishBands()
	{
		super();
		maxCacheAge = 864000; // 10 days
		
		String filepath = Config.getInstance().getValue(Config.PARAM_FILE_PATH_SYSTEM)
			+ "/src/ch/ffhs/inf09/pa/mashup_platform/var/portrait_of_finnish_bands/config/config.txt";
		try
		{
			String settings = FileMP.getContent(filepath);
			map.put(PARAM_URL, Config.value(PARAM_URL, settings));
			map.put(PARAM_REGEX, Config.value(PARAM_REGEX, settings));
			map.put(PARAM_CAPTION, Config.value(PARAM_CAPTION, settings));
		} catch (FileNotFoundException e)
		{
			LoggerMP.writeError("[DBFinnishBands] Couldn't find " + filepath);
		} catch (IOException e)
		{
			LoggerMP.writeError("[DBFinnishBands] Couldn't open " + filepath);
		}
	}
	
	public void fillIn(Content content, int start, int number) throws ExceptionMP
	{
		String identCache = DB.identCache(DB_IDENT, content, start, number);
		if ( !fillInFromCache(content, identCache) )
		{
			ArrayList<String> words = Fetcher.fetch(map.get(PARAM_URL), map.get(PARAM_REGEX));
			int end = start + number;
			if (end >= words.size()) end = words.size() - 1;
			List<String> list = words.subList(start, end);
			ContentSection section = new ContentSection();
			section.setCaption(map.get(PARAM_CAPTION));
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