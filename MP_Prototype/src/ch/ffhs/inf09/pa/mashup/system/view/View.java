package ch.ffhs.inf09.pa.mashup.system.view;

import ch.ffhs.inf09.pa.mashup.config.Config;
import ch.ffhs.inf09.pa.mashup.system.model.*;
import ch.ffhs.inf09.pa.mashup.system.util.*;
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
	
	public void storeOutput(String filename) throws ExceptionMashup
	{
		String filepath = Config.FILE_PATH_OUTPUT + filename;
		String output = getOutput();
		try
		{
			if (output != null) FileMashup.write(filepath, output, false);
		} catch (IOException e)
		{
			throw new ExceptionMashup("Could not store '" + filepath + "'", e);
		}
	}
}