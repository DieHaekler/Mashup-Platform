package ch.ffhs.inf09.pa.mashup_platform.common.util;

import java.io.*;

public class FileMP
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
		stream.close();
	}
	
	public static String getContent(String filepath) throws IOException, FileNotFoundException
	{
		FileInputStream in = new FileInputStream(filepath);
		DataInputStream in2 = new DataInputStream(in);
		BufferedReader br = new BufferedReader(new InputStreamReader(in2,"UTF8"));
		String text = "";
		String line;
		while ( (line = br.readLine() ) != null)
		{
			text += line + "\n";
		}
		br.close();
		in2.close();
		in.close();
		return text;
	}
	
	public static Object get(String filepath) throws IOException, ClassNotFoundException
	{
		FileInputStream in = new FileInputStream(filepath);
		ObjectInputStream in2 = new ObjectInputStream(in);
		Object obj = in2.readObject();
		in2.close();
		in.close();
		return obj;
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