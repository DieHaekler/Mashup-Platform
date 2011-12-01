package ch.ffhs.inf09.pa.mashup_platform.core.system.model;

import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import java.util.*;
import java.io.*;
import java.security.*;
import javax.persistence.Id;
import javax.persistence.Version;

public class Content implements Serializable
{  
	
	@Id   
	private Object id;
	
	@Version   
	private Object version;
	
	private static final long serialVersionUID = 1L;
	private HashMap<String, ContentSection> sections = new HashMap<String, ContentSection>();
	private List<String> keywords = new ArrayList<String>();
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
	
	public void clearKeywords()
	{
		keywords = new ArrayList<String>();
	}
	
	public String getJSON()
	{
		return toJSON(this);
	}

	private String toJSON(Content content)
	{
		String k = "";
		for (String keyword: content.getKeywords())
		{
			if (keyword != null && !keyword.equals(""))
			{
				k += "\"" + keyword + "\",";
			}
		}
		if ( !k.equals("") ) k = k.substring(0, k.length() - 1);
		String s = "{"
			+ (content.getCaption() != null ? ("\"caption\":\"" + content.getCaption() + "\",") : "")
			+ (content.getImgUrl() != null ? "\"imgURL\":\"" + content.getImgUrl() + "\"," : "")
			+ (content.getIntro() != null ? "\"intro\":\"" + content.getIntro() + "\"," : "")
			+ (content.getHeading() != null ? "\"heading\":\"" + content.getHeading() + "\"," : "")
			+ (content.getBody() != null ? "\"body\":\"" + content.getBody() + "\"," : "")
			+ (content.getFooter() != null ? "\"footer\":\"" + content.getFooter() + "\"," : "")
			+ (content.getUrl() != null ? "\"url\":\"" + content.getUrl() + "\"," : "")
			+ (content.getPublisher() != null ? "\"publisher\":\"" + content.getPublisher() + "\"," : "")
			+ (content.getPublisherURL() != null ? "\"publisherURL\":\"" + content.getPublisherURL() + "\"," : "")
			+ (content.getPublishedDate() != null ? "\"publishedDate\":\"" + content.getPublishedDate() + "\"," : "")
			+ (!k.equals("") ? "\"keywords\":[" + k + "]," : "");
		HashMap<String, ContentSection> sections = content.getSections();
		if (sections.size() > 0)
		{
			for (String ident: sections.keySet())
			{
				s += "\"" + ident + "\":{";
				ContentSection section = sections.get(ident);
				List<Content> parts = section.getParts();
				String caption = section.getCaption();
				String q = "";
				if (caption != null && !caption.equals(""))
				{
					q = "\"caption\":\"" + caption + "\",";
				}
				if (parts.size() > 0)
				{
					String p = "";
					for (Content part: parts)
					{
						String t = toJSON(part);
						if ( !t.equals("{}") ) p += t + ",";
					}
					if ( !p.equals("") )
					{
						p = p.substring(0, p.length() - 1);
						q += "\"parts\":[" + p + "],";
					}
				}
				if ( !q.equals("") )
				{
					q = q.substring(0, q.length() - 1);
				}
				s += q + "},";
			}
		}
		if ( !s.equals("{") ) s = s.substring(0, s.length() - 1);
		return s + "}";
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
		HashMap<String, ContentSection> sections = content.getSections();
		for (String ident: sections.keySet())
		{
			ContentSection section = sections.get(ident);
			s += "_" + section.getCaption();
			List<Content> parts = section.getParts();
			for (Content part: parts)
			{
				s += "_" + hash(part);
			}
		}
		return s;
	}
	
	public HashMap<String, ContentSection> getSections()
	{
		return sections;
	}
	
	public void update(Content content)
	{
		sections = content.getSections();
		keywords = content.getKeywords();
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
	
	public void addKeyword(String keyword)
	{
		keywords.add(keyword);
	}
	
	public List<String> getKeywords()
	{
		return keywords;
	}
	
	public void setCaption(String caption) {
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
	
	public void setKeywords(ArrayList<String> keywords) {
		this.keywords = keywords;
	}
	
	public void setSections(HashMap<String, ContentSection> sections)
	{
		this.sections = sections;
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
	
	public void addSection(String ident, ContentSection section)
	{
		sections.put(ident, section);
	}
	
	public String getPublisher()
	{
		return publisher;
	}
	
	public String getPublishedDate()
	{
		return publishedDate;
	}
	
	public ContentSection getSection(String ident)
	{
		return sections.get(ident);
	}
	
	public String getImgUrl()
	{
		return imgURL;
	}

	public String getImgURL() {
		return imgURL;
	}
	
	public Object getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = id;
	}

	public Object getVersion() {
		return version;
	}

	public void setVersion(Object version) {
		this.version = version;
	}

}