package ch.ffhs.inf09.pa.mashup;

import ch.ffhs.inf09.pa.mashup.system.view.*;
import ch.ffhs.inf09.pa.mashup.system.controller.*;
import ch.ffhs.inf09.pa.mashup.system.util.*;

public class Main
{
	public static void main(String[] args)
	{
		String mashupName = "portrait_of_finnish_bands";
		String viewType = View.VIEW_TYPE_HTML;
		int start = 0;
		int number = 3;		
		String inputParams = mashupName + ", " + viewType + ", " + start + ", " + number;
		LoggerMashup.writeNotice("mashup platform started for input params: " + inputParams);
		try
		{
			Controller controller = new Controller(mashupName, viewType, start, number);
			LoggerMashup.writeNotice("main controller instantiated");
			View view = controller.getView();
			view.storeOutput(mashupName + "_" + start + "_" + number + "." + viewType);
		} catch (ExceptionMashup e)
		{
			LoggerMashup.writeError(e);
			e.printStackTrace();
		}
		LoggerMashup.writeNotice("mashup platform finished for input params: " + inputParams);
	}
}