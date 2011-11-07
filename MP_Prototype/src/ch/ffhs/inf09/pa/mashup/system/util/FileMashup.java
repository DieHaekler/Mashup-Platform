package ch.ffhs.inf09.pa.mashup.system.util;

import java.io.*;

public class FileMashup
{
	public static void write(String filepath, Object obj) throws IOException
	{
		FileOutputStream out = new FileOutputStream(filepath);
		ObjectOutputStream out2 = new ObjectOutputStream(out);
		out2.writeObject(obj);
		out2.close();
		out.close();
	}
	
	public static void write(String filepath, String msg, Boolean append) throws IOException
	{
		FileWriter stream = new FileWriter(filepath, append);
		BufferedWriter out = new BufferedWriter(stream);
		out.write(msg);
		out.close();
	}
	
	public static Object get(String filepath) throws IOException, ClassNotFoundException
	{
		FileInputStream in = new FileInputStream(filepath);
		ObjectInputStream in2 = new ObjectInputStream(in);
		return in2.readObject();
	}
	
	public static boolean exists(String filepath)
	{
		File file = new File(filepath);
		return file.exists();
	}
	
	public static long getTimestamp(String filepath)
	{
		File file = new File(filepath);
		return file.lastModified()/1000;
	}
	
	public static void remove(String filepath)
	{
		File file = new File(filepath);
		if (file.exists()) file.delete();
	}
}