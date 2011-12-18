package ch.ffhs.inf09.pa.mashup_platform.web.model;

import ch.ffhs.inf09.pa.mashup_platform.common.db.MashupInfo;
import ch.ffhs.inf09.pa.mashup_platform.common.db.MashupPage;
import ch.ffhs.inf09.pa.mashup_platform.web.Environment;

/**
 * The model class provides the data of the requested page.
 * 
 * @author Joël
 * 
 */
public class ModelMashup extends ModelApplication {
	private String ident;
	private int pageNr;

	public ModelMashup(Environment environment, String ident, int pageNr) {
		super(environment);
		this.ident = ident;
		this.pageNr = pageNr;
	}

	/**
	 * Returns the requested mashup page.
	 * 
	 * @return the mashup page object
	 */
	public MashupPage getPage() {
		return environment.getDB().getPage(ident, pageNr);
	}

	/**
	 * Returns meta information of the mashup
	 * 
	 * @return the MashupInfo object
	 */
	public MashupInfo getInfo() {
		return environment.getDB().getInfo(ident);
	}
}