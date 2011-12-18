package ch.ffhs.inf09.pa.mashup_platform.core.system.controller;

import java.lang.reflect.Constructor;

import ch.ffhs.inf09.pa.mashup_platform.common.db.MashupPage;
import ch.ffhs.inf09.pa.mashup_platform.common.util.ExceptionMP;
import ch.ffhs.inf09.pa.mashup_platform.common.util.LoggerMP;
import ch.ffhs.inf09.pa.mashup_platform.config.Config;
import ch.ffhs.inf09.pa.mashup_platform.core.system.config.ConfigMashup;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.Model;

/**
 * The Controller generates a mashup page by executing a mashup for a specific
 * page number.
 * 
 * @author Malte
 * 
 */
public class Controller {
	private Model model;

	/**
	 * 
	 * @param ident
	 *            the mashup ident
	 * @param pagenr
	 *            the page number to be processed
	 * @throws ExceptionMP
	 */
	public Controller(String ident, int pagenr) throws ExceptionMP {
		String pathVar = "ch.ffhs.inf09.pa.mashup_platform.var";

		// load mashup config
		String filepath = Config.getFilepathVar() + "/" + ident
				+ "/config/config.properties";
		ConfigMashup config = new ConfigMashup(filepath);

		// instantiate mashup model
		String pathModel = pathVar + "." + ident + ".model.ModelMain";
		try {
			Class<?> c = (Class<?>) Class.forName(pathModel);
			Class<?>[] args = new Class[] { ConfigMashup.class };
			Constructor<?> ct = c.getConstructor(args);
			model = (Model) ct.newInstance(config);
			LoggerMP.writeNotice("[Controller] model instantiated ("
					+ pathModel + ")");
			model.setPageNr(pagenr);
		} catch (ClassNotFoundException e) {
			throw new ExceptionMP("[Controller] couldn't find " + pathModel, e);
		} catch (Exception e) {
			throw new ExceptionMP("[Controller] couldn't instantiate "
					+ pathModel, e);
		}
	}

	/**
	 * Returns a mashup page
	 * 
	 * @return the MashupPage object
	 */
	public MashupPage getPage() {
		return model.getPage();
	}
}