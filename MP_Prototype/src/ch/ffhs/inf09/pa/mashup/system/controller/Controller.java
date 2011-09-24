package ch.ffhs.inf09.pa.mashup.system.controller;

import java.lang.reflect.*;
import ch.ffhs.inf09.pa.mashup.system.util.*;
import ch.ffhs.inf09.pa.mashup.system.model.*;
import ch.ffhs.inf09.pa.mashup.system.view.*;

public class Controller
{
	private Model model;
	private View view;
	
	public Controller(String mashupName, String viewType, int start,
		int number) throws ExceptionMashup
	{
		String pathVar = "ch.ffhs.inf09.pa.mashup.var";
		String pathModel = pathVar + "." + mashupName
			+ ".model.ModelMain";
		String pathView = pathVar + "." + mashupName
			+ ".view." + viewType + ".ViewMain";
		
		try
		{
			Class<?> c = (Class<?>)Class.forName(pathModel);
			Constructor<?> ct = c.getConstructor();
			model = (Model)ct.newInstance();
			model.setRange(start, number);
		} catch (ClassNotFoundException e)
		{
			throw new ExceptionMashup("Couldn't find " + pathModel, e);
		} catch (Exception e)
		{
			throw new ExceptionMashup("Couldn't instantiate " + pathModel, e);
		}
		
		try
		{
			Class<?> c = (Class<?>) Class.forName(pathView);
			Class<?> partypes[] = new Class[1];
			partypes[0] = Model.class;
			Constructor<?> ct = c.getConstructor(partypes);
			Object arglist[] = new Object[1];
			arglist[0] = model;
			view = (View)ct.newInstance(arglist);
		} catch (ClassNotFoundException e)
		{
			throw new ExceptionMashup("Couldn't find " + pathView, e);
		} catch (Exception e)
		{
			throw new ExceptionMashup("Couldn't instantiate " + pathView, e);
		}
		
	}
	
	public View getView()
	{
		return view;
	}
}