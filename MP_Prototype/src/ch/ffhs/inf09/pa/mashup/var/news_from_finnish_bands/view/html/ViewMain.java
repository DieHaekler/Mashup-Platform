package ch.ffhs.inf09.pa.mashup.var.news_from_finnish_bands.view.html;

import ch.ffhs.inf09.pa.mashup.system.view.*;
import ch.ffhs.inf09.pa.mashup.system.model.*;
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
				ArrayList<Content> newsList = band.getChildren();
				for (Content news: newsList)
				{
					html += "<p>" + news.getCaption() + "</p>\n";
				}
			}
		}
		html += "</body></html>";
		return html;
	}
	
}