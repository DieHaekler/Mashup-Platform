package ch.ffhs.inf09.pa.mashup_platform.web.view;

import ch.ffhs.inf09.pa.mashup_platform.common.util.*;

public class ViewLogin extends ViewApplication
{
	public ViewLogin(String mainContent) throws ExceptionMP
	{
		super("html/mashup/login_form.html");
		content = mainContent.replaceFirst("\\[__VIEW_LOGIN_FORM__\\]", content);
	}
}