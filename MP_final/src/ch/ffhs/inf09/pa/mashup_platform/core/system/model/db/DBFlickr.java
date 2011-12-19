package ch.ffhs.inf09.pa.mashup_platform.core.system.model.db;

import java.util.ArrayList;
import java.util.List;

import ch.ffhs.inf09.pa.mashup_platform.common.util.ExceptionMP;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.Content;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.ContentSection;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.fetcher.flickr.FetcherFlickr;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.fetcher.flickr.ResultFlickr;

/**
 * This virtual data source provides access to the Flickr image search.
 * 
 * @author Malte
 * 
 */
public class DBFlickr extends DB {
	public static final String DB_IDENT = "system___DBFlickr";
	public static final String SECTION_IDENT = "flickr";
	public static String PARAM_URL_API = "URL_API";

	public DBFlickr(String filepath) throws ExceptionMP {
		super(filepath);
	}

	/**
	 * A "Flickr" section gets added to the content object. This section
	 * contains a list of (sub-)content objects keeping the information of the
	 * Flickr search results.
	 */
	public void fillIn(Content content, int start, int number)
			throws ExceptionMP {
		String identCache = DB.identCache(DB_IDENT, content, start, number);
		if (!fillInFromCache(content, identCache)) {
			List<ResultFlickr> results = FetcherFlickr.fetchResults(
					config.getValue(PARAM_URL_API),
					(ArrayList<String>) content.getKeywords());			
			if (start < 0)
				start = 0;
			int end = start + number;
			if (end > results.size())
				end = results.size();
			if (end < 0)
				end = 0;
			List<ResultFlickr> list = results.subList(start, end);
			if (list.size() > 0) {
				ContentSection section = new ContentSection();
				for (ResultFlickr result : list) {
					Content part = new Content();
					part.setUrl(result.getURL());
					part.setImgURL(result.getImgURL());
					String[] keywords = result.getTags().split(" ");
					for (String keyword : keywords) {
						part.addKeyword(keyword);
					}
					section.addPart(part);
				}
				content.addSection(SECTION_IDENT, section);
			}
			storeToCache(content, identCache);
		}
	}

}