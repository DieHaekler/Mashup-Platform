package ch.ffhs.inf09.pa.mashup_platform.config;

import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Config
{
	public static final String PARAM_FILE_PATH_SYSTEM = "FILE_PATH_SYSTEM";
	public static final String PARAM_WEB_PATH_ROOT = "WEB_PATH_ROOT";
	public static final String PARAM_DB_FOLDER_PATH = "DB_FOLDER_PATH";
	public static final String PARAM_DB_USERNAME = "DB_USERNAME";
	public static final String PARAM_DB_PASSWORD = "DB_PASSWORD";
	public static final String PARAM_DB_USERS = "DB_USERS";
	public static final String PARAM_DB_MASHUPS = "DB_MASHUPS";
	public static final String PARAM_DB_USERS_CLASS_NAME = "DB_USERS_CLASS_NAME";
	public static final String PARAM_DB_MASHUPS_CONTENT_CLASS_NAME = "DB_MASHUPS_CONTENT_CLASS_NAME";
	public static final String PARAM_DB_MASHUPS_MASHUP_CLASS_NAME = "DB_MASHUPS_MASHUP_CLASS_NAME";
	
	HashMap<String, String>map = new HashMap<String, String>();
	private static Config instance;
	
	public static Config getInstance(String filepath)
	{
		if (instance == null)
		try
		{
			instance = new Config(filepath);
		} catch (ExceptionMP e)
		{
			e.printStackTrace();
		}
		return instance;
	}
	
	public static Config getInstance()
	{
		return getInstance(new File(".").getAbsolutePath() + "/config");
	}
	
	private Config(String filepath) throws ExceptionMP
	{
		try
		{
			String settings = FileMP.getContent(filepath + "/config.txt");
			map.put(PARAM_FILE_PATH_SYSTEM, value(PARAM_FILE_PATH_SYSTEM, settings));
			map.put(PARAM_WEB_PATH_ROOT, value(PARAM_WEB_PATH_ROOT, settings));
			map.put(PARAM_DB_FOLDER_PATH, value(PARAM_DB_FOLDER_PATH, settings));
			map.put(PARAM_DB_USERNAME, value(PARAM_DB_USERNAME, settings));
			map.put(PARAM_DB_PASSWORD, value(PARAM_DB_PASSWORD, settings));
			map.put(PARAM_DB_USERS, value(PARAM_DB_USERS, settings));
			map.put(PARAM_DB_MASHUPS, value(PARAM_DB_MASHUPS, settings));
			map.put(PARAM_DB_USERS_CLASS_NAME, value(PARAM_DB_USERS_CLASS_NAME, settings));
			map.put(PARAM_DB_MASHUPS_CONTENT_CLASS_NAME, value(PARAM_DB_MASHUPS_CONTENT_CLASS_NAME, settings));
			map.put(PARAM_DB_MASHUPS_MASHUP_CLASS_NAME, value(PARAM_DB_MASHUPS_MASHUP_CLASS_NAME, settings));
		} catch (FileNotFoundException e)
		{
			throw new ExceptionMP("Couldn't find " + filepath + "config.txt", e);
		} catch (IOException e)
		{
			throw new ExceptionMP("Couldn't open " + filepath + "config.txt", e);
		}
	}
	
	public String getValue(String ident)
	{
		return map.get(ident);
	}
	
	public static String value(String ident, String content)
	{
		String regex = ident + ":\\s*([^\\n]+)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(content);
		if ( matcher.find() ) return matcher.group(1);
		return null;
	}
}