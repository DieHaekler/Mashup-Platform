package ch.ffhs.inf09.pa.mashup_platform.core.system.controller;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import ch.ffhs.inf09.pa.mashup_platform.common.db.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.*;
import ch.ffhs.inf09.pa.mashup_platform.core.system.config.*;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.*;

public class Controller
{
	private Model model;
	private String mashupName;
	private int pagenr;
	
	public Controller(String mashupName, int pagenr) throws ExceptionMP
	{
		this.mashupName = mashupName;
		this.pagenr = pagenr;
		String pathVar = "ch.ffhs.inf09.pa.mashup_platform.var";
		
		// instantiate mashup config
		String filepath = Config.getFilepathVar() + "/" + mashupName
			+ "/config/config.properties";
		ConfigMashup config = new ConfigMashup(filepath);
		
		// instantiate mashup model
		String pathModel = pathVar + "." + mashupName + ".model.ModelMain";
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
	
	public void storeOutput() throws ExceptionMP
	{
		String filepath = Config.getInstance().getValue(Config.PARAM_FILE_PATH_SYSTEM)
			+ "/output/" + mashupName + "_" + pagenr + ".json";
		Mashup mashup = model.getMashup();
		MashupPage page = mashup.getPage();
		Content content = page.getContent();
		String output = content.getJSON();
		try
		{
			if (output != null) FileMP.write(filepath, output, false);
		} catch (IOException e)
		{
			throw new ExceptionMP("Could not store '" + filepath + "'", e);
		}
	}
	
	public void storeOutputDB() throws ExceptionMP
	{
		Config config = Config.getInstance();
		
		Mashup mashup = model.getMashup();
		DBLocal dbLocal = new DBOrient(config.getValue(Config.PARAM_DB_USERNAME), config.getValue(Config.PARAM_DB_PASSWORD));
		dbLocal.setMashup(mashup);
		Mashup dbMashup = dbLocal.getMashup("portrait_of_finnish_bands", 0);
		System.out.println(dbMashup.getIdent());
		System.out.println(dbMashup.getPage().getPageNr());
		System.out.println(dbMashup.getPage().getContent().getJSON());
	}
		
}