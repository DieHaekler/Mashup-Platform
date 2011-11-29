package ch.ffhs.inf09.pa.jans_mashup_platform.web.view;

import ch.ffhs.inf09.pa.jans_mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.jans_mashup_platform.web.Environment;

public class ViewMashup extends ViewApplication
{
	public ViewMashup(Environment environment) throws ExceptionMP
	{
		super(environment);
		String part = getTemplate("html/mashup/mashup.html");
		content = content.replace(PLACEHOLDER_VIEW_APPLICATION, part);
		
		complete();
	}
}