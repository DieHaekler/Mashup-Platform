package ch.ffhs.inf09.pa.mashup_platform.common.util;

/**
 * The exception class is used as container for error messages.
 * 
 * @author Malte
 * 
 */
public class ExceptionMP extends Exception {
	private static final long serialVersionUID = 1L;

	public ExceptionMP(String msg, Exception e) {
		super(msg, e);
	}
}