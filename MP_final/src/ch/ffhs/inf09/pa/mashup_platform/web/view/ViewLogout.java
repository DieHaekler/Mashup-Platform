package ch.ffhs.inf09.pa.mashup_platform.web.view;

import ch.ffhs.inf09.pa.mashup_platform.common.util.*;

public class ViewLogout extends ViewApplication
{
	public ViewLogout(String mainContent) throws ExceptionMP
	{
		super("html/mashup/logout_link.html");
		content = mainContent.replaceFirst("\\[__VIEW_LOGOUT_LINK__\\]", content);
	}
}