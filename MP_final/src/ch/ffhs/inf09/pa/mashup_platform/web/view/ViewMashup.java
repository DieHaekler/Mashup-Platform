package ch.ffhs.inf09.pa.mashup_platform.web.view;

import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.web.Environment;
import ch.ffhs.inf09.pa.mashup_platform.web.model.*;

public class ViewMashup extends ViewApplication
{	
	public ViewMashup(ModelMashup model) throws ExceptionMP
	{
		super(model);
		String part = getTemplate("html/mashup/mashup.html");
		content = content.replace(PLACEHOLDER_VIEW_APPLICATION, part);
		complete();
	}
}