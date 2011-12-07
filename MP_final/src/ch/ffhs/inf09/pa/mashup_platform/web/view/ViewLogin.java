package ch.ffhs.inf09.pa.mashup_platform.web.view;

import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.web.model.*;

public class ViewLogin extends ViewApplication
{	
	public ViewLogin(ModelUser model) throws ExceptionMP
	{
		super(model);
		String login = getTemplate("html/menu/login.html");
		content = content.replace(PLACEHOLDER_VIEW_LOGIN_FORM, login);
		complete();
	}
}