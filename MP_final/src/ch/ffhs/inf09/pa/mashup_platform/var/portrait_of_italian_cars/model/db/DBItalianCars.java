package ch.ffhs.inf09.pa.mashup_platform.var.portrait_of_italian_cars.model.db;

import java.util.ArrayList;
import java.util.List;

import ch.ffhs.inf09.pa.mashup_platform.common.util.ExceptionMP;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.Content;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.ContentSection;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.db.DB;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.fetcher.Fetcher;

/**
 * This virtual database provides a list of Italian car manufacturers.
 * 
 * @author Alexander
 * 
 */
public class DBItalianCars extends DB {
	public static final String DB_IDENT = "portrait_of_italian_cars___DBItalianCars";
	public static final String SECTION_IDENT = "italian_cars";
	public static final String PARAM_URL = "URL";
	public static final String PARAM_REGEX = "REGEX";
	public static final String PARAM_CAPTION = "CAPTION";

	public DBItalianCars(String filepath) throws ExceptionMP {
		super(filepath);
	}

	/**
	 * A section gets added to the Content object. This section contains a list
	 * of Finnish band names.
	 * 
	 */
	public void fillIn(Content content, int start, int number)
			throws ExceptionMP {
		String identCache = DB.identCache(DB_IDENT, content, start, number);
		if (!fillInFromCache(content, identCache)) {
			ArrayList<String> words = Fetcher.fetch(config.getValue(PARAM_URL),
					config.getValue(PARAM_REGEX));
			int end = start + number;
			if (end >= words.size())
				end = words.size() - 1;
			List<String> list = words.subList(start, end);
			ContentSection section = new ContentSection();
			section.setCaption(config.getValue(PARAM_CAPTION));
			for (String word : list) {
				Content part = new Content();
				part.setCaption(word);
				section.addPart(part);
				content.addSection(SECTION_IDENT, section);
			}
			storeToCache(content, identCache);
		}

	}
}