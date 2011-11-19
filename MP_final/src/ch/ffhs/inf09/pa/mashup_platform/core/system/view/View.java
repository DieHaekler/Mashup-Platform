package ch.ffhs.inf09.pa.mashup_platform.core.system.view;

import ch.ffhs.inf09.pa.mashup_platform.config.*;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import java.io.*;

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
	
	public void storeOutput(String filename) throws ExceptionMP
	{
		String filepath = Config.FILE_PATH_OUTPUT + filename;
		String output = getOutput();
		try
		{
			if (output != null) FileMP.write(filepath, output, false);
		} catch (IOException e)
		{
			throw new ExceptionMP("Could not store '" + filepath + "'", e);
		}
	}
}