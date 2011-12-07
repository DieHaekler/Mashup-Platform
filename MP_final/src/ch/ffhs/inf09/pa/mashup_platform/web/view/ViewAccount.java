package ch.ffhs.inf09.pa.mashup_platform.web.view;

import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.web.Environment;
import ch.ffhs.inf09.pa.mashup_platform.web.model.*;

public class ViewAccount extends ViewApplication
{	
	public ViewAccount(ModelUser model) throws ExceptionMP
	{
		super(model);
		String part = getTemplate("html/account/overview.html");
		content = content.replace(PLACEHOLDER_VIEW_APPLICATION, part);
		
		/*ViewMenu viewMenu = new ViewMenu(content);
		content = viewMenu.getContent();
		
		ViewLogout viewLogout = new ViewLogout(content);
		content = viewLogout.getContent(); */
		complete();
	}
}