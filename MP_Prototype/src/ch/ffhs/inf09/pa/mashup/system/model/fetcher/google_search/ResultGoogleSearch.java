package ch.ffhs.inf09.pa.mashup.system.model.fetcher.google_search;

public class ResultGoogleSearch
{
	private String url;
	private String title;
	private String content;
	
	public void setURL(String url)
	{
		this.url = url;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public void setContent(String content)
	{
		this.content = content;
	}
	
	public String getURL() { return url; }
	public String getTitle() { return title; }
	public String getContent() { return content; }

}