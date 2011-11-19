package ch.ffhs.inf09.pa.mashup_platform.web.controller;

import ch.ffhs.inf09.pa.mashup_platform.web.view.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;

public class ControllerMashupOverview extends ControllerApplication
{
	public ControllerMashupOverview() throws ExceptionMP
	{
		super(new ViewMashupOverview());
	}
}