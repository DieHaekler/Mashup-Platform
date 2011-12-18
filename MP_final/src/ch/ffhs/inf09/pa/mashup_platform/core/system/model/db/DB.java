package ch.ffhs.inf09.pa.mashup_platform.core.system.model.db;

import java.io.IOException;

import ch.ffhs.inf09.pa.mashup_platform.common.util.ExceptionMP;
import ch.ffhs.inf09.pa.mashup_platform.core.system.config.ConfigDB;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.Content;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.cache.Cache;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.cache.CacheFile;

/**
 * This is the base class of all virtual data sources. A virtual data source
 * represents a remote data source (e.g. Google Search, Flickr, Yahoo...) that
 * can be accessed in a standardized way.
 * 
 * @author Malte
 * 
 */
public abstract class DB {
	public static String PARAM_MAX_CACHE_AGE = "MAX_CACHE_AGE";

	private static Cache cache = new CacheFile();
	protected ConfigDB config;
	protected int maxCacheAge = 3600;

	/**
	 * 
	 * @param filepath
	 *            the absolute path to the properties file
	 * @throws ExceptionMP
	 */
	public DB(String filepath) throws ExceptionMP {
		config = new ConfigDB(filepath);
		String temp = config.getValue(PARAM_MAX_CACHE_AGE);
		if (temp != null) {
			maxCacheAge = Integer.parseInt(temp.trim());
		}
	}

	/**
	 * Updates the content object with data from the remote data sources.
	 * 
	 * @param content
	 *            the content object to be updated
	 * @param start
	 *            the start position of the remote data set
	 * @param number
	 *            the number of records from the remote data set
	 * @throws ExceptionMP
	 */
	public abstract void fillIn(Content content, int start, int number)
			throws ExceptionMP;

	/**
	 * Returns the cache identifier of the content object
	 * 
	 * @param identDB
	 *            the identifier of the virtual data source
	 * @param content
	 *            the content object
	 * @param start
	 *            the start position of the remote data that has been stored in
	 *            the content object
	 * @param number
	 *            the number of records that has been stored in the content
	 *            object
	 * @return
	 * @throws ExceptionMP
	 */
	public static String identCache(String identDB, Content content, int start,
			int number) throws ExceptionMP {
		return identDB + "_" + content.getHashCode() + "_" + start + "_"
				+ number;
	}

	/**
	 * Updates a content object with data from the cache.
	 * 
	 * @param content
	 *            the content object
	 * @param identCache
	 *            the cache identifier
	 * @return <code>true</code> if cache data are available
	 * @throws ExceptionMP
	 */
	public boolean fillInFromCache(Content content, String identCache)
			throws ExceptionMP {
		try {
			Content contentCache = (Content) cache.getRecord(identCache,
					maxCacheAge);
			if (contentCache == null) {
				return false;
			} else {
				content.update(contentCache);
			}
		} catch (IOException e) {
			throw new ExceptionMP("Couldn't fetch '" + identCache
					+ "' from cache", e);
		}
		return true;
	}

	/**
	 * Stores a content object to the cache.
	 * 
	 * @param content
	 *            the content object to be cached
	 * @param identCache
	 *            the cache identifier of the content object
	 * @throws ExceptionMP
	 */
	public void storeToCache(Content content, String identCache)
			throws ExceptionMP {
		try {
			cache.put(identCache, content);
		} catch (IOException e) {
			throw new ExceptionMP("Couldn't store '" + identCache
					+ "' to cache", e);
		}
	}
}