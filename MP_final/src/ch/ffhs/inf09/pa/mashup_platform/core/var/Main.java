package ch.ffhs.inf09.pa.mashup_platform.core.var;

import ch.ffhs.inf09.pa.mashup_platform.core.system.view.*;
import ch.ffhs.inf09.pa.mashup_platform.core.system.controller.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;

public class Main
{
	public static void main(String[] args)
	{
		String mashupName = "portrait_of_finnish_bands";
		String viewType = View.VIEW_TYPE_HTML;
		int start = 0;
		int number = 3;		
		String inputParams = mashupName + ", " + viewType + ", " + start + ", " + number;
		LoggerMP.writeNotice("mashup platform started for input params: " + inputParams);
		try
		{
			Controller controller = new Controller(mashupName, viewType, start, number);
			LoggerMP.writeNotice("main controller instantiated");
			View view = controller.getView();
			view.storeOutput(mashupName + "_" + start + "_" + number + "." + viewType);
		} catch (ExceptionMP e)
		{
			LoggerMP.writeError(e);
			e.printStackTrace();
		}
		LoggerMP.writeNotice("mashup platform finished for input params: " + inputParams);
	}
}