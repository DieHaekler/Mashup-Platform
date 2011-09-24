package ch.ffhs.inf09.pa.mashup.system.model.fetcher;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;
import ch.ffhs.inf09.pa.mashup.system.util.*;

public abstract class Fetcher
{
	public static String fetch(String url) throws ExceptionMashup
	{
		try
		{
			URL ur = new URL(url);
			URLConnection conn = ur.openConnection();
			String encoding = conn.getContentEncoding();
			if (encoding == null) encoding = "UTF-8";
			String line;
			StringBuilder builder = new StringBuilder();
			BufferedReader reader = new BufferedReader(
				new InputStreamReader(
					conn.getInputStream(), encoding));
			while( (line = reader.readLine()) != null )
			{
				builder.append(line);
			}
			reader.close();
			return builder.toString();
		} catch (MalformedURLException e)
		{
			throw new ExceptionMashup("invalid " + url, e);
		} catch (IOException e)
		{
			throw new ExceptionMashup("could not connect to " + url, e);
		}
	}
	
	public static ArrayList<String> fetch(String url, String regex)
		throws ExceptionMashup
	{
		String text = fetch(url);
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		ArrayList<String> words = new ArrayList<String>();
		while ( matcher.find() )
		{
			words.add(matcher.group(1));
		}
		return words;
	}
}