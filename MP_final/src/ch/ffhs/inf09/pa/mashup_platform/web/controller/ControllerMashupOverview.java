package ch.ffhs.inf09.pa.mashup_platform.web.controller;

import ch.ffhs.inf09.pa.mashup_platform.common.util.ExceptionMP;
import ch.ffhs.inf09.pa.mashup_platform.web.Environment;
import ch.ffhs.inf09.pa.mashup_platform.web.model.ModelMashupOverview;
import ch.ffhs.inf09.pa.mashup_platform.web.view.ViewMashupOverview;
import ch.ffhs.inf09.pa.mashup_platform.web.view.ViewMashupOverviewJSON;

/**
 * This class delivers the mashup overview.
 * 
 * @author Joël
 * 
 */
public class ControllerMashupOverview extends ControllerApplication {
	public ControllerMashupOverview(Environment environment) throws ExceptionMP {
		super(environment);
		String format = environment.getValuePost("format");
		ModelMashupOverview model = new ModelMashupOverview(environment);
		if (format != null && format.equals("json")) {
			setView(new ViewMashupOverviewJSON(model));
		} else {
			setView(new ViewMashupOverview(model));
		}
	}
}