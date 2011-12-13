package ch.ffhs.inf09.pa.mashup_platform.var.portrait_of_finnish_bands.model.db;

import ch.ffhs.inf09.pa.mashup_platform.core.system.model.db.*;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.Config;

import java.util.*;

public class DBPortraitOfFinnishBands extends DB {
	public static final String DB_IDENT = "portrait_of_finnish_bands___DBPortraitOfFinnishBands";
	public static final String PARAM_NUMBER_FLICKR_RESULTS = "NUMBER_FLICKR_RESULTS";
	public static final String PARAM_NUMBER_GOOGLE_SEARCH_RESULTS = "NUMBER_GOOGLE_SEARCH_RESULTS";
	private int numberFlickrResults = 3;
	private int numberGoogleSearchResults = 5;

	public DBPortraitOfFinnishBands(String filepath) throws ExceptionMP {
		super(filepath);
		String temp = config.getValue(PARAM_NUMBER_FLICKR_RESULTS);
		if (temp != null) {
			numberFlickrResults = Integer.parseInt(temp);
		}
		temp = config.getValue(PARAM_NUMBER_GOOGLE_SEARCH_RESULTS);
		if (temp != null) {
			numberGoogleSearchResults = Integer.parseInt(temp);
		}
	}

	public void fillIn(Content content, int start, int number)
			throws ExceptionMP {
		String identCache = DB.identCache(DB_IDENT, content, start, number);
		if (!fillInFromCache(content, identCache)) {
			// get the Finnish band names
			DBFinnishBands db1 = new DBFinnishBands(
					Config.getFilepathVar()
							+ "/portrait_of_finnish_bands/config/db/DBFinnishBands.properties");
			db1.fillIn(content, start, number);

			// process Flickr search
			DBFlickr dbFlickr = new DBFlickr(Config.getFilepathSystem()
					+ "/config/system/db/DBFlickr.properties");
			ContentSection section = content
					.getSection(DBFinnishBands.SECTION_IDENT);
			List<Content> parts = section.getParts();
			for (Content part : parts) {
				part.clearKeywords();
				part.addKeyword(part.getCaption());
				part.addKeyword("band");
				dbFlickr.fillIn(part, 0, numberFlickrResults);
			}

			// process Google search
			DBGoogleSearch dbGoogleSearch = new DBGoogleSearch(
					Config.getFilepathSystem()
							+ "/config/system/db/DBGoogleSearch.properties");
			section = content.getSection(DBFinnishBands.SECTION_IDENT);
			parts = section.getParts();
			for (Content part : parts) {
				part.clearKeywords();
				part.addKeyword(part.getCaption());
				part.addKeyword("finnish");
				part.addKeyword("band");
				dbGoogleSearch.fillIn(part, 0, numberGoogleSearchResults);
			}

			storeToCache(content, identCache);
		}
	}
}