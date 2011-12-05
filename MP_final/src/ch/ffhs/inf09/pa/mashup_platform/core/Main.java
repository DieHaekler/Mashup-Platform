package ch.ffhs.inf09.pa.mashup_platform.core;

import ch.ffhs.inf09.pa.mashup_platform.core.system.controller.background.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;

public class Main
{
	public static void main(String[] args)
	{
		try
		{
			new Thread(new Bot()).start();
			new Thread(new DBFeeder()).start();
		} catch (ExceptionMP e)
		{
			LoggerMP.writeError(e);
			e.printStackTrace();
		}
	}
}