package ch.ffhs.inf09.pa.mashup_platform.web.view;

import ch.ffhs.inf09.pa.mashup_platform.common.db.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.web.model.*;

public class ViewMashupOverviewJSON extends ViewApplication
{	
	public ViewMashupOverviewJSON(ModelMashupOverview model) throws ExceptionMP
	{
		super(model);
		MashupOverview overview = model.get(0, 10);
		content = overview.getJSON();
		contentType = CONTENT_TYPE_JSON;
	}
}