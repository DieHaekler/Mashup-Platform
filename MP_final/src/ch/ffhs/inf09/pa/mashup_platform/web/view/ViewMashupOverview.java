package ch.ffhs.inf09.pa.mashup_platform.web.view;

import ch.ffhs.inf09.pa.mashup_platform.common.util.*;

public class ViewMashupOverview extends ViewApplication
{
	public ViewMashupOverview() throws ExceptionMP
	{
		super();
		String part = getTemplate("html/mashup/overview.html");
		content = content.replaceFirst("\\[__VIEW_APPLICATION__\\]", part);
		ViewLogin viewLogin = new ViewLogin(content);
		content = viewLogin.getContent();
		complete();
	}
}