package ch.ffhs.inf09.pa.mashup_platform.web.controller;

import ch.ffhs.inf09.pa.mashup_platform.web.model.*;
import ch.ffhs.inf09.pa.mashup_platform.web.view.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.web.*;

public class ControllerMashup extends ControllerApplication
{
	public ControllerMashup(Environment environment) throws ExceptionMP
	{
		super(environment);
		String ident = environment.getValuePost("id");
		int pagenr = 0;
		try
		{
			pagenr = Integer.parseInt(environment.getValuePost("p"));
		} catch (NumberFormatException e)
		{
			pagenr = 0;
		}
		String format = environment.getValuePost("format");
		ModelMashup model = new ModelMashup(environment, ident, pagenr);
		if (format != null && format.equals("json"))
		{
			setView(new ViewMashupJSON(model));
		} else
		{
			setView(new ViewMashup(model));
		}
	}
}