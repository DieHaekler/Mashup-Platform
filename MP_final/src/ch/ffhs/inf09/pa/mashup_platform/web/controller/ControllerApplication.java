package ch.ffhs.inf09.pa.mashup_platform.web.controller;

import ch.ffhs.inf09.pa.mashup_platform.web.view.*;
import ch.ffhs.inf09.pa.mashup_platform.web.*;

public abstract class ControllerApplication
{
	protected ViewApplication view;
	protected Environment environment;
	
	public ControllerApplication(Environment environment, ViewApplication view)
	{
		this.environment = environment;
		this.view = view;
	}
	
	public ViewApplication getView()
	{
		return view;
	}
	
}
