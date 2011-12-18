package ch.ffhs.inf09.pa.mashup_platform.web.controller;

import ch.ffhs.inf09.pa.mashup_platform.common.util.ExceptionMP;
import ch.ffhs.inf09.pa.mashup_platform.web.Environment;
import ch.ffhs.inf09.pa.mashup_platform.web.model.ModelUser;
import ch.ffhs.inf09.pa.mashup_platform.web.view.ViewAccount;

/**
 * This class controls the user account.
 * 
 * @author Joël
 * 
 */
public class ControllerAccount extends ControllerApplication {
	public ControllerAccount(Environment environment) throws ExceptionMP {
		super(environment);
		setView(new ViewAccount(new ModelUser(environment, "admin", "admin")));
	}
}