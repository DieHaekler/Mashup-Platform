import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;

import com.fourspaces.couchdb.Database;
import com.fourspaces.couchdb.Document;
import com.fourspaces.couchdb.Session;

public class Main {

	public static void main(String[] args) throws ClientProtocolException,
			IOException {
		Session dbSession = new Session("localhost", 5984);
		String dbname = "employee";
		dbSession.createDatabase(dbname);
		Database db = dbSession.getDatabase(dbname);
		
		
		createDocuments(db);
		
		// Löschen eines Dokuments
		// Document d = db.getDocument("1");
		// db.deleteDocument(d);

		
		// Anzahl Datensätze herausfinden
		db = dbSession.getDatabase(dbname);
		int count = db.getDocumentCount();
		System.out.println("\n" + "Total Documents: " + count + "\n");

		//ViewResults results = db.getAllDocuments();
		//System.out.println(results);

		
		HttpClient httpclient = getThreadSafeClient();

		System.out.println("Abfrage der ersten 3 Dokumente:");
		HttpGet get = new HttpGet(
				"http://localhost:5984/employee/_all_docs?startkey=%221%22&limit=3");
		HttpResponse response = httpclient.execute(get);
		System.out.println(getOutput(response));

		
		System.out.println("Abfrage Dokumente 1 - 2:");
		get = new HttpGet(
				"http://localhost:5984/employee/_all_docs?startkey=%221%22&endkey=%222%22");
		response = httpclient.execute(get);
		System.out.println(getOutput(response));

		//Anlegen von zwei Views
		Document doc = new Document();
		doc.setId("_design/couchview");
		String str = "{\"javalanguage\": {\"map\": \"function(doc) { if (doc.Language == 'Java')  emit(null, doc) } \"}, \"java_and_se\": {\"map\": \"function(doc) { if (doc.Language == 'Java' & doc.Designation == 'SE')  emit(null, doc) } \"}}";
		doc.put("views", str);
		try {
			db.saveDocument(doc);
		} catch (net.sf.json.JSONException e) {
			System.out.println("Dokument bereits vorhanden" + "\n");
		}

		System.out.println("Abfrage nach Java:");
		get = new HttpGet(
				"http://localhost:5984/employee/_design/couchview/_view/javalanguage");
		response = httpclient.execute(get);
		System.out.println(getOutput(response));

		System.out.println("Abfrage nach Java und SE:");
		get = new HttpGet(
				"http://localhost:5984/employee/_design/couchview/_view/java_and_se");
		response = httpclient.execute(get);
		System.out.println(getOutput(response));

		//dbSession.deleteDatabase(dbname);
	}

	public static DefaultHttpClient getThreadSafeClient() {
		DefaultHttpClient client = new DefaultHttpClient();
		ClientConnectionManager mgr = client.getConnectionManager();
		// HttpParams params = client.getParams();
		client = new DefaultHttpClient(new ThreadSafeClientConnManager(
				mgr.getSchemeRegistry()));
		return client;
	}

	public static String getOutput(HttpResponse response) {
		HttpEntity entity = response.getEntity();
		InputStream instream = null;
		try {
			instream = entity.getContent();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				instream));
		String strdata = null;
		String output = new String();
		try {
			while ((strdata = reader.readLine()) != null) {
				output += strdata + "\n";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;
	}
	
	public static void createDocuments(Database db){
		Document doc = new Document();
		doc.setId("1");
		doc.put("EmpNO", "1");
		doc.put("Name", "Mike");
		doc.put("Group", "J2EECOE");
		doc.put("Designation", "Manager");
		doc.put("Language", "Java");
		
		try {
			db.saveDocument(doc);
		} catch (net.sf.json.JSONException e) {
			System.out.println("Id " + doc.getId() + " bereits vorhanden");
		}

		doc = new Document();
		doc.setId("2");
		doc.put("EmpNO", "2");
		doc.put("Name", "Hayden");
		doc.put("Group", "J2EECOE");
		doc.put("Designation", "RA");
		doc.put("Language", "Java");

		try {
			db.saveDocument(doc);
		} catch (net.sf.json.JSONException e) {
			System.out.println("Id " + doc.getId() + " bereits vorhanden");
		}

		doc = new Document();
		doc.setId("3");
		doc.put("EmpNO", "3");
		doc.put("Name", "Tom");
		doc.put("Group", "J2EECOE");
		doc.put("Designation", "JRA");
		doc.put("Language", "Java");
		try {
			db.saveDocument(doc);
		} catch (net.sf.json.JSONException e) {
			System.out.println("Id " + doc.getId() + " bereits vorhanden");
		}
		
		doc = new Document();
		doc.setId("6");
		doc.put("EmpNO", "6");
		doc.put("Name", "Andrew");
		doc.put("Group", "J2EECOE");
		doc.put("Designation", "SE");
		doc.put("Language", "Java");
		try {
			db.saveDocument(doc);
		} catch (net.sf.json.JSONException e) {
			System.out.println("Id " + doc.getId() + " bereits vorhanden");
		}
	}
	

}
