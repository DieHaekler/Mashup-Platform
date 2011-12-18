package ch.ffhs.inf09.pa.mashup_platform.web.model;

import ch.ffhs.inf09.pa.mashup_platform.common.db.DBLocal;
import ch.ffhs.inf09.pa.mashup_platform.web.Environment;

/**
 * The base class defines the interface for all application models.
 * 
 * @author Joël
 * 
 */
public abstract class ModelApplication {
	protected Environment environment;

	public ModelApplication(Environment environment) {
		this.environment = environment;
	}

	public Environment getEnvironment() {
		return environment;
	}

	public DBLocal getDB() {
		return environment.getDB();
	}
}
