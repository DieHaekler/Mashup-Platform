package ch.ffhs.inf09.pa.mashup_platform.web.view;

import ch.ffhs.inf09.pa.mashup_platform.common.util.*;

public class ViewLogin extends ViewApplication
{
	
	public ViewLogin() throws ExceptionMP
	{
		super();
		String login = getTemplate("html/menu/login.html");
		content = content.replaceFirst("\\[__VIEW_APPLICATION__\\]", login);

		complete();
	}
	
}