package ch.ffhs.inf09.pa.mashup_platform.core.system.model.fetcher.google_search;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import ch.ffhs.inf09.pa.mashup_platform.common.util.ExceptionMP;
import ch.ffhs.inf09.pa.mashup_platform.common.util.LoggerMP;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.fetcher.Fetcher;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * This fetcher class is used for retrieving the search results from Google.
 * 
 * @author Malte
 * 
 */
public class FetcherGoogleSearch extends Fetcher {

	/**
	 * Returns a list of search results.
	 * 
	 * @param urlAPI
	 *            the (RESTful) URL to the Google API
	 * @param keyWords
	 *            the key words for the search
	 * @return a list of search results
	 * @throws ExceptionMP
	 */
	public static ArrayList<ResultGoogleSearch> fetchResults(String urlAPI,
			ArrayList<String> keyWords) throws ExceptionMP {
		ArrayList<ResultGoogleSearch> results = new ArrayList<ResultGoogleSearch>();
		String query = "";
		for (String keyWord : keyWords)
			query += keyWord + " ";
		try {
			query = URLEncoder.encode(query, "UTF-8");
		} catch (IOException e) {
			throw new ExceptionMP("Couldn't encode " + query, e);
		}
		String url = urlAPI + query;
		String text = Fetcher.fetch(url);
		JsonParser parser = new JsonParser();
		JsonObject obj = parser.parse(text).getAsJsonObject();
		JsonElement el = obj.get("responseData");
		String errorMsg = "[FetcherGoogleSearch] the retrieved text doesn't match the expected structure: "
				+ text;
		if (el == null) {
			LoggerMP.writeError(errorMsg);
		} else {
			obj = el.getAsJsonObject();
			el = obj.get("results");
			if (el == null) {
				LoggerMP.writeError(errorMsg);
			} else {
				JsonArray arr = el.getAsJsonArray();
				for (int i = 0; i < arr.size(); i++) {
					ResultGoogleSearch result = new ResultGoogleSearch();
					obj = arr.get(i).getAsJsonObject();
					result.setURL(obj.get("url").getAsString());
					result.setTitle(obj.get("title").getAsString());
					result.setContent(obj.get("content").getAsString());
					results.add(result);
				}
			}
		}
		return results;
	}
}