package ch.ffhs.inf09.pa.mashup_platform.core.system.view;

import ch.ffhs.inf09.pa.mashup_platform.core.system.model.*;

public abstract class View
{
	public static final String VIEW_TYPE_HTML = "html";
	public static final String VIEW_TYPE_XML = "xml";
	
	protected Model model;
	
	public View(Model model)
	{
		this.model = model;
	}
	
	public abstract String getOutput();
}