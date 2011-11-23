package ch.ffhs.inf09.pa.mashup_platform.web.controller;

import ch.ffhs.inf09.pa.mashup_platform.web.Environment;
import ch.ffhs.inf09.pa.mashup_platform.web.view.*;
import ch.ffhs.inf09.pa.mashup_platform.common.util.*;

public class ControllerLogin extends ControllerApplication
{
	public ControllerLogin(Environment environment) throws ExceptionMP
	{
		super(environment, new ViewLogin(environment));
	}
}