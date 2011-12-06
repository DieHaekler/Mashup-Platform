package ch.ffhs.inf09.pa.mashup_platform.web.controller;

import ch.ffhs.inf09.pa.mashup_platform.common.util.ExceptionMP;
import ch.ffhs.inf09.pa.mashup_platform.web.view.*;
import ch.ffhs.inf09.pa.mashup_platform.web.*;

public abstract class ControllerApplication
{
	protected ViewApplication view;
	protected Environment environment;
	
	public ControllerApplication(Environment environment) throws ExceptionMP
	{
		this.environment = environment;
	}
	
	public void setView(ViewApplication view)
	{
		this.view = view;
	}
	
	public ViewApplication getView()
	{
		return view;
	}
	
}
