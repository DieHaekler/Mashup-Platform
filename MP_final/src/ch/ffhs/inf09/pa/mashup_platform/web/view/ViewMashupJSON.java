package ch.ffhs.inf09.pa.mashup_platform.web.view;

import ch.ffhs.inf09.pa.mashup_platform.common.db.MashupPage;
import ch.ffhs.inf09.pa.mashup_platform.common.util.ExceptionMP;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.Content;
import ch.ffhs.inf09.pa.mashup_platform.web.model.ModelMashup;

/**
 * The ViewMashupJSON class produces the JSON code of a single mashup page.
 * 
 * @author Joël
 * 
 */
public class ViewMashupJSON extends ViewApplication {
	public ViewMashupJSON(ModelMashup model) throws ExceptionMP {
		super(model);
		content = null;
		MashupPage page = model.getPage();
		if (page != null) {
			Content c = page.getContent();
			if (c != null) {
				content = c.getJSON();
			}
		}
		contentType = CONTENT_TYPE_JSON;
	}
}