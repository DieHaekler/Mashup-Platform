package ch.ffhs.inf09.pa.mashup_platform.core.system.model;

import java.io.*;
import java.util.*;

public class ContentSection implements Serializable
{
	private static final long serialVersionUID = 1L;
	String caption;
	private List<Content> parts = new ArrayList<Content>();
	
	public void setCaption(String caption)
	{
		this.caption = caption;
	}
	
	public String getCaption()
	{
		return caption;
	}
	
	public List<Content> getParts()
	{
		return parts;
	}
	
	public void addPart(Content content)
	{
		parts.add(content);
	}
}