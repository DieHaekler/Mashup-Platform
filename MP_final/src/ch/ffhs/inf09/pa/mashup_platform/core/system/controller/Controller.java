package ch.ffhs.inf09.pa.mashup_platform.core.system.controller;

import java.lang.reflect.*;
import ch.ffhs.inf09.pa.mashup_platform.common.db.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.*;
import ch.ffhs.inf09.pa.mashup_platform.core.system.config.*;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.*;

public class Controller
{
	private Model model;
	
	public Controller(String ident, int pagenr) throws ExceptionMP
	{
		String pathVar = "ch.ffhs.inf09.pa.mashup_platform.var";
		
		// instantiate mashup config
		String filepath = Config.getFilepathVar() + "/" + ident
			+ "/config/config.properties";
		ConfigMashup config = new ConfigMashup(filepath);
		
		// instantiate mashup model
		String pathModel = pathVar + "." + ident + ".model.ModelMain";
		try
		{
			Class<?> c = (Class<?>)Class.forName(pathModel);
			Class<?>[] args = new Class[] {ConfigMashup.class};
			Constructor<?> ct = c.getConstructor(args);
			model = (Model)ct.newInstance(config);
			LoggerMP.writeNotice("model instantiated (" + pathModel + ")");
			model.setPageNr(pagenr);
		} catch (ClassNotFoundException e)
		{
			throw new ExceptionMP("Couldn't find " + pathModel, e);
		} catch (Exception e)
		{
			throw new ExceptionMP("Couldn't instantiate " + pathModel, e);
		}
	}
	
	public MashupPage getPage() { return model.getPage(); }
}