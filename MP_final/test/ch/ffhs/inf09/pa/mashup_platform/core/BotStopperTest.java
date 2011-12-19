package ch.ffhs.inf09.pa.mashup_platform.core;

import java.util.Set;

import ch.ffhs.inf09.pa.mashup_platform.core.system.controller.background.*;
import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;

public class BotStopperTest
{
	@BeforeClass
    public static void setUpBeforeClass() {
       PlatformResetter.main(null);
       BotStarter.main(null);
     
    }

    @AfterClass
    public static void tearDownAfterClass() {
        PlatformResetter.main(null);
    }
	
	@Test
	public void mainTest() throws InterruptedException
	{			
		boolean bot = true;
		boolean dbFeeder = true; 
	
		while(bot || dbFeeder){
			BotStopper.main(null);
			bot = false;
			dbFeeder = false;
			Set<Thread>threadSet = Thread.getAllStackTraces().keySet(); 
			for(Thread t: threadSet){
				if(t.getName().equals("bot")){
					bot = true;
				}
				if(t.getName().equals("db_feeder")){
					dbFeeder = true;
				}
			}
			Thread.sleep(1000);
		}
				
		assertFalse(bot);
		assertFalse(dbFeeder);
	}
}