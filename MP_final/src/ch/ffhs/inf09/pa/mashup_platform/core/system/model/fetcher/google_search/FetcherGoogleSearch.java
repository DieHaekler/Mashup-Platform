package ch.ffhs.inf09.pa.mashup_platform.core.system.model.fetcher.google_search;

import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.fetcher.*;
import java.util.*;
import com.google.gson.*;
import java.io.IOException;
import java.net.URLEncoder;

public class FetcherGoogleSearch extends Fetcher
{	
	public static ArrayList<ResultGoogleSearch> fetchResults(String urlAPI, ArrayList<String> keyWords)
			throws ExceptionMP
	{
		ArrayList<ResultGoogleSearch> results = new ArrayList<ResultGoogleSearch>();
		String query = "";
		for (String keyWord: keyWords) query += keyWord + " ";
		try
		{
			query = URLEncoder.encode(query, "UTF-8");
		} catch (IOException e)
		{
			throw new ExceptionMP("Couldn't encode " + query, e);
		}
		String url = urlAPI + query;
		String text = Fetcher.fetch(url);
		JsonParser parser = new JsonParser();
		JsonObject obj = parser.parse(text).getAsJsonObject();
		JsonElement el = obj.get("responseData");
		obj = el.getAsJsonObject();
		el = obj.get("results");
		JsonArray arr = el.getAsJsonArray();
		for (int i = 0; i < arr.size(); i++)
		{
			ResultGoogleSearch result = new ResultGoogleSearch();
			obj = arr.get(i).getAsJsonObject();
			result.setURL(obj.get("url").getAsString());
			result.setTitle(obj.get("title").getAsString());
			result.setContent(obj.get("content").getAsString());
			results.add(result);
		}
		return results;
	}
}