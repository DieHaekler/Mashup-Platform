package ch.ffhs.inf09.pa.mashup.system.model.db;

import ch.ffhs.inf09.pa.mashup.system.model.*;
import ch.ffhs.inf09.pa.mashup.system.util.*;

public abstract class DB
{
	public abstract void fillIn(Content content, int start,
			int number) throws ExceptionMashup;
}