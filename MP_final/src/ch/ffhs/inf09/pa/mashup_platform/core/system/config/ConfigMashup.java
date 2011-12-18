package ch.ffhs.inf09.pa.mashup_platform.core.system.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import ch.ffhs.inf09.pa.mashup_platform.common.util.ExceptionMP;

/**
 * The mashup config class provides read access to the mashup config parameters
 * that are defined in a properties file.
 * 
 * @author Malte
 * 
 */
public class ConfigMashup {
	protected Properties properties = new Properties();

	public ConfigMashup(String filepath) throws ExceptionMP {
		try {
			properties.load(new FileInputStream(filepath));
		} catch (IOException e) {
			throw new ExceptionMP("[ConfigMashup] couldn't load " + filepath, e);
		}
	}

	public String getValue(String ident) {
		return properties.getProperty(ident);
	}
}