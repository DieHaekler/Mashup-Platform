package ch.ffhs.inf09.pa.mashup_platform.core.system.model.db;

import java.util.ArrayList;
import java.util.List;

import ch.ffhs.inf09.pa.mashup_platform.common.util.ExceptionMP;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.Content;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.ContentSection;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.fetcher.google_search.FetcherGoogleSearch;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.fetcher.google_search.ResultGoogleSearch;

/**
 * This virtual data source provides access to the Google search.
 * 
 * @author Malte
 * 
 */
public class DBGoogleSearch extends DB {
	public static final String DB_IDENT = "system___DBGoogleSearch";
	public static final String SECTION_IDENT = "google_search";
	public static String PARAM_URL_API = "URL_API";

	public DBGoogleSearch(String filepath) throws ExceptionMP {
		super(filepath);
	}

	/**
	 * A "Google Search" section gets added to the content object. This section
	 * contains a list of (sub-)content objects keeping the information of the
	 * Google search results.
	 */
	public void fillIn(Content content, int start, int number)
			throws ExceptionMP {
		String identCache = DB.identCache(DB_IDENT, content, start, number);
		if (!fillInFromCache(content, identCache)) {
			List<ResultGoogleSearch> results = FetcherGoogleSearch
					.fetchResults(config.getValue(PARAM_URL_API),
							(ArrayList<String>) content.getKeywords());
			if (start < 0)
				start = 0;
			int end = start + number;
			if (end > results.size())
				end = results.size();
			if (end < 0)
				end = 0;
			List<ResultGoogleSearch> list = results.subList(start, end);
			if (list.size() > 0) {
				ContentSection section = new ContentSection();
				for (ResultGoogleSearch result : list) {
					Content part = new Content();
					part.setCaption(result.getTitle());
					part.setUrl(result.getURL());
					part.setBody(result.getContent());
					section.addPart(part);
				}
				content.addSection(SECTION_IDENT, section);
			}
			storeToCache(content, identCache);
		}
	}

}