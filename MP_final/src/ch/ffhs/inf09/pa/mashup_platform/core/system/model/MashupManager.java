package ch.ffhs.inf09.pa.mashup_platform.core.system.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import ch.ffhs.inf09.pa.mashup_platform.common.db.MashupPage;
import ch.ffhs.inf09.pa.mashup_platform.common.util.ExceptionMP;
import ch.ffhs.inf09.pa.mashup_platform.common.util.FileMP;
import ch.ffhs.inf09.pa.mashup_platform.config.Config;

/**
 * The MashupManager is a helper class of the Bot to retrieve meta information
 * about mashups. Furthermore it provides a buffer to store the output of the
 * processed mashups.
 * 
 * @author Malte
 * 
 */
public class MashupManager {
	private static ArrayList<MashupInfo> getMashupInfos() throws ExceptionMP {
		ArrayList<MashupInfo> mashupInfos = new ArrayList<MashupInfo>();
		ArrayList<String> foldernames = FileMP.getFilenames(
				Config.getFilepathVar(), "");
		for (String foldername : foldernames) {
			String filepath = Config.getFilepathVar() + "/" + foldername
					+ "/config/config.properties";
			if (FileMP.exists(filepath)) {
				mashupInfos.add(new MashupInfo(filepath));
			}
		}
		return mashupInfos;
	}

	/**
	 * Returns information about a randomly picked mashup. The picked mashup is
	 * the candidate that gets processed next by the Bot.
	 * 
	 * @param minTimeInterval
	 *            the minimum time interval that has to be elapsed before the
	 *            same mashup can be picked
	 * @return the MashupInfo object of the picked mashup
	 * @throws ExceptionMP
	 */
	public static synchronized MashupInfo pick(int minTimeInterval)
			throws ExceptionMP {
		ArrayList<MashupInfo> candidates = new ArrayList<MashupInfo>();
		ArrayList<MashupInfo> list = getMashupInfos();
		for (MashupInfo mashupInfo : list) {
			if ((System.currentTimeMillis() / 1000 - mashupInfo
					.getLastProcessed()) > minTimeInterval) {
				candidates.add(mashupInfo);
			}
		}
		if (candidates.size() > 0) {
			Random generator = new Random();
			int index = generator.nextInt(candidates.size());
			return candidates.get(index);
		}
		return null;
	}

	/**
	 * Stores a mashup page to an internal buffer. This buffer gets processed by
	 * the DBFeeder (= background task) to store the page to the local database.
	 * 
	 * @param page
	 *            the mashup page to be stored
	 * @throws ExceptionMP
	 */
	public static synchronized void store(MashupPage page) throws ExceptionMP {
		String folderpathOutput = Config.getFilepathSystem() + "/output/";
		page.setLastUpdated(new Date());
		Content content = page.getContent();
		String hash = content.getHashCode();
		String filename = page.getMashupIdent() + "_" + page.getPageNr() + "_"
				+ hash + ".output";
		String filepath = folderpathOutput + filename;
		if (!FileMP.exists(filepath)
				&& !FileMP.exists(folderpathOutput + "done/" + filename)) {
			try {
				FileMP.write(filepath, page);
			} catch (IOException e) {
				throw new ExceptionMP("[MashupManager] couldn't create "
						+ filepath, e);
			}
		}
	}
}