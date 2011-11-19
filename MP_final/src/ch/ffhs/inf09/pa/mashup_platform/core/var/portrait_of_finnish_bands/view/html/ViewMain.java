package ch.ffhs.inf09.pa.mashup_platform.core.var.portrait_of_finnish_bands.view.html;

import ch.ffhs.inf09.pa.mashup_platform.core.system.view.*;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.*;
import java.util.*;

public class ViewMain extends ViewHTML
{
	public ViewMain(Model model)
	{
		super(model);
	}
	
	public String getOutput()
	{
		String html = "<html><head></head><body>\n";
		Content content = model.getContent();
		if (content != null)
		{
			html += "<h1>" + content.getCaption() + "</h1>\n";
			ArrayList<Content> bands = content.getChildren();
			for (Content band: bands)
			{
				html += "<h2>" + band.getCaption() + "</h2>\n";
				ArrayList<Content> portraits = band.getChildren();
				for (Content portrait: portraits)
				{
					html += "<h3>" + portrait.getCaption() + "</h3>\n";
					html += "<p>" + portrait.getBody()
						+ " <a href=\"" + portrait.getUrl() + "\" target=\"_blank\">more</a></p>";
				}
			}
		}
		html += "</body></html>";
		return html;
	}
	
}