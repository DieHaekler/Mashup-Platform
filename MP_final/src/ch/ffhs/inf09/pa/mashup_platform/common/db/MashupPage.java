package ch.ffhs.inf09.pa.mashup_platform.common.db;

import ch.ffhs.inf09.pa.mashup_platform.core.system.model.*;
import java.io.*;
import java.util.*;
import javax.persistence.Id;
import javax.persistence.Version;

public class MashupPage implements Serializable {
	private static final long serialVersionUID = 1L;
	private Content content;
	private String mashupIdent;
	private int pageNr;
	private String name;
	private String username;
	private Date lastUpdated;
	private Date createdAt;

	@Id
	private Object id;

	@Version
	private Object version;

	public void setMashupIdent(String ident) {
		mashupIdent = ident;
	}

	public void setPageNr(int nr) {
		pageNr = nr;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public int getPageNr() {
		return pageNr;
	}

	public String getMashupIdent() {
		return mashupIdent;
	}

	public String getName() {
		return name;
	}

	public String getUsername() {
		return username;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public Content getContent() {
		return content;
	}
}