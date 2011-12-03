package ch.ffhs.inf09.pa.mashup_platform.var.portrait_of_finnish_bands.model;

import ch.ffhs.inf09.pa.mashup_platform.core.system.config.*;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.*;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.db.*;
import ch.ffhs.inf09.pa.mashup_platform.var.portrait_of_finnish_bands.model.db.*;
import ch.ffhs.inf09.pa.mashup_platform.common.db.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;
import ch.ffhs.inf09.pa.mashup_platform.config.Config;

public class ModelMain extends Model
{	
	public ModelMain(ConfigMashup config)
	{
		super(config);
	}
	
	public void setPageNr(int pagenr) throws ExceptionMP
	{
		int start = pagenr * numberRecordsPerPage;
		Content content = new Content();
		String filepath = Config.getFilepathVar()
			+ "/portrait_of_finnish_bands/config/db/DBFinnishBands.properties";
		DB db = new DBPortraitOfFinnishBands(filepath);
		db.fillIn(content, start, numberRecordsPerPage);
		MashupPage page = new MashupPage();
		page.setContent(content, pagenr);
		mashup.setPage(page);
	}
}