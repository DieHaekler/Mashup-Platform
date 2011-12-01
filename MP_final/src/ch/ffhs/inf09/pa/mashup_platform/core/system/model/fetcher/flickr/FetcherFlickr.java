package ch.ffhs.inf09.pa.mashup_platform.core.system.model.fetcher.flickr;

import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.fetcher.*;
import java.util.*;
import com.google.gson.*;
import java.io.IOException;
import java.net.URLEncoder;

public class FetcherFlickr extends Fetcher
{
	private static final String URL =
			"http://www.flickr.com/services/feeds/photos_public.gne?format=json&nojsoncallback=1&tags=";
	
	public static ArrayList<ResultFlickr> fetchResults(ArrayList<String> keyWords) throws ExceptionMP
	{
		ArrayList<ResultFlickr> results = new ArrayList<ResultFlickr>();
		String query = "";
		for (String keyWord: keyWords) query += keyWord + ",";
		try
		{
			query = URLEncoder.encode(query, "UTF-8");
		} catch (IOException e)
		{
			throw new ExceptionMP("Couldn't encode " + query, e);
		}
		String url = URL + query;
		String text = Fetcher.fetch(url);
		JsonParser parser = new JsonParser();
		JsonObject obj = parser.parse(text).getAsJsonObject();
		JsonElement el = obj.get("items");
		JsonArray arr = el.getAsJsonArray();
		for (int i = 0; i < arr.size(); i++)
		{
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