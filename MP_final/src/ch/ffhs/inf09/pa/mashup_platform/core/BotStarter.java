package ch.ffhs.inf09.pa.mashup_platform.core;

import ch.ffhs.inf09.pa.mashup_platform.common.util.ExceptionMP;
import ch.ffhs.inf09.pa.mashup_platform.common.util.LoggerMP;
import ch.ffhs.inf09.pa.mashup_platform.core.system.controller.background.Bot;
import ch.ffhs.inf09.pa.mashup_platform.core.system.controller.background.DBFeeder;

/**
 * This class starts the Bot.
 * 
 * @author Joël
 * 
 */
public class BotStarter {
	public static void main(String[] args) {
		try {
			Thread bot = new Thread(new Bot());
			bot.setName("bot");
			bot.start();
			Thread dbFeeder = new Thread(new DBFeeder());
			dbFeeder.setName("db_feeder");
			dbFeeder.start();
		} catch (ExceptionMP e) {
			LoggerMP.writeError(e);
			e.printStackTrace();
		}
	}
}