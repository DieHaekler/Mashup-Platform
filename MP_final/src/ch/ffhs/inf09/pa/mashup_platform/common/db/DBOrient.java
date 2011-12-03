package ch.ffhs.inf09.pa.mashup_platform.common.db;

import java.util.ArrayList;
import java.util.List;

import com.orientechnologies.orient.client.remote.OEngineRemote;
import com.orientechnologies.orient.core.Orient;
import com.orientechnologies.orient.core.db.object.ODatabaseObjectPool;
import com.orientechnologies.orient.core.db.object.ODatabaseObjectTx;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;

import ch.ffhs.inf09.pa.mashup_platform.common.util.ExceptionMP;
import ch.ffhs.inf09.pa.mashup_platform.config.Config;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.Content;
import ch.ffhs.inf09.pa.mashup_platform.core.system.model.ContentSection;

public class DBOrient extends DBLocal {
	private ODatabaseObjectTx dbMashups;
	private ODatabaseObjectTx dbUsers;
	private Config config = Config.getInstance();

	public DBOrient(String dbUsername, String dbPassword) throws ExceptionMP {
		super(dbUsername, dbPassword);
	}

	public void connect() {
		Orient.instance().registerEngine(new OEngineRemote());

		createObjectDatabase(config.getValue(Config.PARAM_DB_MASHUPS));
		createObjectDatabase(config.getValue(Config.PARAM_DB_USERS));

		dbMashups = ODatabaseObjectPool.global().acquire(
				config.getValue(Config.PARAM_DB_FOLDER_PATH) + config.getValue(Config.PARAM_DB_MASHUPS), this.dbUsername,
				this.dbPassword);
		dbUsers = ODatabaseObjectPool.global().acquire(
				config.getValue(Config.PARAM_DB_FOLDER_PATH) + config.getValue(Config.PARAM_DB_USERS), this.dbUsername,
				this.dbPassword);

		dbMashups.getEntityManager().registerEntityClass(Content.class);
		dbMashups.getEntityManager().registerEntityClass(MashupPage.class);
		dbMashups.getEntityManager().registerEntityClass(ContentSection.class);
		dbMashups.getEntityManager().registerEntityClass(Mashup.class);

		dbUsers.getEntityManager().registerEntityClass(User.class);

		if (getUserCount() == 0) {
			User user = new User();
			user.setUsername(config.getValue(Config.PARAM_DB_USERNAME));
			user.setPassword(config.getValue(Config.PARAM_DB_PASSWORD));
			setUser(user);
		}
	}

	public void close() {
		dbMashups.close();
		dbUsers.close();
	}

	public Mashups getMashups(int start, int number, int sortedBy, int status,
			String username) {
		Mashups mashups = new Mashups(status, sortedBy);
		if (dbMashups.getClusterType(config.getValue(Config.PARAM_DB_MASHUPS_MASHUP_CLASS_NAME)) != null) {
			String query = "select from "
					+ config.getValue(Config.PARAM_DB_MASHUPS_MASHUP_CLASS_NAME);

			switch (status) {
			case Mashups.STATUS_PENDING:
				query += " where status = " + Mashups.STATUS_PENDING;
				break;
			case Mashups.STATUS_ACTIVE:
				query += " where status = " + Mashups.STATUS_ACTIVE;
				break;
			case Mashups.STATUS_INACTIVE:
				query += " where status = " + Mashups.STATUS_INACTIVE;
				break;
			case Mashups.STATUS_REJECTED:
				query += " where status = " + Mashups.STATUS_REJECTED;
				break;
			}

			if (username != null) {
				query += " and username = '" + username + "'";
			}

			switch (sortedBy) {
			case Mashups.SORTED_BY_DATE_ASC:
				query += " order by createdAt ASC";
				break;
			case Mashups.SORTED_BY_DATE_DESC:
				query += " order by createdAt DESC";
				break;
			case Mashups.SORTED_BY_NAME_ASC:
				query += " order by name ASC";
				break;
			case Mashups.SORTED_BY_NAME_DESC:
				query += " order by name DESC";
				break;
			}

			List<Mashup> results = dbMashups.query(new OSQLSynchQuery<Mashup>(
					query));
			mashups.getList().addAll(results);
			ArrayList<Mashup> mashupsToRemove = new ArrayList<Mashup>();
			int mashupCount = results.size();
			for (int i = 0; i < mashupCount; i++) {
				if (i < start || i >= start + number) {
					mashupsToRemove.add(mashups.getList().get(i));
				}
			}
			mashups.getList().removeAll(mashupsToRemove);
		}

		return mashups;
	}

	public Mashup getMashup(String ident, int pagenr) {
		Mashup mashup = null;
		if (dbMashups.getClusterType(config.getValue(Config.PARAM_DB_MASHUPS_MASHUP_CLASS_NAME)) != null) {
			List<Mashup> results = dbMashups.query(new OSQLSynchQuery<Mashup>(
					"select from " + config.getValue(Config.PARAM_DB_MASHUPS_MASHUP_CLASS_NAME)
							+ " where ident = '" + ident
							+ "' and page.pagenr = " + pagenr));
			if (results.size() > 0) {
				mashup = results.get(0);
			}

		}

		return mashup;
	}

	public void setMashup(Mashup mashup) {
		List<Mashup> existingMashups = null;
		if (mashup != null
				&& dbMashups
						.getClusterType(config.getValue(Config.PARAM_DB_MASHUPS_MASHUP_CLASS_NAME)) != null) {
			existingMashups = dbMashups.query(new OSQLSynchQuery<Mashup>(
					"select from " + config.getValue(Config.PARAM_DB_MASHUPS_MASHUP_CLASS_NAME)
							+ " where ident = '" + mashup.getIdent() + "'"));
			for (Mashup m : existingMashups) {
				dbMashups.delete(m.getPage().getContent());
				dbMashups.delete(m.getPage());
				dbMashups.delete(m);
			}
			dbMashups.save(mashup);
		}
		

	}

	public User getUser(String username, String password) {
		User user = null;
		if (dbUsers.getClusterType(config.getValue(Config.PARAM_DB_USERS_CLASS_NAME)) != null) {
			List<User> results = dbUsers.query(new OSQLSynchQuery<Content>(
					"select from " + config.getValue(Config.PARAM_DB_USERS_CLASS_NAME)
							+ " where username = '" + username
							+ "' and password = '" + password + "'"));
			if (results.size() > 0) {
				user = results.get(0);
			}
		}

		return user;
	}

	public void setUser(User user) {
		if (user != null
				&& dbUsers.getClusterType(config.getValue(Config.PARAM_DB_USERS_CLASS_NAME)) != null) {
			List<User> results = dbUsers
					.query(new OSQLSynchQuery<Content>("select from "
							+ config.getValue(Config.PARAM_DB_USERS_CLASS_NAME)
							+ " where username = '" + user.getUsername() + "'"));
			for (User u : results) {
				dbUsers.delete(u);
			}
			dbUsers.save(user);
		}
	}

	/*
	 * private void createDocumentDatabase(String dbName) { ODatabaseDocumentTx
	 * db = new ODatabaseDocumentTx(config.getValue(Config.PARAM_DB_FILE_PATH + dbName); if
	 * (!db.exists()) { db.create(); } db.close(); }
	 */

	private void createObjectDatabase(String dbName) {
		System.out.println(config.getValue(Config.PARAM_DB_FOLDER_PATH) + config.getValue(Config.PARAM_DB_MASHUPS));
		ODatabaseObjectTx db = new ODatabaseObjectTx(config.getValue(Config.PARAM_DB_FOLDER_PATH)
				+ dbName);

		if (!db.exists()) {
			db.create();
		}
		db.close();

	}

	private long getUserCount() {
		long userNumber = 0;
		if (dbUsers.getClusterType(config.getValue(Config.PARAM_DB_USERS_CLASS_NAME)) != null) {
			userNumber = dbUsers
					.countClusterElements(config.getValue(Config.PARAM_DB_USERS_CLASS_NAME));
		}
		return userNumber;
	}
}
