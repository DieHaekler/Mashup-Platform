package ch.ffhs.inf09.pa.mashup_platform.web.controller;

import ch.ffhs.inf09.pa.mashup_platform.web.view.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.web.*;

public class ControllerMashupOverview extends ControllerApplication
{
	public ControllerMashupOverview(Environment environment) throws ExceptionMP
	{
		super(environment, new ViewMashupOverview());
	}
}