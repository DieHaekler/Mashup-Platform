import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

public abstract class DB {

	protected URL url;
	protected Content content;

	public Content fillIn(Content content, String expr) {
		return fetchContent(content, expr, 0, -1);
	}

	public Content fillIn(Content content, String expr, int start, int limit) {
		return fetchContent(content, expr, start, limit);
	}

	protected Content executeYQL(String query) {
		return null;
	}

	public static CharSequence getURLContent(URL url) throws IOException {

		URLConnection conn = url.openConnection();
		String encoding = conn.getContentEncoding();

		if (encoding == null) {
			encoding = "UTF-8";
		}

		BufferedReader br = null;

		if ("gzip".equals(conn.getContentEncoding())) {
			br = new BufferedReader(new InputStreamReader(new GZIPInputStream(
					conn.getInputStream()), encoding));
		} else {
			br = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), encoding));
		}

		StringBuilder sb = new StringBuilder(16384);

		try {
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
				sb.append('\n');
			}
		} finally {
			br.close();
		}
		return sb;
	}

	public Content fetchContent(Content content, String expr, int start,
			int limit) {
		Pattern patt = Pattern.compile(expr, Pattern.DOTALL
				| Pattern.UNIX_LINES);
		try {
			Matcher m = patt.matcher(getURLContent(url));

			int counter = 0;

			while (m.find()) {

				if ((counter >= start && counter < limit) || limit < 0) {
					// String stateURL = m.group(1);
					String stateName = m.group(2);
					content.addContent(new Content(stateName));
				}
				counter++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return content;
	}

	public Content getContent() {
		return content;
	}

}
