package ch.ffhs.inf09.pa.mashup_platform.core.system.model.cache;

import ch.ffhs.inf09.pa.mashup_platform.config.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import java.io.IOException;

public class CacheFile extends Cache
{
	private String filepath(String ident)
	{
		return Config.getInstance().getValue(Config.PARAM_FILE_PATH_SYSTEM) + "/cache/"
			+ ident + ".cache";
	}
	
	public void put(String ident, Object obj) throws IOException
	{
		FileMP.write(filepath(ident), obj);
	}
	
	public Object get(String ident) throws IOException
	{
		if ( !FileMP.exists(filepath(ident)) ) return null;
		try
		{
			return FileMP.get(filepath(ident));
		} catch (ClassNotFoundException e)
		{
			throw new IOException("[CacheFile] corrupted file: " + filepath(ident), e);
		}
	}
	
	public void remove(String ident) throws IOException
	{
		FileMP.remove(filepath(ident));
	}
	
	public long getTimestamp(String ident) throws IOException
	{
		return FileMP.getTimestamp(filepath(ident));
	}
}