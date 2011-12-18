package ch.ffhs.inf09.pa.mashup_platform.web.controller;

import ch.ffhs.inf09.pa.mashup_platform.common.util.ExceptionMP;
import ch.ffhs.inf09.pa.mashup_platform.web.Environment;
import ch.ffhs.inf09.pa.mashup_platform.web.model.ModelUser;
import ch.ffhs.inf09.pa.mashup_platform.web.view.ViewLogin;

/**
 * This class controls the login form.
 * 
 * @author Joël
 * 
 */
public class ControllerLogin extends ControllerApplication {
	public ControllerLogin(Environment environment) throws ExceptionMP {
		super(environment);
		setView(new ViewLogin(new ModelUser(environment, "admin", "admin")));
	}
}