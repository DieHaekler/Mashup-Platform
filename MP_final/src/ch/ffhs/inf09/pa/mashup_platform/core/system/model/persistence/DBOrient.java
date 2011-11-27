package ch.ffhs.inf09.pa.mashup_platform.core.system.model.persistence;

import java.util.List;

import ch.ffhs.inf09.pa.mashup_platform.config.DBConfig;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.Content;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;

public class DBOrient implements DBLocal{

	private String username;
	private String password;
	private String filePath;
	private String dbMashupsName;
	private String dbMashupsClassName;
	private String dbUsersName;
	private String dbUsersClassName;
	
	public DBOrient(String username, String password, String filePath, String dbMashupsName, String dbMashupsClassName,
			String dbUsersName, String dbUsersClassName){
		this.username = username;
		this.password = password;
		this.filePath = filePath;
		this.dbMashupsName = dbMashupsName;
		this.dbMashupsClassName = dbMashupsClassName;
		this.dbUsersName = dbUsersName;
		this.dbUsersClassName = dbUsersClassName;
		
		createDatabase(this.dbMashupsName);
		createDatabase(this.dbUsersName);
	}
		
	@Override
	public void storeMashup(Content content) {
		ODatabaseDocumentTx db = openRemoteConnection(this.dbMashupsName);
		ODocument mashup = new ODocument(db, this.dbMashupsClassName);
		mashup.field("caption", content.getCaption());
		mashup.field( "json", content.getJSON());
		mashup.save();
		closeRemoteConnection(db);
	}


	@Override
	public boolean checkUsernameAndPassword(String username, String password) {
		ODatabaseDocumentTx db = openRemoteConnection(this.dbUsersName);
		List<ODocument> results = db.query(new OSQLSynchQuery<ODocument>("select * from User where username = '" + 
		username + "' and password = '" + password + "'"));
		closeRemoteConnection(db);
		return results.size() > 0;
	}

	@Override
	public String getMashupAsJSON(String caption) {
		ODatabaseDocumentTx db = openRemoteConnection(this.dbMashupsName);
		List<ODocument> results = db.query(new OSQLSynchQuery<ODocument>("select json from Mashup where caption = '" + 
		caption + "'"));
		closeRemoteConnection(db);
		if(results.size() > 0){
			return results.get(0).toString();
		}
		return null;		
	}
	
	private void createDatabase(String dbName) {		
		ODatabaseDocumentTx db = getLocalConnection(dbName);
		if(!db.exists()){
			db.create();
		}		
	}

	private ODatabaseDocumentTx getLocalConnection(String dbName){
		return new ODatabaseDocumentTx (this.filePath + dbName);		
	}
	
	private ODatabaseDocumentTx getRemoteConnection(String dbName){
		return new ODatabaseDocumentTx ("remote:localhost/" + dbName);		
	}
			
	private ODatabaseDocumentTx openRemoteConnection(String dbName){
		return getRemoteConnection(dbName).open(this.username, this.password);
	}
	
	private void closeRemoteConnection(ODatabaseDocumentTx conn){
		conn.close();
	}


}
