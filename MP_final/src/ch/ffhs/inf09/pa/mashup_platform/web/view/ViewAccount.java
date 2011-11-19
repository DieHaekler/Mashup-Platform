package ch.ffhs.inf09.pa.mashup_platform.web.view;

import ch.ffhs.inf09.pa.mashup_platform.common.util.*;

public class ViewAccount extends ViewApplication
{
	public ViewAccount() throws ExceptionMP
	{
		super();
		String part = getTemplate("html/account/overview.html");
		content = content.replaceFirst("\\[__VIEW_APPLICATION__\\]", part);
		ViewLogout viewLogout = new ViewLogout(content);
		content = viewLogout.getContent();
		complete();
	}
}