package ch.ffhs.inf09.pa.mashup_platform.web.view;

import ch.ffhs.inf09.pa.mashup_platform.common.db.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.*;
import ch.ffhs.inf09.pa.mashup_platform.web.model.*;

public class ViewMashupJSON extends ViewApplication
{	
	public ViewMashupJSON(ModelMashup model) throws ExceptionMP
	{
		super(model);
		content = null;
		MashupPage page = model.getPage();
		if (page != null)
		{
			Content c = page.getContent();
			if (c != null)
			{
				content = c.getJSON();
			}
		}
		//content = getTemplate("html/sample-data/portrait_of_finnish_bands_0_3.json");
		contentType = CONTENT_TYPE_JSON;
	}
}