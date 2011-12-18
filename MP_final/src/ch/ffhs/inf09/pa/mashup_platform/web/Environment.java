package ch.ffhs.inf09.pa.mashup_platform.web;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ch.ffhs.inf09.pa.mashup_platform.common.db.DBLocal;
import ch.ffhs.inf09.pa.mashup_platform.common.db.DBOrient;
import ch.ffhs.inf09.pa.mashup_platform.common.util.ExceptionMP;
import ch.ffhs.inf09.pa.mashup_platform.config.Config;

/**
 * The environment class takes care of the session handling and provides the
 * client request data. Furthermore it instantiates the database connection and
 * manages the account login/logout.
 * 
 * @author Alexander
 * 
 */
public class Environment {
	private HttpServletRequest request;
	private HttpSession session;
	private Config config;
	private DBLocal db;

	public Environment(HttpServletRequest request) throws ExceptionMP {
		this.request = request;
		session = request.getSession(true);
		ServletContext context = session.getServletContext();
		String filepath = context.getRealPath("");
		config = Config.getInstance(filepath + "/config/config.properties");
		db = new DBOrient(config.getValue(Config.PARAM_DB_USERNAME),
				config.getValue(Config.PARAM_DB_PASSWORD));
	}

	/**
	 * Processes the login and returns <code>true</code> if it was successful.
	 * 
	 * @param username the user name
	 * @param password the password
	 * @return
	 */
	public boolean login(String username, String password) {
		if (username != null && password != null && username.equals("admin")
				&& password.equals("admin")) {
			setUsername(username);
			return true;
		}
		return false;
	}

	public DBLocal getDB() {
		return db;
	}

	public void close() {
		db.close();
	}

	public void logout() {
		setUsername(null);
	}

	public String getUsername() {
		return (String) session.getAttribute("username");
	}

	public void setUsername(String username) {
		session.setAttribute("username", username);
	}

	public boolean isUserLoggedIn() {
		return getUsername() != null;
	}

	public String getValuePost(String name) {
		return request.getParameter(name);
	}

	public Config getConfig() {
		return config;
	}
}
