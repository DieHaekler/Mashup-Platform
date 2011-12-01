package ch.ffhs.inf09.pa.mashup_platform.web.controller;

import ch.ffhs.inf09.pa.mashup_platform.web.model.*;
import ch.ffhs.inf09.pa.mashup_platform.web.view.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.web.*;

public class ControllerMashup extends ControllerApplication
{
	public ControllerMashup(Environment environment) throws ExceptionMP
	{
		super(environment, new ViewMashup(environment, new ModelMashup()));
	}
}