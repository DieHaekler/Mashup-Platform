package ch.ffhs.inf09.pa.mashup_platform.common.db;

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import com.orientechnologies.orient.client.remote.*;
import com.orientechnologies.orient.core.*;
import com.orientechnologies.orient.core.db.object.*;
import com.orientechnologies.orient.core.record.impl.*;
import com.orientechnologies.orient.core.sql.query.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.*;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.*;

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
		dbMashups = new ODatabaseObjectTx("remote:localhost/" + config.getValue(Config.PARAM_DB_MASHUPS));
		dbUsers = new ODatabaseObjectTx("remote:localhost/" + config.getValue(Config.PARAM_DB_USERS));
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
	
	public MashupOverview getOverview(int start, int number, int sortedBy)
	{
		MashupOverview overview = new MashupOverview(sortedBy);
		if (dbMashups.getClusterType("MashupPage") != null)
		{
			String query = "select distinct(mashupIdent) from MashupPage";
			List<ODocument> r1 = dbMashups.query(new OSQLSynchQuery<ODocument>(query));
			for (ODocument d1: r1)
			{
				String mashupIdent = (String)d1.field("distinct");
				MashupInfo info = new MashupInfo(mashupIdent);
				query = "select name, username, lastUpdated, createdAt from MashupPage where mashupIdent = '"
					+ mashupIdent + "' limit 1";
				List<ODocument> r2 = dbMashups.query(new OSQLSynchQuery<ODocument>(query));
				for (ODocument d2: r2)
				{
					String name = (String)d2.field("name");
					String username = (String)d2.field("username");
					Date lastUpdated = (Date)d2.field("lastUpdated");
					Date createdAt = (Date)d2.field("createdAt");
					info.setName(name);
					info.setUsername(username);
					info.setLastUpdated(lastUpdated);
					info.setCreatedAt(createdAt);
				}
				query = "select max(pageNr) from MashupPage where mashupIdent = '"
					+ mashupIdent + "'";
				List<ODocument> r3 = dbMashups.query(new OSQLSynchQuery<ODocument>(query));
				for (ODocument d3: r3)
				{
					int pageNr = (Integer)d3.field("max");
					info.setNumberPages(pageNr);
				}
				overview.add(info);
			}
		}
		return overview;
	}

	public MashupPage getPage(String mashupIdent, int pageNr) {
		MashupPage page = null;
		if (dbMashups.getClusterType("MashupPage") != null) {
			String query = "select from MashupPage where mashupIdent = '"
				+ mashupIdent + "' and pageNr = '" + pageNr + "'";
			List<MashupPage> results = dbMashups.query(new OSQLSynchQuery<MashupPage>(query));
			if (results.size() > 0) page = results.get(0);
		}
		return page;
	}

	public void setPage(MashupPage page) {
		List<MashupPage> existingPages = null;
		if (page != null) {
			if(dbMashups.getClusterType("MashupPage") != null) {
				String query = "select from MashupPage where mashupIdent = '"
					+ page.getMashupIdent() + "' and pageNr = '" + page.getPageNr() + "'";
				existingPages = dbMashups.query(new OSQLSynchQuery<MashupPage>(query));
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
			List<User> results = dbUsers.query(new OSQLSynchQuery<Content>(
					"select from User where username = '" + username
							+ "' and password = '" + password + "'"));
			if (results.size() > 0) user = results.get(0);
		}
		return user;
	}

	public void setUser(User user) {
		if (user != null
				&& dbUsers.getClusterType("User") != null) {
			List<User> results = dbUsers.query(new OSQLSynchQuery<Content>(
				"select from User where username = '" + user.getUsername() + "'"));
			for (User u : results) dbUsers.delete(u);
			dbUsers.save(user);
		}
	}

	private void createObjectDatabase(String dbName) throws ExceptionMP {
		OServerAdmin oServer = null;
		try {
			oServer = new OServerAdmin("remote:localhost/"
				+ dbName).connect("root", getRootPassword());
			if(!oServer.existsDatabase()){
				oServer.createDatabase("local").close();
			}
		} catch (IOException e) {
			throw new ExceptionMP("[DBOrient] could'nt create " + dbName, e);
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
            + config.getValue(Config.PARAM_DB_FOLDER_PATH_CONFIG) + "orientdb-server-config.xml";
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(filepath);
            XPathFactory factoryX = XPathFactory.newInstance();
            XPath xpath = factoryX.newXPath();
            XPathExpression expr = xpath.compile("/orient-server/users/user[@name='root']/@password");
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
 	
}
