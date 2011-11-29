package ch.ffhs.inf09.pa.jans_mashup_platform.web;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.*;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import ch.ffhs.inf09.pa.jans_mashup_platform.common.util.LoggerMP;

public class Environment {

	private HttpSession session;

	private HashMap<String, String> parameterValueMap = new HashMap<String, String>();
	private String filename;
	private FileItem item;

	public Environment(HttpServletRequest request) {
	
		session = request.getSession(true);
		
		String parameter = null;
		String value = null;

		if (ServletFileUpload.isMultipartContent(request)) {
			
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<?> items = null;

			try {
				items = upload.parseRequest(request);
			} catch (FileUploadException e) {
				LoggerMP.writeError(e);
				e.printStackTrace();
			}

			Iterator<?> iter = items.iterator();

			while (iter.hasNext()) {

				FileItem item = (FileItem) iter.next();

				if (item.isFormField()) {
					parameter = item.getFieldName();
					value = item.getString();
					parameterValueMap.put(parameter, value);					
				
				} else {
				
					setFilename(item.getName());
					setFileItem(item);
					
				}	
			}

		} else {

			Enumeration<String> p = request.getParameterNames();

			while (p.hasMoreElements()) {
				parameter = (String) p.nextElement();
				value = request.getParameter(parameter).toString();
				parameterValueMap.put(parameter, value);
			}
		}
	}

	public boolean login(String username, String password) {
		if (username != null && password != null && username.equals("admin")
				&& password.equals("admin")) {
			setUsername(username);
			return true;
		}
		return false;
	}

	public void logout() {
		setUsername(null);
	}

	public String getUsername() {
		return (String) session.getAttribute("username");
	}

	public void setUsername(String username) {
		session.setAttribute("username", username);
	}

	public boolean isUserLoggedIn() {
		return getUsername() != null;
	}

	public String getValuePost(String name) {
//		return request.getParameter(name);
		return parameterValueMap.get(name);
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public String getFilename() {
		return filename;
	}
	
	public void setFileItem(FileItem item) {
		this.item = item;
	}
	
	public FileItem getFileItem() {
		return item;
	}
	
}
