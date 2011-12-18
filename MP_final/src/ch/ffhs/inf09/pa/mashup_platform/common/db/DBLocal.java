package ch.ffhs.inf09.pa.mashup_platform.common.db;

import ch.ffhs.inf09.pa.mashup_platform.common.util.ExceptionMP;

/**
 * The base class defines the interface of the local database that is used by
 * the kernel and the web environment to exchange mashup data.
 * 
 * @author Alexander
 * 
 */
public abstract class DBLocal {
	protected String dbUsername;
	protected String dbPassword;

	public DBLocal(String dbUsername, String dbPassword) throws ExceptionMP {
		this.dbUsername = dbUsername;
		this.dbPassword = dbPassword;
	}

	public abstract void close();

	public abstract MashupOverview getOverview(int start, int number,
			int sortedBy);

	/**
	 * Returns a specific mashup page
	 * 
	 * @param mashupIdent
	 *            the mashup ident
	 * @param pageNr
	 *            the page number
	 * @return the MashupPage object
	 */
	public abstract MashupPage getPage(String mashupIdent, int pageNr);

	/**
	 * Stores a mashup page
	 * 
	 * @param page
	 *            the page object
	 */
	public abstract void setPage(MashupPage page);

	public abstract User getUser(String username, String password);

	public abstract void setUser(User user);

	/**
	 * Returns information about a specific mashup
	 * 
	 * @param mashupIdent
	 *            the mashup ident
	 * @return
	 */
	public abstract MashupInfo getInfo(String mashupIdent);

	public synchronized MashupOverview getOverview(int start, int number) {
		return getOverview(start, number, MashupOverview.SORTED_BY_NAME_ASC);
	}
}