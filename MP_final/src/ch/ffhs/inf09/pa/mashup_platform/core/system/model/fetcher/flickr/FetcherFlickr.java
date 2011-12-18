package ch.ffhs.inf09.pa.mashup_platform.core.system.model.fetcher.flickr;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import ch.ffhs.inf09.pa.mashup_platform.common.util.ExceptionMP;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.fetcher.Fetcher;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * This fetcher class is used for retrieving the search results from Flickr.
 * 
 * @author Malte
 * 
 */
public class FetcherFlickr extends Fetcher {

	/**
	 * Returns a list of search results.
	 * 
	 * @param urlAPI
	 *            the (RESTful) URL to the Flickr API
	 * @param keyWords
	 *            the key words for the search
	 * @return a list of search results
	 * @throws ExceptionMP
	 */
	public static ArrayList<ResultFlickr> fetchResults(String urlAPI,
			ArrayList<String> keyWords) throws ExceptionMP {
		ArrayList<ResultFlickr> results = new ArrayList<ResultFlickr>();
		String query = "";
		for (String keyWord : keyWords)
			query += keyWord + ",";
		try {
			query = URLEncoder.encode(query, "UTF-8");
		} catch (IOException e) {
			throw new ExceptionMP("Couldn't encode " + query, e);
		}
		String url = urlAPI + query;
		String text = Fetcher.fetch(url);
		JsonParser parser = new JsonParser();
		JsonObject obj = parser.parse(text).getAsJsonObject();
		JsonElement el = obj.get("items");
		JsonArray arr = el.getAsJsonArray();
		for (int i = 0; i < arr.size(); i++) {
			ResultFlickr result = new ResultFlickr();
			obj = arr.get(i).getAsJsonObject();
			result.setURL(obj.get("link").getAsString());
			result.setPublished(obj.get("published").getAsString());
			result.setTags(obj.get("tags").getAsString());
			el = obj.get("media");
			obj = el.getAsJsonObject();
			result.setImgURL(obj.get("m").getAsString());
			results.add(result);
		}
		return results;
	}
}