package ch.ffhs.inf09.pa.mashup_platform.web.view;

import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.web.Environment;
import ch.ffhs.inf09.pa.mashup_platform.web.model.*;

public class ViewMashupJSON extends ViewApplication
{
	private ModelMashup model;
	
	public ViewMashupJSON(Environment environment, ModelMashup model) throws ExceptionMP
	{
		super(environment);
		this.model = model;
		
		content = getTemplate("html/sample-data/portrait_of_finnish_bands_0_3.json");
		contentType = CONTENT_TYPE_JSON;
	}
}