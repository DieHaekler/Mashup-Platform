package ch.ffhs.inf09.pa.mashup_platform.common.util;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.ffhs.inf09.pa.mashup_platform.config.Config;
import ch.ffhs.inf09.pa.mashup_platform.core.PlatformResetter;


public class LoggerMPTest {

	@BeforeClass
    public static void setUpBeforeClass() {
       PlatformResetter.main(null);
    }

    @AfterClass
    public static void tearDownAfterClass() {
        PlatformResetter.main(null);
    }
	
	@Test
	public void mainTest() throws FileNotFoundException, IOException{
		Calendar cal = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("yyyy_MM_dd");
		String filepath = Config.getInstance().getValue(
				Config.PARAM_FILE_PATH_SYSTEM) + "/log/log_"
				+ df.format(cal.getTime()) + ".txt";
		String errorPath = Config.getInstance().getValue(
				Config.PARAM_FILE_PATH_SYSTEM) + "/log/error_"
				+ df.format(cal.getTime()) + ".txt"; 
		FileMP.remove(filepath);
		LoggerMP.writeNotice("TestLog");
		
		
		assertTrue(FileMP.exists(filepath));
		
		
		
		df = new SimpleDateFormat("HH:mm:ss");
		String time = df.format(cal.getTime());	
		assertEquals(time + " --- " + "TestLog\n", FileMP.getContent(filepath));
		
		
		FileMP.remove(filepath);
		
		FileMP.remove(errorPath);
		
		ExceptionMP exceptionMP = new ExceptionMP("ExceptionMP", new Exception());
		LoggerMP.writeError(exceptionMP);
		assertTrue(FileMP.exists(errorPath));
		
		/*try {
			assertEquals(time + " --- " + "ch.ffhs.inf09.pa.mashup_platform.common.util.ExceptionMP: ExceptionMP\n" +
			"	at ch.ffhs.inf09.pa.mashup_platform.common.util.LoggerMPTest.mainTest(LoggerMPTest.java:55)\n" +
			"	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n" +
			"	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)\n" +
			"	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n" +
			"	at java.lang.reflect.Method.invoke(Method.java:601)\n" +
			"	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:45)\n" +
			"	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:15)\n" +
			"	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:42)\n" +
			"	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:20)\n" +
			"	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:263)\n" +
			"	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:68)\n" +
			"	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:47)\n" +
			"	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:231)\n" +
			"	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:60)\n" +
			"	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:229)\n" +
			"	at org.junit.runners.ParentRunner.access$000(ParentRunner.java:50)\n" +
			"	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:222)\n" +
			"	at org.junit.runners.ParentRunner.run(ParentRunner.java:300)\n" +
			"	at org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:50)\n" +
			"	at org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)\n" +
			"	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:467)\n" +
			"	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:683)\n" +
			"	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:390)\n" +
			"	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:197)\n" +
			  	"Caused by: java.lang.Exception\n" +
					"	... 24 more\n\n", FileMP.getContent(errorPath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		FileMP.remove(errorPath);
		
	}	
	
}
