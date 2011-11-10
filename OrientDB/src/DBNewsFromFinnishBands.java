public class DBNewsFromFinnishBands extends DBGoogleNews {

	private DBFinnishBands db;

	public DBNewsFromFinnishBands() {
		db = new DBFinnishBands();
	}

	public Content fillIn(Content content, int start, int limit,
			String arguments) {
		fillIn(start, limit, arguments);
		content.addContent(this.content);
		return content;
	}

	public void fillIn(int start, int limit, String arguments) {
		this.content = db.fillIn(start, limit, arguments);
		this.content = super.fillIn(this.content, 0, -1, arguments);
	}

	public Content fillIn() {
		this.content = db.fillIn(0, -1, "");
		this.content = super.fillIn(this.content, 0, -1, "");
		return this.content;
	}

}
