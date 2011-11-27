package ch.ffhs.inf09.pa.mashup_platform.core.system.model.persistence;

import ch.ffhs.inf09.pa.mashup_platform.core.system.model.Content;

public interface DBLocal {

	public void storeMashup(Content content);
	public String getMashupAsJSON(String caption);
	public boolean checkUsernameAndPassword(String username, String password);
	
}
