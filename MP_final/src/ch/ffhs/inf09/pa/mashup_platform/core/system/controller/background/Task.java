package ch.ffhs.inf09.pa.mashup_platform.core.system.controller.background;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import ch.ffhs.inf09.pa.mashup_platform.common.util.ExceptionMP;
import ch.ffhs.inf09.pa.mashup_platform.common.util.FileMP;
import ch.ffhs.inf09.pa.mashup_platform.config.Config;

/**
 * The base class defines the interface for background tasks.
 * 
 * @author Malte
 * 
 */
public abstract class Task implements Runnable {
	protected String folderpathStatus = Config.getFilepathSystem()
			+ "/output/status/";
	protected String folderpathConfig = Config.getFilepathSystem()
			+ "/config/system/";
	protected String ident;
	protected String filepathKill;
	protected Properties properties = new Properties();

	/**
	 * 
	 * @param ident
	 *            the unique ident of the task
	 * @throws ExceptionMP
	 */
	public Task(String ident) throws ExceptionMP {
		this.ident = ident;
		filepathKill = folderpathStatus + ident + ".kill";
		String filepath = folderpathConfig + ident + ".properties";
		try {
			properties.load(new FileInputStream(filepath));
		} catch (IOException e) {
			throw new ExceptionMP("[Bot] couldn't load " + filepath, e);
		}
	}

	protected boolean isKilled() {
		return FileMP.exists(filepathKill);
	}

	/**
	 * Kills the running task
	 * 
	 * @throws ExceptionMP
	 */
	public void kill() throws ExceptionMP {
		try {
			FileMP.write(filepathKill, "", false);
		} catch (IOException e) {
			throw new ExceptionMP("[Task] couldn't create " + filepathKill, e);
		}
	}

	protected void cleanup() {
		if (FileMP.exists(filepathKill))
			FileMP.remove(filepathKill);
	}

	protected static int parseInt(String text) {
		if (text == null)
			return 0;
		return Integer.parseInt(text.trim());
	}

}