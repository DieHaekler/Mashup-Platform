package ch.ffhs.inf09.pa.mashup_platform.core.system.config;

import java.util.*;
import java.io.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;

public class ConfigMashup
{
	protected Properties properties = new Properties();
	
	public ConfigMashup(String filepath) throws ExceptionMP
	{
		try
		{
			properties.load(new FileInputStream(filepath));
		} catch (IOException e)
		{
			throw new ExceptionMP("[ConfigMashup] couldn't load " + filepath, e);
		}
	}
	
	public String getValue(String ident)
	{
		return properties.getProperty(ident);
	}
}