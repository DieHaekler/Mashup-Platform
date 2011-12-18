package ch.ffhs.inf09.pa.mashup_platform.core.system.model.fetcher.google_search;

/**
 * This class is a simple container to keep the result of a Google search.
 * 
 * @author Malte
 * 
 */
public class ResultGoogleSearch {
	private String url;
	private String title;
	private String content;

	public void setURL(String url) {
		this.url = url;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getURL() {
		return url;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

}