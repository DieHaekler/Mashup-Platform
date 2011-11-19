package ch.ffhs.inf09.pa.mashup_platform.core.system.model.fetcher;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;

public abstract class Fetcher
{
	public static String fetch(String url) throws ExceptionMP
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
			throw new ExceptionMP("invalid " + url, e);
		} catch (IOException e)
		{
			throw new ExceptionMP("could not connect to " + url, e);
		}
	}
	
	public static ArrayList<String> fetch(String url, String regex)
		throws ExceptionMP
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