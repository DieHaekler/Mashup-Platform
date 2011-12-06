package ch.ffhs.inf09.pa.mashup_platform.common.db;

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
	
	public ArrayList<MashupInfo> getList()
	{
		return list;
	}
	
	public void addInfo(MashupInfo info)
	{
		list.add(info);
	}
	
	public int getSortedBy() { return sortedBy; }
}