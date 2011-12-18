package ch.ffhs.inf09.pa.mashup_platform.common.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class provides general methods for file operations.
 * 
 * @author Malte
 * 
 */
public class FileMP {
	public static void write(String filepath, Object obj) throws IOException {
		FileOutputStream out = new FileOutputStream(filepath);
		ObjectOutputStream out2 = new ObjectOutputStream(out);
		out2.writeObject(obj);
		out2.close();
		out.close();
	}

	/**
	 * Saves text to a file.
	 * 
	 * @param filepath
	 *            the absolute path of the file that gets created/updated
	 * @param msg
	 *            the text to be saved
	 * @param append
	 *            <code>true</code> if the text shall be appended at the end of
	 *            the file
	 * @throws IOException
	 */
	public static void write(String filepath, String msg, Boolean append)
			throws IOException {
		OutputStreamWriter stream = new OutputStreamWriter(
				new FileOutputStream(filepath, append), "UTF-8");
		BufferedWriter out = new BufferedWriter(stream);
		out.write(msg);
		out.close();
		stream.close();
	}

	/**
	 * Returns the content of a file.
	 * 
	 * @param filepath
	 *            the absolute path of a file
	 * @return the content of the file
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static String getContent(String filepath) throws IOException,
			FileNotFoundException {
		FileInputStream in = new FileInputStream(filepath);
		DataInputStream in2 = new DataInputStream(in);
		BufferedReader br = new BufferedReader(new InputStreamReader(in2,
				"UTF-8"));
		String text = "";
		String line;
		while ((line = br.readLine()) != null) {
			text += line + "\n";
		}
		br.close();
		in2.close();
		in.close();
		return text;
	}

	/**
	 * Returns the stored object.
	 * 
	 * @param filepath
	 *            the absolute path of the file
	 * @return the stored object
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object get(String filepath) throws IOException,
			ClassNotFoundException {
		FileInputStream in = new FileInputStream(filepath);
		ObjectInputStream in2 = new ObjectInputStream(in);
		Object obj = in2.readObject();
		in2.close();
		in.close();
		return obj;
	}

	public static boolean exists(String filepath) {
		return new File(filepath).exists();
	}

	public static long getTimestamp(String filepath) {
		File file = new File(filepath);
		return file.lastModified() / 1000;
	}

	/**
	 * Removes a file/folder.
	 * 
	 * @param filepath
	 *            the absolute path of the file
	 * @return
	 */
	public static boolean remove(String filepath) {
		File file = new File(filepath);
		if (!file.exists())
			return false;
		if (file.isDirectory()) {
			String[] children = file.list();
			for (String child : children) {
				boolean isOk = remove(filepath + "/" + child);
				if (!isOk)
					return false;
			}
			return file.delete();
		}
		return file.delete();
	}

	/**
	 * Returns a list of file names that are located in a specific folder.
	 * 
	 * @param folderpath
	 *            the absolute path of the folder
	 * @param regex
	 *            the regex to filter files
	 * @return
	 */
	public static ArrayList<String> getFilenames(String folderpath, String regex) {
		ArrayList<String> filenames = new ArrayList<String>();
		File dir = new File(folderpath);
		String[] list = dir.list();
		Pattern pattern = Pattern.compile(regex);
		for (String filename : list) {
			Matcher matcher = pattern.matcher(filename);
			if (matcher.find()) {
				filenames.add(filename);
			}
		}
		return filenames;
	}

	/**
	 * Moves/renames a file
	 * 
	 * @param filepath
	 *            the absolute path of the file
	 * @param filepathDesc
	 *            the destination path of the file
	 * @param force
	 *            <code>true</code> if an existing file shall be overwritten
	 * @return
	 */
	public static boolean move(String filepath, String filepathDesc,
			boolean force) {
		File file = new File(filepath);
		File fileDesc = new File(filepathDesc);
		if (force && fileDesc.exists()) {
			fileDesc.delete();
		}
		return file.renameTo(fileDesc);
	}
}