package ch.ffhs.inf09.pa.mashup_platform.web.view;

import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.web.Environment;

public class ViewMashups extends ViewApplication
{
	public ViewMashups(Environment environment) throws ExceptionMP
	{
		super(environment);
		content = getTemplate("html/sample-data/mashup_inventory.json");
		contentType = CONTENT_TYPE_JSON;
	}
}