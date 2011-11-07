package ch.ffhs.inf09.pa.mashup.system.model.db;

import ch.ffhs.inf09.pa.mashup.system.model.*;
import ch.ffhs.inf09.pa.mashup.system.util.*;
import ch.ffhs.inf09.pa.mashup.system.model.cache.*;
import java.io.*;

public abstract class DB
{
	private static Cache cache = new CacheFile();
	
	public abstract void fillIn(Content content, int start, int number)
		throws ExceptionMashup;
	
	public static String identCache(String identDB, Content content, int start, int number)
		throws ExceptionMashup
	{
		return identDB + "_" + content.getHashCode() + "_" + start + "_" + number;
	}
	
	public boolean fillInFromCache(Content content, String identCache) throws ExceptionMashup
	{
		try
		{
			Content contentCache = (Content)cache.getRecord(identCache);
			if (contentCache == null)
			{
				return false;
			} else
			{
				content.update(contentCache);
			}
		} catch (IOException e)
		{
			throw new ExceptionMashup("Couldn't fetch '" + identCache + "' from cache", e);
		}
		return true;
	}
	
	public void storeToCache(Content content, String identCache) throws ExceptionMashup
	{
		try
		{
			cache.put(identCache, content);
		} catch (IOException e)
		{
			throw new ExceptionMashup("Couldn't store '" + identCache + "' to cache", e);
		}
	}
}