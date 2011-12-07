package ch.ffhs.inf09.pa.mashup_platform.common.db;

import ch.ffhs.inf09.pa.mashup_platform.common.util.*;

public abstract class DBLocal
{
	protected String dbUsername;
	protected String dbPassword;
	
	public DBLocal(String dbUsername, String dbPassword) throws ExceptionMP
	{
		this.dbUsername = dbUsername;
		this.dbPassword = dbPassword;
	}
	
	public abstract void close();
	public abstract MashupOverview getOverview(int start, int number, int sortedBy);
	public abstract MashupPage getPage(String mashupIdent, int pageNr);
	public abstract void setPage(MashupPage page);
	public abstract User getUser(String username, String password);
	public abstract void setUser(User user);
	public abstract MashupInfo getInfo(String mashupIdent);
	
	public synchronized MashupOverview getOverview(int start, int number)
	{
		return getOverview(start, number, MashupOverview.SORTED_BY_NAME_ASC);
	}
}