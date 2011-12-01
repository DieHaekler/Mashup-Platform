package ch.ffhs.inf09.pa.mashup_platform.web.view;

import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.web.Environment;
import ch.ffhs.inf09.pa.mashup_platform.web.model.*;

public class ViewLogin extends ViewApplication
{
	private ModelUser model;
	
	public ViewLogin(Environment environment, ModelUser model) throws ExceptionMP
	{
		super(environment);
		this.model = model;
		String login = getTemplate("html/menu/login.html");
		content = content.replace(PLACEHOLDER_VIEW_LOGIN_FORM, login);

		complete();
	}
}