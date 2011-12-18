package ch.ffhs.inf09.pa.mashup_platform.core.system.controller.background;

import java.io.IOException;
import java.util.ArrayList;

import ch.ffhs.inf09.pa.mashup_platform.common.db.DBLocal;
import ch.ffhs.inf09.pa.mashup_platform.common.db.DBOrient;
import ch.ffhs.inf09.pa.mashup_platform.common.db.MashupPage;
import ch.ffhs.inf09.pa.mashup_platform.common.util.ExceptionMP;
import ch.ffhs.inf09.pa.mashup_platform.common.util.FileMP;
import ch.ffhs.inf09.pa.mashup_platform.common.util.LoggerMP;
import ch.ffhs.inf09.pa.mashup_platform.config.Config;

/**
 * The DBFeeder is a background process that stores the buffered mashup output
 * to the local database.
 * 
 * @author Malte
 * 
 */
public class DBFeeder extends Task {
	public static final String PARAM_SLEEP_TIME = "SLEEP_TIME";

	public DBFeeder() throws ExceptionMP {
		super("db_feeder");
	}

	public void run() {
		cleanup();
		LoggerMP.writeNotice("[DBFeeder] DB Feeder started");
		int sleepTime = parseInt(properties.getProperty(PARAM_SLEEP_TIME));
		if (sleepTime <= 0)
			sleepTime = 10000;
		while (!isKilled()) {
			process();
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
			}
		}
		LoggerMP.writeNotice("[DBFeeder] DB Feeder killed");
		cleanup();
	}

	private static synchronized void process() {
		String folderpathOutput = Config.getFilepathSystem() + "/output/";
		ArrayList<String> filenames = FileMP.getFilenames(folderpathOutput,
				"\\.output$");
		DBLocal db = null;
		if (filenames.size() > 0) {
			Config config = Config.getInstance();
			try {
				db = new DBOrient(config.getValue(Config.PARAM_DB_USERNAME),
						config.getValue(Config.PARAM_DB_PASSWORD));
			} catch (ExceptionMP e) {
				LoggerMP.writeError(e);
			}
		}
		if (db != null) {
			for (String filename : filenames) {
				String filepath = folderpathOutput + "/" + filename;
				String filepathQueue = folderpathOutput + "/queue/" + filename;
				String filepathDone = folderpathOutput + "/done/" + filename;
				if (FileMP.move(filepath, filepathQueue, true)) {
					try {
						MashupPage page = (MashupPage) FileMP
								.get(filepathQueue);
						if (page != null) {
							db.setPage(page);
							LoggerMP.writeNotice("[DBFeeder] Mashup '"
									+ page.getMashupIdent() + "' (page: "
									+ page.getPageNr() + ") stored to DB");
							FileMP.move(filepathQueue, filepathDone, true);
						}
					} catch (IOException e) {
						LoggerMP.writeError("[DBFeeder] couldn't read "
								+ filepathQueue);
					} catch (ClassNotFoundException e) {
						LoggerMP.writeError("[DBFeeder] couldn't find "
								+ filepathQueue);
					}
				}
			}
			db.close();
		}

		// check stuck queue files
		filenames = FileMP.getFilenames(folderpathOutput + "/queue/",
				"\\.output$");
		for (String filename : filenames) {
			String filepathQueue = folderpathOutput + "/queue/" + filename;
			String filepath = folderpathOutput + "/" + filename;
			int age = (int) (System.currentTimeMillis() / 1000 - FileMP
					.getTimestamp(filepathQueue));
			if (age > 120)
				FileMP.move(filepathQueue, filepath, true);
		}
	}

}