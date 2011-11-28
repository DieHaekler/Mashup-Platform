package ch.ffhs.inf09.pa.mashup_platform.core.system.model.persistence;

import java.util.ArrayList;
import java.util.List;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.Content;
import com.orientechnologies.orient.client.remote.OEngineRemote;
import com.orientechnologies.orient.core.Orient;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentPool;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.db.object.ODatabaseObjectPool;
import com.orientechnologies.orient.core.db.object.ODatabaseObjectTx;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;

public class DBOrient implements DBLocal {

	private String username;
	private String password;
	private String filePath;
	private String dbMashupsName;
	private String dbMashupsClassName;
	private String dbUsersName;
	private String dbUsersClassName;
	private ODatabaseObjectTx dbMashups;
	private ODatabaseDocumentTx dbUsers;

	public DBOrient(String username, String password, String filePath,
			String dbMashupsName, String dbMashupsClassName,
			String dbUsersName, String dbUsersClassName) {
		this.username = username;
		this.password = password;
		this.filePath = filePath;
		this.dbMashupsName = dbMashupsName;
		this.dbMashupsClassName = dbMashupsClassName;
		this.dbUsersName = dbUsersName;
		this.dbUsersClassName = dbUsersClassName;

		Orient.instance().registerEngine(new OEngineRemote());
		createObjectDatabase(this.dbMashupsName);
		createDocumentDatabase(this.dbUsersName);
	
		dbMashups = ODatabaseObjectPool.global().acquire("remote:localhost/" + this.dbMashupsName, this.username, this.password); 
		dbUsers = ODatabaseDocumentPool.global().acquire("remote:localhost/" + this.dbUsersName, this.username, this.password);
		
		createMashupUser(this.username, this.password);
	}

	@Override
	public void storeMashup(Content content) {
		//ODatabaseObjectTx db = openRemoteConnectionObjectDB(this.dbMashupsName);
		dbMashups.getEntityManager().registerEntityClass(
				Content.class);
		if (dbMashups.getClusterType(this.dbMashupsClassName) != null) {
			List<Content> results = dbMashups
					.query(new OSQLSynchQuery<Content>("select * from "
							+ this.dbMashupsClassName + " where caption = '"
							+ content.getCaption() + "'"));
			for (int i = 0; i < results.size(); i++) {
				deleteMashup(results.get(i));
			}
		}

		dbMashups.save(content);
	}

	public void deleteMashup(Content content) {
		if (content == null) {
			return;
		}
		
		List<Content> results = dbMashups.query(new OSQLSynchQuery<Content>(
				"select * from " + this.dbMashupsClassName
						+ " where caption = '" + content.getCaption() + "'"));
		for (int i = 0; i < results.get(0).getChildren().size(); i++) {
			Content cont = new Content();
			cont.setId(results.get(0).getChildren().get(0).getId());
			cont.setCaption(results.get(0).getChildren().get(i).getCaption());
			deleteMashup(cont);
		}

		for (int i = 0; i < results.size(); i++) {
			dbMashups.delete(results.get(i));
		}

	}

	@Override
	public boolean checkUsernameAndPassword(String username, String password) {
		//ODatabaseDocumentTx db = openRemoteConnection(this.dbUsersName);
		List<ODocument> results = dbUsers.query(new OSQLSynchQuery<ODocument>(
				"select * from " + this.dbUsersClassName
						+ " where username = '" + username
						+ "' and password = '" + password + "'"));
		return results.size() > 0;
	}

	@Override
	public String getMashupJSON(String caption) {
		String json = null;
		ODatabaseObjectTx db = openRemoteConnectionObjectDB(this.dbMashupsName);
		db.getEntityManager().registerEntityClass(
				Content.class);
		if (db.getClusterType(this.dbMashupsClassName) != null){
			List<Content> results = db.query(new OSQLSynchQuery<Content>(
					"select * from " + this.dbMashupsClassName
							+ " where caption = '" + caption + "'"));
			if (results.size() > 0) {
				json = results.get(0).getJSON();
			}
		}
		
		return json;
	}

	private void createDocumentDatabase(String dbName) {
		ODatabaseDocumentTx db = getLocalConnection(dbName);
		if (!db.exists()) {
			db.create();
		}
		db.close();
	}

	private void createObjectDatabase(String dbName) {
		ODatabaseObjectTx db = getLocalConnectionObjectDB(dbName);
		if (!db.exists()) {
			db.create();
		}
		db.close();
	}

	private ODatabaseDocumentTx getLocalConnection(String dbName) {
		return new ODatabaseDocumentTx(this.filePath + dbName);
	}

	private ODatabaseObjectTx getLocalConnectionObjectDB(String dbName) {
		return new ODatabaseObjectTx(this.filePath + dbName);
	}

	private ODatabaseObjectTx getRemoteConnectionObjectDB(String dbName) {
		return new ODatabaseObjectTx("remote:localhost/" + dbName);
	}

	private ODatabaseDocumentTx getRemoteConnection(String dbName) {
		return new ODatabaseDocumentTx("remote:localhost/" + dbName);
	}

	private ODatabaseDocumentTx openRemoteConnection(String dbName) {
		return getRemoteConnection(dbName).open(this.username, this.password);
	}

	private ODatabaseObjectTx openRemoteConnectionObjectDB(String dbName) {
		return getRemoteConnectionObjectDB(dbName).open(this.username,
				this.password);
	}

	private void closeRemoteConnection(ODatabaseDocumentTx conn) {
		conn.close();
	}

	private void createMashupUser(String username, String password) {
		//ODatabaseDocumentTx db = openRemoteConnection(this.dbUsersName);
		if (dbUsers.getClusterType(this.dbUsersClassName) == null
				|| !checkUsernameAndPassword(username, password)) {
			ODocument user = new ODocument(dbUsers, this.dbUsersClassName);
			user.field("username", username);
			user.field("password", password);
			user.save();
		}
	}

	@Override
	public Content getMashup(String caption) {
		Content content = null;
		//ODatabaseObjectTx db = openRemoteConnectionObjectDB(this.dbMashupsName);
		dbMashups.getEntityManager().registerEntityClass(
				Content.class);
		if (dbMashups.getClusterType(this.dbMashupsClassName) != null){
			List<Content> results = dbMashups.query(new OSQLSynchQuery<Content>(
					"select * from " + this.dbMashupsClassName
							+ " where caption = '" + caption + "'"));
			if(results.size()>0){
				content = results.get(0);
			}
		}
		
		return content;
	}

	@Override
	public List<String> getMashupsFromUserJSON(String username) {
		List<String> jsons = new ArrayList<String>();
		//ODatabaseObjectTx db = openRemoteConnectionObjectDB(this.dbMashupsName);
		dbMashups.getEntityManager().registerEntityClass(
				Content.class);
		if (dbMashups.getClusterType(this.dbMashupsClassName) != null){
			List<Content> results = dbMashups.query(new OSQLSynchQuery<Content>(
					"select * from " + this.dbMashupsClassName
							+ " where publisher = '" + username + "'"));
			for(Content con: results){
				jsons.add(con.getJSON());
			}
		}	
		
		return jsons;
	}

	@Override
	public List<Content> getMashupsFromUser(String username) {
		List<Content> contents = null;
		//ODatabaseObjectTx db = openRemoteConnectionObjectDB(this.dbMashupsName);
		dbMashups.getEntityManager().registerEntityClass(
				Content.class);
		if (dbMashups.getClusterType(this.dbMashupsClassName) != null){
			contents = dbMashups.query(new OSQLSynchQuery<Content>(
					"select * from " + this.dbMashupsClassName
							+ " where publisher = '" + username + "'"));
			 
		}	
		
		return contents;
	}

	@Override
	public List<Content> getAllMashups() {
		List<Content> contents = null;
		//ODatabaseObjectTx db = openRemoteConnectionObjectDB(this.dbMashupsName);
		dbMashups.getEntityManager().registerEntityClass(
				Content.class);
		if (dbMashups.getClusterType(this.dbMashupsClassName) != null){
			contents = dbMashups.query(new OSQLSynchQuery<Content>(
					"select * from " + this.dbMashupsClassName));
			 
		}	
		
		return contents;
	}

	@Override
	public List<String> getAllMashupsJSON() {
		List<String> jsons = new ArrayList<String>();
		//ODatabaseObjectTx db = openRemoteConnectionObjectDB(this.dbMashupsName);
		dbMashups.getEntityManager().registerEntityClass(
				Content.class);
		if (dbMashups.getClusterType(this.dbMashupsClassName) != null){
			List<Content> results = dbMashups.query(new OSQLSynchQuery<Content>(
					"select * from " + this.dbMashupsClassName
							+ " where publisher = '" + username + "'"));
			for(Content con: results){
				jsons.add(con.getJSON());
			}
		}		
		return jsons;
	}

	@Override
	public void closeConnections() {
		dbMashups.close();
		dbUsers.close();
	}
	
	

}
