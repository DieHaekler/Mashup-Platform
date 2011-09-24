package ch.ffhs.inf09.pa.mashup;

import ch.ffhs.inf09.pa.mashup.system.view.*;
import ch.ffhs.inf09.pa.mashup.system.controller.*;
import ch.ffhs.inf09.pa.mashup.system.util.*;

public class Main
{
	public static void main(String[] args)
	{
		String mashupName = "news_from_finnish_bands";
		String viewType = View.VIEW_TYPE_HTML;
		int start = 2;
		int number = 4;
		
		try
		{
			Controller controller = new Controller(mashupName, viewType,
				start, number);
			View view = controller.getView();
			System.out.println(view.getOutput());
		} catch (ExceptionMashup e)
		{
			e.printStackTrace();
		}
	}
}