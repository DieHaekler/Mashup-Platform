package ch.ffhs.inf09.pa.mashup_platform.core.system.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import ch.ffhs.inf09.pa.mashup_platform.common.util.ExceptionMP;
import ch.ffhs.inf09.pa.mashup_platform.common.util.FileMP;
import ch.ffhs.inf09.pa.mashup_platform.config.Config;

/**
 * The MashupInfo class contains some meta information about a specific mashup.
 * 
 * @author Malte
 * 
 */
public class MashupInfo {
	private String ident;
	private int status;
	private int pageNrProcessed = -1;
	private long lastProcessed;
	private int maxNumberPages;
	private String filepathStatus;

	/**
	 * 
	 * @param filepath
	 *            the absolute path to the properties file
	 * @throws ExceptionMP
	 */
	public MashupInfo(String filepath) throws ExceptionMP {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(filepath));
			ident = properties.getProperty(Model.PARAM_IDENT);
			status = parseInt(properties.getProperty(Model.PARAM_STATUS));
			maxNumberPages = parseInt(properties
					.getProperty(Model.PARAM_MAX_NUMBER_PAGES));
		} catch (IOException e) {
			throw new ExceptionMP("[MashupInfo] couldn't read " + filepath, e);
		}
		filepathStatus = Config.getFilepathSystem() + "/output/status/" + ident
				+ "_processed.status";
		if (FileMP.exists(filepathStatus)) {
			lastProcessed = FileMP.getTimestamp(filepathStatus);
			try {
				pageNrProcessed = parseInt(FileMP.getContent(filepathStatus)
						.trim());
			} catch (FileNotFoundException e) {
				throw new ExceptionMP("[MashupInfo] couldn't find "
						+ filepathStatus, e);
			} catch (IOException e) {
				throw new ExceptionMP("[MashupInfo] couldn't read "
						+ filepathStatus, e);
			}
		}
	}

	public void setPageNrProcessed(int pagenr) throws ExceptionMP {
		try {
			FileMP.write(filepathStatus, pagenr + "", false);
		} catch (IOException e) {
			throw new ExceptionMP("[MashupInfo] couldn't write to "
					+ filepathStatus, e);
		}
	}

	private static int parseInt(String text) {
		if (text == null)
			return 0;
		return Integer.parseInt(text.trim());
	}

	public String getIdent() {
		return ident;
	}

	public int getStatus() {
		return status;
	}

	public int getPageNrProcessed() {
		return pageNrProcessed;
	}

	public long getLastProcessed() {
		return lastProcessed;
	}

	public int getMaxNumberPages() {
		return maxNumberPages;
	}
}