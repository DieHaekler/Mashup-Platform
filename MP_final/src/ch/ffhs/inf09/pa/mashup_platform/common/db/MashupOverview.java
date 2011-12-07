package ch.ffhs.inf09.pa.mashup_platform.common.db;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class MashupOverview
{	
	public static final int SORTED_BY_NAME_ASC = 1;
	public static final int SORTED_BY_NAME_DESC = 2;
	public static final int SORTED_BY_DATE_ASC = 3;
	public static final int SORTED_BY_DATE_DESC = 4;
	private int sortedBy;
	private ArrayList<MashupInfo> list = new ArrayList<MashupInfo>();
	
	public MashupOverview(int sortedBy)
	{
		this.sortedBy = sortedBy;
	}

	public void add(MashupInfo info)
	{
		list.add(info);
	}
	
	public void addInfo(MashupInfo info)
	{
		list.add(info);
	}
	
	public int getSortedBy() { return sortedBy; }
	
	public ArrayList<MashupInfo> getList()
	{
		return list;
	}
	
	public String getJSON()
	{
		String s = "";
		DateFormat df1 = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		DateFormat df2 = new SimpleDateFormat("HH:mm:ss");
		for(MashupInfo info: list)
		{
			s += "{\"id\":\"" + info.getMashupIdent() + "\","
				+ "\"name\":\"" + info.getName() + "\","
				+ "\"username\":\"" + info.getUsername() + "\","
				+ "\"lastUpdated\":\"" + df1.format(info.getLastUpdated()) + "\","
				+ "\"createdAt\":\"" + df2.format(info.getCreatedAt()) + "\","
				+ "\"numberPages\":\"" + info.getNumberPages() + "\""
				+ "},";
		}
		if ( !s.equals("") ) s = s.substring(0, s.length() - 1);
		return "{\"mashups\":[" + s + "]}";
	}
}