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
	protected Environment environment;
	
	public ViewApplication(Environment environment) throws ExceptionMP
	{
		this.environment = environment;
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
		String filepath = Config.getInstance().getValue(Config.PARAM_FILE_PATH_SYSTEM)
			+ "/design/" + name;
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
		content = content.replace(PLACEHOLDER_WEB_PATH_ROOT,
			Config.getInstance().getValue(Config.PARAM_WEB_PATH_ROOT));
		content = stripPlaceholders(content);
	}
}