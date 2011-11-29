package ch.ffhs.inf09.pa.jans_mashup_platform.web.view;

import ch.ffhs.inf09.pa.jans_mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.jans_mashup_platform.web.Environment;

public class ViewLogin extends ViewApplication
{
	public ViewLogin(Environment environment) throws ExceptionMP
	{
		super(environment);
		String login = getTemplate("html/menu/login.html");
		content = content.replace(PLACEHOLDER_VIEW_LOGIN_FORM, login);

		complete();
	}
}