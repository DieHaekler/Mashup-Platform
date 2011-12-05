package ch.ffhs.inf09.pa.mashup_platform.config;

import ch.ffhs.inf09.pa.mashup_platform.common.util.*;

import java.io.*;
import java.util.*;

public class Config
{
	public static final String PARAM_FILE_PATH_SYSTEM = "FILE_PATH_SYSTEM";
	public static final String PARAM_WEB_PATH_ROOT = "WEB_PATH_ROOT";
	public static final String PARAM_DB_FOLDER_PATH = "DB_FOLDER_PATH";
	public static final String PARAM_DB_FOLDER_PATH_CONFIG = "DB_FOLDER_PATH_CONFIG";
	public static final String PARAM_DB_USERNAME = "DB_USERNAME";
	public static final String PARAM_DB_PASSWORD = "DB_PASSWORD";
	public static final String PARAM_DB_USERS = "DB_USERS";
	public static final String PARAM_DB_MASHUPS = "DB_MASHUPS";
	public static final String PARAM_DB_USERS_CLASS_NAME = "DB_USERS_CLASS_NAME";
	public static final String PARAM_DB_MASHUPS_CONTENT_CLASS_NAME = "DB_MASHUPS_CONTENT_CLASS_NAME";
	public static final String PARAM_DB_MASHUPS_MASHUP_CLASS_NAME = "DB_MASHUPS_MASHUP_CLASS_NAME";
	
	private Properties properties = new Properties();
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
		return getInstance(new File(".").getAbsolutePath() + "/config/config.properties");
	}
	
	private Config(String filepath) throws ExceptionMP
	{
		try
		{
			properties.load(new FileInputStream(filepath));
		} catch (IOException e)
		{
			throw new ExceptionMP("[Config] couldn't load " + filepath, e);
		}
	}
	
	public String getValue(String ident)
	{
		return properties.getProperty(ident);
	}
	
	public static String getFilepathVar()
	{
		String filepath = getFilepathSystem();
		if (filepath == null) return null;
		return filepath + "/src/ch/ffhs/inf09/pa/mashup_platform/var/";
	}
	
	public static String getFilepathSystem()
	{
		if (instance == null) instance = getInstance();
		return instance.getValue(Config.PARAM_FILE_PATH_SYSTEM);
	}
}