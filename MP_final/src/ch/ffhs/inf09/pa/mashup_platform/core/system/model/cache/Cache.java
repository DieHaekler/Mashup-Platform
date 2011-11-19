package ch.ffhs.inf09.pa.mashup_platform.core.system.model.cache;

import java.io.*;
import ch.ffhs.inf09.pa.mashup_platform.config.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;

public abstract class Cache
{
	public abstract void put(String ident, Object obj) throws IOException;
	public abstract void remove(String ident) throws IOException;
	public abstract long getTimestamp(String ident) throws IOException;
	protected abstract Object get(String ident) throws IOException;
	
	public Object getRecord(String ident) throws IOException
	{
		Object obj = get(ident);
		if (obj == null)
		{
			LoggerMP.writeNotice("no cache entry found for '" + ident + "'");
			return null;
		} else
		{
			int age = (int)(System.currentTimeMillis()/1000 - getTimestamp(ident));
			LoggerMP.writeNotice("cache entry found (ident: '" + ident
				+ ", age: " + age + " sec)");
			if (age > Config.MAX_CACHE_TIME_IN_SECONDS)
			{
				remove(ident);
				LoggerMP.writeNotice("cache entry '" + ident + "' has expired");
				return null;
			} else
			{
				LoggerMP.writeNotice("cache entry '" + ident + "' is still valid");
				return obj;
			}
		}
	}
}