package ch.ffhs.inf09.pa.mashup_platform.web.view;

import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.web.Environment;

public class ViewMashupJSON extends ViewApplication
{
	public ViewMashupJSON(Environment environment) throws ExceptionMP
	{
		super(environment);
		content = getTemplate("html/sample-data/portrait_of_finnish_bands_0_3.json");
		contentType = CONTENT_TYPE_JSON;
	}
}