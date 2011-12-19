package ch.ffhs.inf09.pa.mashup_platform.core;

import java.util.ArrayList;
import java.util.Set;

import ch.ffhs.inf09.pa.mashup_platform.core.system.controller.background.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.Config;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class BotStarterTest
{
	@BeforeClass
    public static void setUpBeforeClass() {
       PlatformResetter.main(null);
    }

    @AfterClass
    public static void tearDownAfterClass() {
    	BotStopper.main(null);
        PlatformResetter.main(null);
    }
	
	@Test
	public void mainTest(){
		BotStarter.main(null);
		Set<Thread> threadSet = Thread.getAllStackTraces().keySet(); 
		boolean bot = false;
		boolean dbFeeder = false;

		for(Thread t: threadSet){
			if(t.getName().equals("bot")){
				bot = true;
			}
			if(t.getName().equals("db_feeder")){
				dbFeeder = true;
			}
		}
		
		assertTrue(bot);
		assertTrue(dbFeeder);		
	}
}