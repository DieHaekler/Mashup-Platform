package ch.ffhs.inf09.pa.mashup.system.util;

public class ExceptionMashup extends Exception
{
	private static final long serialVersionUID = 1L;
	
	public ExceptionMashup(String msg, Exception e)
	{
		super(msg, e);
	}
}