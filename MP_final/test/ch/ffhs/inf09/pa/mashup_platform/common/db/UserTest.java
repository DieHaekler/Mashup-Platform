package ch.ffhs.inf09.pa.mashup_platform.common.db;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class UserTest {

	private String username = "username";
	private String password = "password";
	private Date lastLogin = new Date();
	
	@Test
	public void mainTest(){
		User user = new User();	
		user.setUsername(username);
		user.setPassword(password);
		user.setLastLogin(lastLogin);
		assertEquals(username, user.getUsername());
		assertEquals(password, user.getPassword());
		assertEquals(lastLogin, user.getLastLogin());
	}	
}
