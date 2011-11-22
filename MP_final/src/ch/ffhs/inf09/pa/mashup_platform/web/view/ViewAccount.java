package ch.ffhs.inf09.pa.mashup_platform.web.view;

import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.web.Environment;

public class ViewAccount extends ViewApplication
{
	public ViewAccount(Environment environment) throws ExceptionMP
	{
		super(environment);
		String part = getTemplate("html/account/overview.html");
		content = content.replaceFirst("\\[__VIEW_APPLICATION__\\]", part);
		
		/*ViewMenu viewMenu = new ViewMenu(content);
		content = viewMenu.getContent();
		
		ViewLogout viewLogout = new ViewLogout(content);
		content = viewLogout.getContent(); */
		complete();
	}
}