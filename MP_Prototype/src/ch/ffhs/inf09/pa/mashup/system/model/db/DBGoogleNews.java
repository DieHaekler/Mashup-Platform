package ch.ffhs.inf09.pa.mashup.system.model.db;

import java.util.*;
import ch.ffhs.inf09.pa.mashup.system.model.*;
import ch.ffhs.inf09.pa.mashup.system.util.*;

public class DBGoogleNews extends DB
{
	private static final String URL =
		"http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
	
	public void fillIn(Content content, int start, int number) throws ExceptionMashup
	{
		ArrayList<Content> children = content.getChildren();
		int end = start + number;
		if (end >= children.size()) end = children.size() - 1;
		List<Content> list = children.subList(start, start + number);
		for (Content child: list)
		{
			String caption = child.getCaption();
			
			// just for testing
			for (int i = 0; i < 3; i++)
			{
				Content news = new Content(i + ". News from " + caption);
				child.addChild(news);
			}
		}
	}

}