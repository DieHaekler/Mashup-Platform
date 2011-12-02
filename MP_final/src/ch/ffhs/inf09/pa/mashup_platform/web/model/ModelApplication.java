package ch.ffhs.inf09.pa.mashup_platform.web.model;

import ch.ffhs.inf09.pa.mashup_platform.common.db.*;
//import ch.ffhs.inf09.pa.mashup_platform.core.system.model.persistence.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.ExceptionMP;
import ch.ffhs.inf09.pa.mashup_platform.config.*;

public abstract class ModelApplication
{
	protected DBLocal db;
	
	public ModelApplication()
	{
		//initDBLocal();
	}
	
	public void setDB(DBLocal db)
	{
		this.db = db;
	}

	private void initDBLocal()
	{
		Config config = Config.getInstance();
		try {
			db = new DBOrient(config.getValue(Config.PARAM_DB_USERNAME), config.getValue(Config.PARAM_DB_PASSWORD));
		} catch (ExceptionMP e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
