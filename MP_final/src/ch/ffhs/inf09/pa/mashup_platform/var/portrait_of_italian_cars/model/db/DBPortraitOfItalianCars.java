package ch.ffhs.inf09.pa.mashup_platform.var.portrait_of_italian_cars.model.db;

import java.util.List;

import ch.ffhs.inf09.pa.mashup_platform.common.util.ExceptionMP;
import ch.ffhs.inf09.pa.mashup_platform.config.Config;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.Content;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.ContentSection;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.db.DB;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.db.DBFlickr;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.db.DBGoogleSearch;

/**
 * This virtual database provides the mashed data of Finnish bands. The data are
 * coming from Wikipedia, Flickr and Google.
 * 
 * @author Joël
 * 
 */
public class DBPortraitOfItalianCars extends DB {
	public static final String DB_IDENT = "portrait_of_italian_cars___DBPortraitOfItalianCars";
	public static final String PARAM_NUMBER_FLICKR_RESULTS = "NUMBER_FLICKR_RESULTS";
	public static final String PARAM_NUMBER_GOOGLE_SEARCH_RESULTS = "NUMBER_GOOGLE_SEARCH_RESULTS";
	private int numberFlickrResults = 3;
	private int numberGoogleSearchResults = 5;

	public DBPortraitOfItalianCars(String filepath) throws ExceptionMP {
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
			DBItalianCars db1 = new DBItalianCars(
					Config.getFilepathVar()
							+ "/portrait_of_italian_cars/config/db/DBItalianCars.properties");
			db1.fillIn(content, start, number);
			
			// process Flickr search
			DBFlickr dbFlickr = new DBFlickr(Config.getFilepathSystem()
					+ "/config/system/db/DBFlickr.properties");
			ContentSection section = content
					.getSection(DBItalianCars.SECTION_IDENT);
			List<Content> parts = section.getParts();
			for (Content part : parts) {
				part.clearKeywords();
				part.addKeyword(part.getCaption());
				part.addKeyword("car");
				dbFlickr.fillIn(part, 0, numberFlickrResults);
			}

			// process Google search
			DBGoogleSearch dbGoogleSearch = new DBGoogleSearch(
					Config.getFilepathSystem()
							+ "/config/system/db/DBGoogleSearch.properties");
			section = content.getSection(DBItalianCars.SECTION_IDENT);
			parts = section.getParts();
			for (Content part : parts) {
				part.clearKeywords();
				part.addKeyword(part.getCaption());
				part.addKeyword("italian");
				part.addKeyword("car");
				dbGoogleSearch.fillIn(part, 0, numberGoogleSearchResults);
			}

			storeToCache(content, identCache);
		}
	}
}