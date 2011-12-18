package ch.ffhs.inf09.pa.mashup_platform.web.controller;

import ch.ffhs.inf09.pa.mashup_platform.common.util.ExceptionMP;
import ch.ffhs.inf09.pa.mashup_platform.web.Environment;
import ch.ffhs.inf09.pa.mashup_platform.web.model.ModelMashup;
import ch.ffhs.inf09.pa.mashup_platform.web.view.ViewMashup;
import ch.ffhs.inf09.pa.mashup_platform.web.view.ViewMashupJSON;

/**
 * This class delivers a mashup page depending on the requested mashup ident and
 * page number.
 * 
 * @author Joël
 * 
 */
public class ControllerMashup extends ControllerApplication {
	public ControllerMashup(Environment environment) throws ExceptionMP {
		super(environment);
		String ident = environment.getValuePost("id");
		int pagenr = 0;
		try {
			pagenr = Integer.parseInt(environment.getValuePost("p"));
		} catch (NumberFormatException e) {
			pagenr = 0;
		}
		String format = environment.getValuePost("format");
		ModelMashup model = null;

		// get a valid page
		for (int i = 0; i < 3; i++) {
			model = new ModelMashup(environment, ident, pagenr);
			if (model.getPage() == null) {
				pagenr++;
			} else {
				break;
			}
		}

		if (format != null && format.equals("json")) {
			setView(new ViewMashupJSON(model));
		} else {
			setView(new ViewMashup(model));
		}
	}
}