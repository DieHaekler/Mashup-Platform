import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;

public class Main {

	public static void main(String[] args) throws IOException {	
		//2 Mashups erstellen
		DBNewsFromFinnishAndDanishBands db = new DBNewsFromFinnishAndDanishBands();
		db.fillIn(0, 10, "");
		
		DBNewsFromFinnishAndDanishBands db2 = new DBNewsFromFinnishAndDanishBands();
		db2.fillIn(0, 5, "");
		
		
		//Lokale Session zur Erstellung der Mashup-Datenbank
		ODatabaseDocumentTx mashupDBLocal = new ODatabaseDocumentTx ("local:C:/orientdb-1.0rc6/databases/mashups");
		if(!mashupDBLocal.exists()){
			mashupDBLocal.create();
		}
				
		//Öffnen der Datenbankverbindung
		ODatabaseDocumentTx mashupDB = new ODatabaseDocumentTx("remote:localhost/mashups").open("admin", "admin"); 
		
		/*//Löschen der vorhandenen Einträge
		for (ODocument mashup : mashupDB.browseClass("Mashup")){
			mashup.delete();
		}*/
		
		//die beiden Mashups als Dokumente abbilden
		ODocument mashup1 = new ODocument(mashupDB, "Mashup"); 
		mashup1.field("title", "10 news about Finnish and Danish Bands");
		mashup1.field("description", "This Mashup contains 10 news about Finnish and Danish Bands.");
		mashup1.field( "html", db.getContent().toString());
		mashup1.field("creator", "Admin");
		mashup1.field("createTime", new Date(System.currentTimeMillis()).toString());
		
		ODocument mashup2 = new ODocument(mashupDB, "Mashup"); 
		mashup2.field("title", "5 news about Finnish and Danish Bands");
		mashup2.field("description", "This Mashup contains 5 news about Finnish and Danish Bands.");
		mashup2.field( "html", db.getContent().toString());
		mashup2.field("creator", "Admin");
		mashup2.field("createTime", new Date(System.currentTimeMillis()).toString());
				
		
		
		//Mashups in DB abspeichern
		mashup1.save();  
		mashup2.save();
				
		System.out.println("Anzahl Mashups in DB: " + mashupDB.countClass("Mashup"));
		
		//Beispiel für SQL-Abfrage
		//HTML's aller Mashups von Admin-User ausgeben
		List<ODocument> adminMashups = mashupDB.query(new OSQLSynchQuery<ODocument>("select html from Mashup where creator = 'Admin'"));
		for(ODocument doc: adminMashups){
			System.out.println(doc);
		}		
						
		//Datenbankverbindung schliessen
		mashupDB.close();						
	}

}
