package ch.ffhs.inf09.pa.mashup_platform.web.view;

import ch.ffhs.inf09.pa.mashup_platform.common.util.ExceptionMP;
import ch.ffhs.inf09.pa.mashup_platform.web.model.ModelMashupOverview;

/**
 * The ViewMashupOverview class produces the HTML code of the mashup overview.
 * 
 * @author Joël
 * 
 */
public class ViewMashupOverview extends ViewApplication {
	public ViewMashupOverview(ModelMashupOverview model) throws ExceptionMP {
		super(model);
		String part = getTemplate("html/mashup/overview.html");
		content = content.replace(PLACEHOLDER_VIEW_APPLICATION, part);
		complete();
	}
}