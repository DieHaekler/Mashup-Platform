package ch.ffhs.inf09.pa.mashup_platform.common.util;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.ffhs.inf09.pa.mashup_platform.core.PlatformResetter;


public class ExeptionMPTest {

	@BeforeClass
    public static void setUpBeforeClass() {
       PlatformResetter.main(null);
    }

    @AfterClass
    public static void tearDownAfterClass() {
        PlatformResetter.main(null);
    }
	
	@Test
	public void mainTest(){
		String msg = "ExceptionMP";
		Exception cause = new Exception();
		ExceptionMP exceptionMP = new ExceptionMP(msg, cause);
		assertEquals(msg, exceptionMP.getMessage());
		assertEquals(cause, exceptionMP.getCause());
	}	
	
	
}
