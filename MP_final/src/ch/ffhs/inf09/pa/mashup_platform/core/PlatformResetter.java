package ch.ffhs.inf09.pa.mashup_platform.core;

import java.util.ArrayList;

import ch.ffhs.inf09.pa.mashup_platform.common.util.FileMP;
import ch.ffhs.inf09.pa.mashup_platform.common.util.LoggerMP;
import ch.ffhs.inf09.pa.mashup_platform.config.Config;

/**
 * The PlatformResetter resets the Mashup Platform. This includes the clean up
 * of the local database, the buffer and the cache.
 * 
 * @author Joël
 * 
 */
public class PlatformResetter {
	public static void main(String[] args) {
		Config config = Config.getInstance();

		// remove databases
		String folderpath = Config.getFilepathSystem() + "/"
				+ config.getValue(Config.PARAM_DB_FOLDER_PATH) + "/";
		String folderpathUsers = folderpath
				+ config.getValue(Config.PARAM_DB_USERS);
		String folderpathMashups = folderpath
				+ config.getValue(Config.PARAM_DB_MASHUPS);
		if (FileMP.remove(folderpathUsers)) {
			LoggerMP.writeNotice("[PlatformResetter] removed '"
					+ folderpathUsers + "'");
		}
		if (FileMP.remove(folderpathMashups)) {
			LoggerMP.writeNotice("[PlatformResetter] removed '"
					+ folderpathMashups + "'");
		}

		// remove cache
		folderpath = Config.getFilepathSystem() + "/cache/";
		remove(folderpath, "\\.cache$");

		// remove output
		folderpath = Config.getFilepathSystem() + "/output/";
		String folderpathQueue = folderpath + "queue/";
		String folderpathDone = folderpath + "done/";
		remove(folderpath, "\\.output$");
		remove(folderpathQueue, "\\.output$");
		remove(folderpathDone, "\\.output$");

		// remove status
		folderpath += "status/";
		remove(folderpath, "\\.status$");
	}

	private static void remove(String folderpath, String regex) {
		ArrayList<String> names = FileMP.getFilenames(folderpath, regex);
		for (String name : names) {
			if (FileMP.remove(folderpath + name)) {
				LoggerMP.writeNotice("[PlatformResetter] removed '"
						+ folderpath + name + "'");
			}
		}
	}
}