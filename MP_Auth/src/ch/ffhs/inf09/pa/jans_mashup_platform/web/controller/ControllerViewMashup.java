package ch.ffhs.inf09.pa.jans_mashup_platform.web.controller;

import ch.ffhs.inf09.pa.jans_mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.jans_mashup_platform.web.view.*;
import ch.ffhs.inf09.pa.jans_mashup_platform.web.*;

public class ControllerViewMashup extends ControllerApplication
{
	public ControllerViewMashup(Environment environment) throws ExceptionMP
	{
		super(environment, new ViewMashup(environment));
	}
}