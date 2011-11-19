package ch.ffhs.inf09.pa.mashup_platform.web.controller;

import ch.ffhs.inf09.pa.mashup_platform.web.view.*;

public abstract class ControllerApplication
{
	protected ViewApplication view;
	
	public ControllerApplication(ViewApplication view)
	{
		this.view = view;
	}
	
	public ViewApplication getView()
	{
		return view;
	}
}
