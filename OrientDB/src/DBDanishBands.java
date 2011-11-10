import java.net.MalformedURLException;
import java.net.URL;

public class DBDanishBands extends DB {

	private String source = "http://en.wikipedia.org/wiki/List_of_Danish_bands";
	private String expr = "<li><a href=\"" + "([^\"]+)" + "\"[^>]*>"
			+ "([^<]+)" + "</a>.*?</li>";

	public DBDanishBands() {
		try {
			url = new URL(source);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		this.content = new Content("Danish Bands");
	}

	public Content fillIn(int start, int limit, String arguments) {
		return super.fillIn(content, expr, start, limit);
	}

	public Content fillIn() {
		return super.fillIn(this.content, expr);
	}

	protected Content executeYQL(String query) {
		return null;
	}

}
