package ch.ffhs.inf09.pa.jans_mashup_platform.common.util;

public class ExceptionMP extends Exception
{
	private static final long serialVersionUID = 1L;
	
	public ExceptionMP(String msg, Exception e)
	{
		super(msg, e);
	}
}