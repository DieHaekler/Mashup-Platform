package ch.ffhs.inf09.pa.mashup_platform.web.model;

import ch.ffhs.inf09.pa.mashup_platform.common.db.*;
//import ch.ffhs.inf09.pa.mashup_platform.core.system.model.persistence.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.ExceptionMP;
import ch.ffhs.inf09.pa.mashup_platform.config.DBConfig;

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
		try {
			db = new DBOrient(DBConfig.DB_USERNAME, DBConfig.DB_PASSWORD);
		} catch (ExceptionMP e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
