 package ch.ffhs.inf09.pa.mashup_platform.web.view;

import ch.ffhs.inf09.pa.mashup_platform.config.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.web.Environment;
import ch.ffhs.inf09.pa.mashup_platform.web.model.*;

public abstract class ViewApplication
{
	public static final String CONTENT_TYPE_HTML = "text/html";
	public static final String CONTENT_TYPE_JSON = "application/json";
	public static final String PLACEHOLDER_VIEW_MENU = "[__VIEW_MENU__]";
	public static final String PLACEHOLDER_VIEW_APPLICATION = "[__VIEW_APPLICATION__]";
	public static final String PLACEHOLDER_WEB_PATH_ROOT = "[__WEB_PATH_ROOT__]";
	public static final String PLACEHOLDER_VIEW_LOGIN_FORM = "[__VIEW_LOGIN_FORM__]";
	
	protected String content;
	protected String contentType = CONTENT_TYPE_HTML;
	protected ModelApplication model;
	
	public ViewApplication(Environment environment) throws ExceptionMP
	{
		content = getTemplate("html/main.html");
				
		//Add global menu
		String menu = getTemplate("html/menu/menu.html");
		if ( environment.isUserLoggedIn() )
		{
			menu = getTemplate("html/menu/menu_logged_in.html");
		}
		
		content = content.replace(PLACEHOLDER_VIEW_MENU, menu);
	}
	
	public ViewApplication(String templatePath) throws ExceptionMP
	{
		content = getTemplate(templatePath);
	}
	
	protected String getTemplate(String name) throws ExceptionMP
	{
		String filepath = Config.FILE_PATH_DESIGN + name;
		if ( FileMP.exists(filepath) )
		{
			try
			{
				return FileMP.getContent(filepath);
			} catch (Exception e)
			{
				throw new ExceptionMP("Couldn't read " + filepath, e);
			}
		}
		return null;
	}
	
	public String getContentType()
	{
		return contentType;
	}
	
	public String getContent()
	{
		return content;
	}
	
	public void setContent(String templatePath) throws ExceptionMP
	{
		content = getTemplate(templatePath);
	}
	
	public void setModel(ModelApplication model)
	{
		this.model = model;
	}
	
	private static String stripPlaceholders(String content)
	{
		return content.replaceAll("\\[__.+?__\\]", "");
	}
	
	protected void complete()
	{
		content = content.replace(PLACEHOLDER_WEB_PATH_ROOT, Config.WEB_PATH_ROOT);
		content = stripPlaceholders(content);
	}
}