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
		/*
		DBLocal dbLocal = new DBOrient(DBConfig.DB_USERNAME, DBConfig.DB_PASSWORD);		
		Mashup mashup = new Mashup();
		mashup.setIdent("0001");
		mashup.setName("Portrait of Finnish Bands (4 Entries)");
		mashup.setContent(model.getContent());		
		mashup.setStart(start);
		mashup.setNumber(number);
		mashup.setUsername(DBConfig.DB_USERNAME);
		mashup.setCreatedAt(new Date());
		dbLocal.setMashup(mashup);
		
		System.out.println(dbLocal.getMashup("0001", 0, 2).getContent().getJSON());
		System.out.println(dbLocal.getMashup("0001", 0, 1).getContent().getJSON());
		
		Mashups mashups = dbLocal.getMashups(0, 50, Mashups.SORTED_BY_DATE_DESC, Mashups.STATUS_ALL, "admin");
		for(Mashup mash: mashups.getList()){
			System.out.println(mash.getCreatedAt());
		}
		
		dbLocal.close();
		*/
		
		/*DBLocal dbLocal = new DBOrient(DBConfig.DB_USERNAME, DBConfig.DB_PASSWORD, DBConfig.DB_FILE_PATH, DBConfig.DB_MASHUPS,
		DBConfig.DB_MASHUPS_CLASS_NAME, DBConfig.DB_USERS, DBConfig.DB_USERS_CLASS_NAME);	*/
		//dbLocal.storeMashup(model.getContent());
		/*System.out.println(dbLocal.getMashupJSON("Portrait of Finnish Bands"));
		System.out.println(dbLocal.getMashupsFromUserJSON(DBConfig.DB_USERNAME));
		System.out.println(dbLocal.getMashupsFromUser(DBConfig.DB_USERNAME).get(0).getJSON());*/
	}
		
}