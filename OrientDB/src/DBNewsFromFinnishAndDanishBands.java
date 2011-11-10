public class DBNewsFromFinnishAndDanishBands extends DBGoogleNews {

	private DBNewsFromFinnishBands dbNewsFromFinnishBands;
	private DBNewsFromDanishBands dbNewsFromDanishBands;

	public DBNewsFromFinnishAndDanishBands() {
		this.content = new Content("News from Finnish and Danish Bands");

		dbNewsFromFinnishBands = new DBNewsFromFinnishBands();
		dbNewsFromDanishBands = new DBNewsFromDanishBands();
	}

	public void fillIn() {
		dbNewsFromFinnishBands.fillIn();
		dbNewsFromDanishBands.fillIn();
		this.content.addContent(dbNewsFromFinnishBands.getContent());		
		this.content.addContent(dbNewsFromDanishBands.getContent());
	}

	public void fillIn(int start, int limit, String arguments) {
		dbNewsFromFinnishBands.fillIn(start, limit, arguments);
		dbNewsFromDanishBands.fillIn(start, limit, arguments);
		this.content.addContent(dbNewsFromFinnishBands.getContent());
		this.content.addContent(dbNewsFromDanishBands.getContent());
	}

}
