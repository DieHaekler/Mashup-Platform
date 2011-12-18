package ch.ffhs.inf09.pa.mashup_platform.web.view;

import ch.ffhs.inf09.pa.mashup_platform.common.db.MashupOverview;
import ch.ffhs.inf09.pa.mashup_platform.common.util.ExceptionMP;
import ch.ffhs.inf09.pa.mashup_platform.web.model.ModelMashupOverview;

/**
 * The ViewMashupOverviewJSON class produces the JSON code of the mashup
 * overview.
 * 
 * @author Joël
 * 
 */
public class ViewMashupOverviewJSON extends ViewApplication {
	public ViewMashupOverviewJSON(ModelMashupOverview model) throws ExceptionMP {
		super(model);
		MashupOverview overview = model.get(0, 10);
		content = overview.getJSON();
		contentType = CONTENT_TYPE_JSON;
	}
}