package ch.ffhs.inf09.pa.mashup_platform.common.db;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

/**
 * This is a test class for the class User.
 * 
 * @author Alexander
 * 
 */
public class UserTest {

	@Test
	public void mainTest() {
		// initialize test variables
		String username = "username";
		String password = "password";
		Date lastLogin = new Date();

		// create user instance
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setLastLogin(lastLogin);

		// check values
		assertEquals(username, user.getUsername());
		assertEquals(password, user.getPassword());
		assertEquals(lastLogin, user.getLastLogin());
	}
}
