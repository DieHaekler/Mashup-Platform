package ch.ffhs.inf09.pa.mashup_platform.web.view;

import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.web.Environment;
import ch.ffhs.inf09.pa.mashup_platform.web.model.*;

public class ViewMashupOverviewJSON extends ViewApplication
{
	private ModelMashups model;
	
	public ViewMashupOverviewJSON(Environment environment, ModelMashups model) throws ExceptionMP
	{
		super(environment);
		this.model = model;
		
		content = getTemplate("html/sample-data/mashup_inventory.json");
		contentType = CONTENT_TYPE_JSON;
	}
}