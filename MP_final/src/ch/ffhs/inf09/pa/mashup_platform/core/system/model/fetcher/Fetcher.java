package ch.ffhs.inf09.pa.mashup_platform.core.system.model.fetcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ch.ffhs.inf09.pa.mashup_platform.common.util.ExceptionMP;

/**
 * The Fetcher base class provides methods to retrieve content from (remote)
 * websites.
 * 
 * @author Malte
 * 
 */
public abstract class Fetcher {

	/**
	 * Returns the plain-text of a website.
	 * 
	 * @param url
	 *            the url of the website
	 * @return the plain-text
	 * @throws ExceptionMP
	 */
	public static String fetch(String url) throws ExceptionMP {
		try {
			URL ur = new URL(url);
			URLConnection conn = ur.openConnection();
			String encoding = conn.getContentEncoding();
			if (encoding == null)
				encoding = "UTF-8";
			String line;
			StringBuilder builder = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), encoding));
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
			reader.close();
			return builder.toString();
		} catch (MalformedURLException e) {
			throw new ExceptionMP("invalid " + url, e);
		} catch (IOException e) {
			throw new ExceptionMP("could not connect to " + url, e);
		}
	}

	/**
	 * Returns a filtered list of words that have been found on a website.
	 * 
	 * @param url
	 *            the URL of the website
	 * @param regex
	 *            the regex to filter words
	 * @return the list of words
	 * @throws ExceptionMP
	 */
	public static ArrayList<String> fetch(String url, String regex)
			throws ExceptionMP {
		String text = fetch(url);
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		ArrayList<String> words = new ArrayList<String>();
		while (matcher.find()) {
			words.add(matcher.group(1));
		}
		return words;
	}
}