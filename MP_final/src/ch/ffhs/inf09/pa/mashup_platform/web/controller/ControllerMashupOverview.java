package ch.ffhs.inf09.pa.mashup_platform.web.controller;

import ch.ffhs.inf09.pa.mashup_platform.web.model.*;
import ch.ffhs.inf09.pa.mashup_platform.web.view.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.web.*;

public class ControllerMashupOverview extends ControllerApplication
{
	public ControllerMashupOverview(Environment environment) throws ExceptionMP
	{
		super(environment);
		String format = environment.getValuePost("format");
		ModelMashupOverview model = new ModelMashupOverview(environment);
		if (format != null && format.equals("json"))
		{
			setView(new ViewMashupOverviewJSON(model));
		} else
		{
			setView(new ViewMashupOverview(model));
		}
	}
}