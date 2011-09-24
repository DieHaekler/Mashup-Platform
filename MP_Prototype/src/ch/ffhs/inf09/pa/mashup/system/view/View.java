package ch.ffhs.inf09.pa.mashup.system.view;

import ch.ffhs.inf09.pa.mashup.system.model.*;

public abstract class View
{
	public static final String VIEW_TYPE_HTML = "html";
	public static final String VIEW_TYPE_XML = "xml";
	
	protected Model model;
	protected String output;
	
	public View(Model model)
	{
		this.model = model;
	}
	
	public String getOutput()
	{
		return output;
	}
}