package ch.ffhs.inf09.pa.mashup_platform.common.db;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ch.ffhs.inf09.pa.mashup_platform.common.util.ExceptionMP;
import ch.ffhs.inf09.pa.mashup_platform.common.util.LoggerMP;
import ch.ffhs.inf09.pa.mashup_platform.config.Config;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.Content;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.ContentSection;

import com.orientechnologies.orient.client.remote.OEngineRemote;
import com.orientechnologies.orient.client.remote.OServerAdmin;
import com.orientechnologies.orient.core.Orient;
import com.orientechnologies.orient.core.db.object.ODatabaseObjectTx;
import com.orientechnologies.orient.core.exception.OCommandExecutionException;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.OCommandSQL;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;

/**
 * This wrapper class provides access to the Orient DB.
 * 
 * @author Alexander
 * 
 */
public class DBOrient extends DBLocal {
	private ODatabaseObjectTx dbMashups;
	private ODatabaseObjectTx dbUsers;
	private Config config;

	public DBOrient(String dbUsername, String dbPassword) throws ExceptionMP {
		super(dbUsername, dbPassword);
		config = Config.getInstance();
		Orient.instance().registerEngine(new OEngineRemote());
		createObjectDatabase(config.getValue(Config.PARAM_DB_MASHUPS));
		createObjectDatabase(config.getValue(Config.PARAM_DB_USERS));
		dbMashups = new ODatabaseObjectTx("remote:localhost/"
				+ config.getValue(Config.PARAM_DB_MASHUPS));
		dbUsers = new ODatabaseObjectTx("remote:localhost/"
				+ config.getValue(Config.PARAM_DB_USERS));
		dbMashups.open(dbUsername, dbPassword);
		dbUsers.open(dbUsername, dbPassword);
		dbMashups.getEntityManager().registerEntityClass(MashupPage.class);
		dbMashups.getEntityManager().registerEntityClass(Content.class);
		dbMashups.getEntityManager().registerEntityClass(ContentSection.class);
		dbUsers.getEntityManager().registerEntityClass(User.class);
		if (getUserCount() == 0) {
			User user = new User();
			user.setUsername(config.getValue(Config.PARAM_DB_USERNAME));
			user.setPassword(config.getValue(Config.PARAM_DB_PASSWORD));
			setUser(user);
		}
	}

	public void close() {
		dbMashups.close();
		dbUsers.close();
	}

	private static String addslashes(String text) {
		return text.replace("\'", "\\\'");
	}

	private static List<ODocument> query(ODatabaseObjectTx db, String query) {
		try {
			return db.query(new OSQLSynchQuery<ODocument>(query));
		} catch (OCommandExecutionException e) {
			LoggerMP.writeError("[DBOrient] couldn't execute: " + query);
		}
		return new ArrayList<ODocument>();
	}

	private List<MashupPage> queryDBMashups(String query) {
		try {
			return dbMashups.query(new OSQLSynchQuery<MashupPage>(query));
		} catch (OCommandExecutionException e) {
			LoggerMP.writeError("[DBOrient] couldn't execute: " + query);
		}
		return new ArrayList<MashupPage>();
	}

	private List<User> queryDBUsers(String query) {
		try {
			return dbMashups.query(new OSQLSynchQuery<Content>(query));
		} catch (OCommandExecutionException e) {
			LoggerMP.writeError("[DBOrient] couldn't execute: " + query);
		}
		return new ArrayList<User>();
	}

	public MashupInfo getInfo(String mashupIdent) {
		MashupInfo info = new MashupInfo(mashupIdent);
		String query = "select name, username, lastUpdated, createdAt from MashupPage where mashupIdent = '"
				+ addslashes(mashupIdent) + "' limit 1";
		List<ODocument> r2 = query(dbMashups, query);
		for (ODocument d2 : r2) {
			String name = (String) d2.field("name");
			String username = (String) d2.field("username");
			Date lastUpdated = (Date) d2.field("lastUpdated");
			Date createdAt = (Date) d2.field("createdAt");
			info.setName(name);
			info.setUsername(username);
			info.setLastUpdated(lastUpdated);
			info.setCreatedAt(createdAt);
		}
		query = "select max(pageNr) from MashupPage where mashupIdent = '"
				+ addslashes(mashupIdent) + "'";
		List<ODocument> r3 = query(dbMashups, query);
		for (ODocument d3 : r3) {
			try {
				int pageNr = (Integer) d3.field("max");
				info.setNumberPages(pageNr);
			} catch (NullPointerException e) {
			}
		}
		return info;
	}

	public MashupOverview getOverview(int start, int number, int sortedBy) {
		MashupOverview overview = new MashupOverview(sortedBy);
		if (dbMashups.getClusterType("MashupPage") != null) {
			String query = "select distinct(mashupIdent) from MashupPage";
			List<ODocument> r1 = query(dbMashups, query);
			for (ODocument d1 : r1) {
				String mashupIdent = (String) d1.field("distinct");
				overview.add(getInfo(mashupIdent));
			}
		}
		return overview;
	}

	public MashupPage getPage(String mashupIdent, int pageNr) {
		MashupPage page = null;
		if (dbMashups.getClusterType("MashupPage") != null) {
			String query = "select from MashupPage where mashupIdent = '"
					+ addslashes(mashupIdent) + "' and pageNr = '"
					+ addslashes("" + pageNr) + "'";
			List<MashupPage> results = queryDBMashups(query);
			if (results.size() > 0)
				page = results.get(0);
		}
		return page;
	}

	public void setPage(MashupPage page) {
		List<MashupPage> existingPages = null;
		if (page != null) {
			if (dbMashups.getClusterType("MashupPage") != null) {
				String query = "select from MashupPage where mashupIdent = '"
						+ addslashes(page.getMashupIdent())
						+ "' and pageNr = '"
						+ addslashes("" + page.getPageNr()) + "'";
				existingPages = queryDBMashups(query);
				for (MashupPage p : existingPages) {
					dbMashups.delete(p.getContent());
					dbMashups.delete(p);
				}
			}
			dbMashups.save(page);
		}
	}

	public User getUser(String username, String password) {
		User user = null;
		if (dbUsers.getClusterType("User") != null) {
			String query = "select from User where username = '"
					+ addslashes(username) + "' and password = '"
					+ addslashes(password) + "'";
			List<User> results = queryDBUsers(query);
			if (results.size() > 0)
				user = results.get(0);
		}
		return user;
	}

	public void setUser(User user) {
		if (user != null && dbUsers.getClusterType("User") != null) {
			String query = "select from User where username = '"
					+ addslashes(user.getUsername()) + "'";
			List<User> results = queryDBUsers(query);
			for (User u : results)
				dbUsers.delete(u);
			dbUsers.save(user);
		}
	}

	private void createObjectDatabase(String dbName) throws ExceptionMP {
		OServerAdmin oServer = null;
		try {
			oServer = new OServerAdmin("remote:localhost/" + dbName).connect(
					"root", getRootPassword());
			if (!oServer.existsDatabase()) {
				oServer.createDatabase("local").close();
			}
		} catch (IOException e) {
			throw new ExceptionMP("[DBOrient] couldn't create database '"
					+ dbName + "'", e);
		}
	}

	private long getUserCount() {
		long userNumber = 0;
		if (dbUsers.getClusterType("User") != null) {
			userNumber = dbUsers.countClusterElements("User");
		}
		return userNumber;
	}

	private String getRootPassword() throws ExceptionMP {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder;
		String password = null;
		String filepath = config.getValue(Config.PARAM_FILE_PATH_SYSTEM)
				+ config.getValue(Config.PARAM_DB_FOLDER_PATH_CONFIG)
				+ "orientdb-server-config.xml";
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(filepath);
			XPathFactory factoryX = XPathFactory.newInstance();
			XPath xpath = factoryX.newXPath();
			XPathExpression expr = xpath
					.compile("/orient-server/users/user[@name='root']/@password");
			Object result = expr.evaluate(doc, XPathConstants.NODESET);
			NodeList nodes = (NodeList) result;
			password = nodes.item(0).getNodeValue();
		} catch (ParserConfigurationException e) {
			throw new ExceptionMP("[DBOrient] couldn't parse " + filepath, e);
		} catch (SAXException e) {
			throw new ExceptionMP("[DBOrient] invalid XML: " + filepath, e);
		} catch (IOException e) {
			throw new ExceptionMP("[DBOrient] couldn't open " + filepath, e);
		} catch (XPathExpressionException e) {
			throw new ExceptionMP("[DBOrient] invalid XML: " + filepath, e);
		}
		return password;
	}
	
	public void clear(){
		clear(dbMashups, "MashupPage");
		clear(dbMashups, "ContentSection");
		clear(dbMashups, "Content");
		clear(dbUsers, "User");	
	}
	
	private void clear(ODatabaseObjectTx db, String className){
		if(db.getClusterType(className) != null){
			db.command(new OCommandSQL("delete from " + className)).execute();
		}
	}
}
