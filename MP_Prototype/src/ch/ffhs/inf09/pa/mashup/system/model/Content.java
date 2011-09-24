package ch.ffhs.inf09.pa.mashup.system.model;

import java.util.*;

public class Content
{
	private ArrayList<Content> children = new ArrayList<Content>();
	private String caption;
	private String imgURL;
	private String intro;
	private String heading;
	private String body;
	private String footer;
	private String url;
	private String publisher;
	private String publishedDate;
	
	public Content(String caption)
	{
		this.caption = caption;
	}
	
	public void setImgURL(String imgURL)
	{
		this.imgURL = imgURL;
	}
	
	public void setIntro(String intro)
	{
		this.intro = intro;
	}
	
	public void setHeading(String heading)
	{
		this.heading = heading;
	}
	
	public void setBody(String body)
	{
		this.body = body;
	}
	
	public void setFooter(String footer)
	{
		this.footer = footer;
	}
	
	public void setUrl(String url)
	{
		this.url = url;
	}
	
	public void setPublisher(String publisher)
	{
		this.publisher = publisher;
	}
	
	public void setPublishedDate(String date)
	{
		this.publishedDate = date;
	}
	
	public String getCaption()
	{
		return caption;
	}
	
	public String getIntro()
	{
		return intro;
	}
	
	public String getHeading()
	{
		return heading;
	}
	
	public String getBody()
	{
		return body;
	}
	
	public String getFooter()
	{
		return footer;
	}
	
	public String getUrl()
	{
		return url;
	}
	
	public void addChild(Content content)
	{
		children.add(content);
	}
	
	public String getPublisher()
	{
		return publisher;
	}
	
	public String getPublishedDate()
	{
		return publishedDate;
	}
	
	public ArrayList<Content> getChildren()
	{
		return children;
	}
	
	public void setChilds(ArrayList<Content> children)
	{
		this.children = children;
	}
	
	public String getImgURL()
	{
		return imgURL;
	}
}