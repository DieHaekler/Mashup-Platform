package ch.ffhs.inf09.pa.mashup_platform.core.system.model;

import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import java.util.*;
import java.io.*;
import java.security.*;

public class Content implements Serializable
{
	private static final long serialVersionUID = 1L;
	private ArrayList<Content> children = new ArrayList<Content>();
	private ArrayList<String> keyWords = new ArrayList<String>();
	private String caption;
	private String imgURL;
	private String intro;
	private String heading;
	private String body;
	private String footer;
	private String url;
	private String publisher;
	private String publisherURL;
	private String publishedDate;
	
	public Content(String caption)
	{
		this.caption = caption;
	}
	
	public String getHashCode() throws ExceptionMP
	{
		String ident = hash(this);
		try
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(ident.getBytes());
			StringBuffer hex = new StringBuffer();
			byte[] result = md.digest();
			for (int i = 0; i < result.length; i++)
			{
				if (result[i] <= 15 && result[i] >= 0) hex.append("0");
				hex.append(Integer.toHexString(0xFF & result[i]));
			}
			return hex.toString();
		} catch (NoSuchAlgorithmException e)
		{
			throw new ExceptionMP("MD5 algorithm is not supported", e);
		}
	}
	
	private String hash(Content content)
	{
		String s = content.getCaption() + "_" + content.getIntro()
			+ "_" + content.getHeading() + "_" + content.getBody()
			+ "_" + content.getFooter() + "_" + content.getUrl()
			+ "_" + content.getPublisher() + "_" + content.getPublisherURL();
		ArrayList<Content> children = content.getChildren();
		for (Content child: children)
		{
			s += "_" + hash(child);
		}
		return s;
	}
	
	public void update(Content content)
	{
		children = content.getChildren();
		keyWords = content.getKeyWords();
		caption = content.getCaption();
		imgURL = content.getImgUrl();
		intro = content.getIntro();
		heading = content.getHeading();
		body = content.getBody();
		footer = content.getFooter();
		url = content.getUrl();
		publisher = content.getPublisher();
		publisherURL = content.getPublisherURL();
		publishedDate = content.getPublishedDate();
	}
	
	public void addKeyWord(String keyWord)
	{
		keyWords.add(keyWord);
	}
	
	public ArrayList<String> getKeyWords()
	{
		return keyWords;
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
	
	public void setPublisherURL(String url)
	{
		publisherURL = url;
	}
	
	public String getPublisherURL()
	{
		return publisherURL;
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
	
	public void setChildren(ArrayList<Content> children)
	{
		this.children = children;
	}
	
	public String getImgUrl()
	{
		return imgURL;
	}
}