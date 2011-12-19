package ch.ffhs.inf09.pa.mashup_platform.config;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConfigTest{
	
	@Test
	public void mainTest(){
		Config config = Config.getInstance();
		assertNotNull(config);
		assertNotNull(config.getValue(Config.PARAM_DB_FOLDER_PATH));
		assertNotNull(config.getValue(Config.PARAM_DB_FOLDER_PATH_CONFIG));
		assertNotNull(config.getValue(Config.PARAM_DB_MASHUPS));
		assertNotNull(config.getValue(Config.PARAM_DB_PASSWORD));
		assertNotNull(config.getValue(Config.PARAM_DB_USERNAME));
		assertNotNull(config.getValue(Config.PARAM_DB_USERS));
		assertNotNull(config.getValue(Config.PARAM_FILE_PATH_SYSTEM));
		assertNotNull(config.getValue(Config.PARAM_WEB_PATH_ROOT));
		assertEquals(config.getValue(Config.PARAM_FILE_PATH_SYSTEM), Config.getFilepathSystem());
		assertEquals(config.getValue(Config.PARAM_FILE_PATH_SYSTEM) + "/src/ch/ffhs/inf09/pa/mashup_platform/var/", Config.getFilepathVar());		
	}
}
