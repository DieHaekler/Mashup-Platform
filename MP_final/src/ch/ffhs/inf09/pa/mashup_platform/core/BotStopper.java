package ch.ffhs.inf09.pa.mashup_platform.core;

import ch.ffhs.inf09.pa.mashup_platform.core.system.controller.background.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;

public class BotStopper
{
	public static void main(String[] args)
	{
		try
		{
			new Bot().kill();
			new DBFeeder().kill();
		} catch (ExceptionMP e)
		{
			LoggerMP.writeError(e);
			e.printStackTrace();
		}
	}
}