package ch.ffhs.inf09.pa.jans_mashup_platform.common.util;

import ch.ffhs.inf09.pa.jans_mashup_platform.config.*;
import java.util.Calendar;
import java.io.*;
import java.text.*;

import org.apache.commons.fileupload.FileUploadException;

public class LoggerMP
{
	public static void writeNotice(String msg)
	{
		write(msg, "log");
	}
	
	public static void writeError(ExceptionMP e)
	{
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		writeError(sw.toString());
	}
	
	public static void writeError(FileUploadException e)
	{
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		writeError(sw.toString());
	}
	
	public static void writeError(Exception e)
	{
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		writeError(sw.toString());
	}
	
	public static void writeError(String msg)
	{
		write(msg, "error");
	}
	
	private static void write(String msg, String prefix)
	{
		Calendar cal = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("yyyy_MM_dd");
		String filepath = Config.FILE_PATH_LOG + prefix + "_"
			+ df.format(cal.getTime()) + ".txt";
		df = new SimpleDateFormat("HH:mm:ss");
		msg = df.format(cal.getTime()) + " --- " + msg + "\n";
		try
		{
			FileMP.write(filepath, msg, true);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}