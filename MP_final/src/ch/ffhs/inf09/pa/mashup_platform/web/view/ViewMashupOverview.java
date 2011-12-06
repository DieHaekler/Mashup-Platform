package ch.ffhs.inf09.pa.mashup_platform.web.view;

import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.web.Environment;
import ch.ffhs.inf09.pa.mashup_platform.web.model.*;

public class ViewMashupOverview extends ViewApplication
{
	private ModelMashupOverview model;
	
	public ViewMashupOverview(Environment environment, ModelMashupOverview model) throws ExceptionMP
	{
		super(environment);
		this.model = model;
		
		String part = getTemplate("html/mashup/overview.html");
		content = content.replace(PLACEHOLDER_VIEW_APPLICATION, part);
		
		complete();
	}
}