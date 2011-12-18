package ch.ffhs.inf09.pa.mashup_platform.core.system.config;

import ch.ffhs.inf09.pa.mashup_platform.common.util.ExceptionMP;

/**
 * The DB config class provides read access to the DB config parameters that are
 * defined in a properties file.
 * 
 * @author Malte
 * 
 */
public class ConfigDB extends ConfigMashup {
	public ConfigDB(String filepath) throws ExceptionMP {
		super(filepath);
	}
}