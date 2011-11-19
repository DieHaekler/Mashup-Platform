package ch.ffhs.inf09.pa.mashup_platform.core.system.model.db;

import ch.ffhs.inf09.pa.mashup_platform.core.system.model.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.cache.*;
import java.io.*;

public abstract class DB
{
	private static Cache cache = new CacheFile();
	
	public abstract void fillIn(Content content, int start, int number)
		throws ExceptionMP;
	
	public static String identCache(String identDB, Content content, int start, int number)
		throws ExceptionMP
	{
		return identDB + "_" + content.getHashCode() + "_" + start + "_" + number;
	}
	
	public boolean fillInFromCache(Content content, String identCache) throws ExceptionMP
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
			throw new ExceptionMP("Couldn't fetch '" + identCache + "' from cache", e);
		}
		return true;
	}
	
	public void storeToCache(Content content, String identCache) throws ExceptionMP
	{
		try
		{
			cache.put(identCache, content);
		} catch (IOException e)
		{
			throw new ExceptionMP("Couldn't store '" + identCache + "' to cache", e);
		}
	}
}