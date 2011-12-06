package ch.ffhs.inf09.pa.mashup_platform.core.system.model;

import java.util.*;
import java.io.*;
import ch.ffhs.inf09.pa.mashup_platform.common.db.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.*;

public class MashupManager
{
	private static ArrayList<MashupInfo> getMashupInfos() throws ExceptionMP
	{
		ArrayList<MashupInfo> mashupInfos = new ArrayList<MashupInfo>();
		ArrayList<String> foldernames
			= FileMP.getFilenames(Config.getFilepathVar(), "");
		for (String foldername: foldernames)
		{
			String filepath = Config.getFilepathVar() + "/" + foldername
				+ "/config/config.properties";
			if ( FileMP.exists(filepath) )
			{
				mashupInfos.add(new MashupInfo(filepath));
			}
		}
		return mashupInfos;
	}
	
	public static synchronized MashupInfo pick(int minTimeInterval) throws ExceptionMP
	{
		ArrayList<MashupInfo> candidates = new ArrayList<MashupInfo>();
		ArrayList<MashupInfo> list = getMashupInfos();
		for (MashupInfo mashupInfo: list)
		{
			if ((System.currentTimeMillis()/1000 - mashupInfo.getLastProcessed()) > minTimeInterval)
			{
				candidates.add(mashupInfo);
			}
		}
		if (candidates.size() > 0)
		{
			Random generator = new Random();
			int index = generator.nextInt(candidates.size());
			return candidates.get(index);
		}
		return null;
	}
	
	public static synchronized void store(MashupPage page) throws ExceptionMP
	{
		String folderpathOutput = Config.getFilepathSystem() + "/output/";
		page.setLastUpdated(new Date());
		Content content = page.getContent();
		String hash = content.getHashCode();
		String filename = page.getMashupIdent() + "_" + page.getPageNr() + "_" + hash + ".output";
		String filepath = folderpathOutput + filename;
		if ( !FileMP.exists(filepath) &&
			!FileMP.exists(folderpathOutput + "done/" + filename) )
		{
			try
			{
				FileMP.write(filepath, page);
			} catch (IOException e)
			{
				throw new ExceptionMP("[MashupManager] couldn't create " + filepath, e);
			}
		}
	}
}