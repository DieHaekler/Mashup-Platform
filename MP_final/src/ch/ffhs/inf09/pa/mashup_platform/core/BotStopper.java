package ch.ffhs.inf09.pa.mashup_platform.core;

import ch.ffhs.inf09.pa.mashup_platform.common.util.ExceptionMP;
import ch.ffhs.inf09.pa.mashup_platform.common.util.LoggerMP;
import ch.ffhs.inf09.pa.mashup_platform.core.system.controller.background.Bot;
import ch.ffhs.inf09.pa.mashup_platform.core.system.controller.background.DBFeeder;

/**
 * This class stops the Bot.
 * 
 * @author Joël
 * 
 */
public class BotStopper {
	public static void main(String[] args) {
		try {
			new Bot().kill();
			new DBFeeder().kill();
		} catch (ExceptionMP e) {
			LoggerMP.writeError(e);
			e.printStackTrace();
		}
	}
}