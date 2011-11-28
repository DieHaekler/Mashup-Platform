package ch.ffhs.inf09.pa.mashup_platform.core.system.model.persistence;

import java.util.List;

import ch.ffhs.inf09.pa.mashup_platform.core.system.model.Content;

public interface DBLocal {

	public void storeMashup(Content content);
	public Content getMashup(String caption);
	public String getMashupJSON(String caption);
	public boolean checkUsernameAndPassword(String username, String password);
	public List<String> getMashupsFromUserJSON(String username);
	public List<Content> getMashupsFromUser (String username);
	public List<Content> getAllMashups();
	public List<String> getAllMashupsJSON();
	public void closeConnections();
	
}
