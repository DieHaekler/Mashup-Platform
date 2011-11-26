package ch.ffhs.inf09.pa.mashup_platform.core;

import ch.ffhs.inf09.pa.mashup_platform.core.system.controller.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;

public class Main
{
	public static void main(String[] args)
	{
		// input params
		String mashupName = "portrait_of_finnish_bands";
		int start = 0;
		int number = 3;
		
		String inputParams = mashupName + ", " + start + ", " + number;
		LoggerMP.writeNotice("mashup platform started for input params: " + inputParams);
		try
		{
			Controller controller = new Controller(mashupName, start, number);
			LoggerMP.writeNotice("main controller instantiated");
			controller.storeOutput();
			LoggerMP.writeNotice("output stored");
		} catch (ExceptionMP e)
		{
			LoggerMP.writeError(e);
			e.printStackTrace();
		}
		LoggerMP.writeNotice("mashup platform finished for input params: " + inputParams);
	}
}