package ch.ffhs.inf09.pa.mashup_platform.web.view;

import ch.ffhs.inf09.pa.mashup_platform.common.db.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.web.model.*;

public class ViewMashup extends ViewApplication
{	
	public ViewMashup(ModelMashup model) throws ExceptionMP
	{
		super(model);
		String part = getTemplate("html/mashup/mashup.html");
		content = content.replace(PLACEHOLDER_VIEW_APPLICATION, part);
		
		// define top pagination
		part = getTemplate("html/mashup/pagination_top.html");
		MashupPage page = model.getPage();
		MashupInfo info = model.getInfo();
		int pagenr = 0;
		if (page != null) pagenr = page.getPageNr();
		int numberPages = 0;
		if (info != null) numberPages = info.getNumberPages();
		content = content.replace(PLACEHOLDER_VIEW_PAGINATION_TOP, part);
		content = content.replace("[__MASHUP_PAGINATION_MAX__]", "" + numberPages);
		content = content.replace("[__MASHUP_PAGINATION_CURRENT__]", "" + pagenr);
		
		// define bottom pagination
		part = getTemplate("html/mashup/pagination_bottom.html");
		content = content.replace(PLACEHOLDER_VIEW_PAGINATION_BOTTOM, part);
		
		// insert html code
		part = getTemplate("html/mashup/" + page.getMashupIdent() + "/content.html");
		content = content.replace(PLACEHOLDER_VIEW_MASHUP, part);
		
		// insert renderer code
		part = getTemplate("html/mashup/" + page.getMashupIdent() + "/renderer.js");
		content = content.replace(PLACEHOLDER_VIEW_MASHUP_RENDERER, part);
		
		System.out.println("mark2: " + content);
		
		complete();
	}
}