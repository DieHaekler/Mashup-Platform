package ch.ffhs.inf09.pa.mashup_platform.web.view;

import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.web.Environment;

public class ViewLogin extends ViewApplication
{
	public ViewLogin(Environment environment) throws ExceptionMP
	{
		super(environment);
		String login = getTemplate("html/menu/login.html");
		content = content.replaceFirst("\\[__VIEW_APPLICATION__\\]", login);

		complete();
	}
}