package ch.ffhs.inf09.pa.mashup_platform.common.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import ch.ffhs.inf09.pa.mashup_platform.config.Config;

/**
 * The logger class generates centralized log files.
 * 
 * @author Malte
 * 
 */
public class LoggerMP {
	public static synchronized void writeNotice(String msg) {
		write(msg, "log");
	}

	/**
	 * Stores an error message to a log file.
	 * 
	 * @param e
	 *            the exception of the error
	 */
	public static synchronized void writeError(ExceptionMP e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		writeError(sw.toString());
	}

	/**
	 * Stores an error message to a log file.
	 * 
	 * @param msg
	 *            the error message
	 */
	public static synchronized void writeError(String msg) {
		write(msg, "error");
	}

	private static synchronized void write(String msg, String prefix) {
		Calendar cal = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("yyyy_MM_dd");
		String filepath = Config.getInstance().getValue(
				Config.PARAM_FILE_PATH_SYSTEM)
				+ "/log/" + prefix + "_" + df.format(cal.getTime()) + ".txt";
		df = new SimpleDateFormat("HH:mm:ss");
		msg = df.format(cal.getTime()) + " --- " + msg + "\n";
		try {
			FileMP.write(filepath, msg, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}