package ch.ffhs.inf09.pa.mashup.system.model.cache;

import ch.ffhs.inf09.pa.mashup.config.Config;
import ch.ffhs.inf09.pa.mashup.system.util.*;
import java.io.IOException;

public class CacheFile extends Cache
{
	private String filepath(String ident)
	{
		return Config.FILE_PATH_CACHE + ident + ".cache";
	}
	
	public void put(String ident, Object obj) throws IOException
	{
		FileMashup.write(filepath(ident), obj);
	}
	
	public Object get(String ident) throws IOException
	{
		if ( !FileMashup.exists(filepath(ident)) ) return null;
		try
		{
			return FileMashup.get(filepath(ident));
		} catch (ClassNotFoundException e)
		{
			throw new IOException("corrupted file: " + filepath(ident), e);
		}
	}
	
	public void remove(String ident) throws IOException
	{
		FileMashup.remove(filepath(ident));
	}
	
	public long getTimestamp(String ident) throws IOException
	{
		return FileMashup.getTimestamp(filepath(ident));
	}
}