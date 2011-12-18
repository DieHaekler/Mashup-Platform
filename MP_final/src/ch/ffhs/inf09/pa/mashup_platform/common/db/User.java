package ch.ffhs.inf09.pa.mashup_platform.common.db;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Version;

/**
 * This class represents a registered user. Users are allowed to manage mashups.
 * 
 * @author Alexander
 * 
 */
public class User {
	@Id
	private Object id;

	@Version
	private Object version;

	private String username;
	private String password;
	private Date lastLogin;

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}