package ch.ffhs.inf09.pa.mashup_platform.common.util;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		OutputStreamWriter stream = new OutputStreamWriter(
			new FileOutputStream(filepath, append), "UTF-8");
		BufferedWriter out = new BufferedWriter(stream);
		out.write(msg);
		out.close();
		stream.close();
	}
	
	public static String getContent(String filepath) throws IOException, FileNotFoundException
	{
		FileInputStream in = new FileInputStream(filepath);
		DataInputStream in2 = new DataInputStream(in);
		BufferedReader br = new BufferedReader(new InputStreamReader(in2, "UTF-8"));
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
		return new File(filepath).exists();
	}
	
	public static long getTimestamp(String filepath)
	{
		File file = new File(filepath);
		return file.lastModified()/1000;
	}
	
	public static boolean remove(String filepath)
	{
		File file = new File(filepath);
		if ( !file.exists() ) return false;
		if (file.isDirectory())
		{
			String[] children = file.list();
			for(String child: children)
			{
				boolean isOk = remove(filepath + "/" + child);
				if (!isOk) return false;
			}
			return file.delete();
		}
		return file.delete();
	}
	
	public static ArrayList<String> getFilenames(String folderpath, String regex)
	{
		ArrayList<String> filenames = new ArrayList<String>();
		File dir = new File(folderpath);
		String[] list = dir.list();
		Pattern pattern = Pattern.compile(regex);
		for(String filename: list)
		{
			Matcher matcher = pattern.matcher(filename);
			if ( matcher.find() )
			{
				filenames.add(filename);
			}
		}
		return filenames;
	}
	
	public static boolean move(String filepath, String filepathDesc, boolean force)
	{
		File file = new File(filepath);
		File fileDesc = new File(filepathDesc);
		if (force && fileDesc.exists()) { fileDesc.delete(); }
		return file.renameTo(fileDesc);
	}
}