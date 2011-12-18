package ch.ffhs.inf09.pa.mashup_platform.web.model;

import ch.ffhs.inf09.pa.mashup_platform.common.db.User;
import ch.ffhs.inf09.pa.mashup_platform.web.Environment;

/**
 * The model class provides user data.
 * 
 * @author Joël
 * 
 */
public class ModelUser extends ModelApplication {
	private User user;

	public ModelUser(Environment environment, String username, String password) {
		super(environment);
		// user = db.getUser(username, password);
	}

	public boolean doesExist() {
		return user != null;
	}

	public User getUser() {
		return user;
	}
}