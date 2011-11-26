package ch.ffhs.inf09.pa.mashup_platform.core.system.controller;

import java.io.IOException;
import java.lang.reflect.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.Config;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.*;

public class Controller
{
	private Model model;
	private String mashupName;
	private int start;
	private int number;
	
	public Controller(String mashupName, int start, int number) throws ExceptionMP
	{
		this.mashupName = mashupName;
		this.start = start;
		this.number = number;
		String pathVar = "ch.ffhs.inf09.pa.mashup_platform.var";
		String pathModel = pathVar + "." + mashupName + ".model.ModelMain";
		try
		{
			Class<?> c = (Class<?>)Class.forName(pathModel);
			Constructor<?> ct = c.getConstructor();
			model = (Model)ct.newInstance();
			LoggerMP.writeNotice("model instantiated (" + pathModel + ")");
			model.setRange(start, number);
		} catch (ClassNotFoundException e)
		{
			throw new ExceptionMP("Couldn't find " + pathModel, e);
		} catch (Exception e)
		{
			throw new ExceptionMP("Couldn't instantiate " + pathModel, e);
		}
	}
	
	public void storeOutput() throws ExceptionMP
	{
		String filepath = Config.FILE_PATH_OUTPUT + mashupName + "_" + start + "_" + number + ".json";
		String output = model.getContent().getJSON();
		try
		{
			if (output != null) FileMP.write(filepath, output, false);
		} catch (IOException e)
		{
			throw new ExceptionMP("Could not store '" + filepath + "'", e);
		}
	}
}