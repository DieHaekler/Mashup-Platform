package ch.ffhs.inf09.pa.mashup_platform.core.system.model.cache;

import java.io.IOException;

import ch.ffhs.inf09.pa.mashup_platform.common.util.LoggerMP;

/**
 * The base class defines the interface of a cache that can store and retrieve
 * objects by identifiers.
 * 
 * @author Malte
 * 
 */
public abstract class Cache {

	/**
	 * Stores an object
	 * 
	 * @param ident
	 *            the identifier of the object
	 * @param obj
	 *            the object to be stored
	 * @throws IOException
	 */
	public abstract void put(String ident, Object obj) throws IOException;

	/**
	 * Removes an object
	 */
	public abstract void remove(String ident) throws IOException;

	/**
	 * Returns the time stamp of the stored object
	 * 
	 * @param ident
	 *            the identifier of the stored object
	 * @return the value of the time stamp
	 * @throws IOException
	 */
	public abstract long getTimestamp(String ident) throws IOException;

	protected abstract Object get(String ident) throws IOException;

	/**
	 * Returns the cached object and removes it from the cache if the maximum
	 * age has been expired.
	 * 
	 * @param ident
	 *            the identifier of the object
	 * @param maxAge
	 *            the maximum age that the object can stay in the cache
	 * @return
	 * @throws IOException
	 */
	public Object getRecord(String ident, int maxAge) throws IOException {
		Object obj = get(ident);
		if (obj == null) {
			LoggerMP.writeNotice("no cache entry found for '" + ident + "'");
			return null;
		} else {
			int age = (int) (System.currentTimeMillis() / 1000 - getTimestamp(ident));
			LoggerMP.writeNotice("cache entry found (ident: '" + ident
					+ ", age: " + age + " sec)");
			if (age > maxAge) {
				remove(ident);
				LoggerMP.writeNotice("cache entry '" + ident + "' has expired");
				return null;
			} else {
				LoggerMP.writeNotice("cache entry '" + ident
						+ "' is still valid");
				return obj;
			}
		}
	}
}