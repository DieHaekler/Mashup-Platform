package ch.ffhs.inf09.pa.jans_mashup_platform.web.controller;

import java.io.File;

import ch.ffhs.inf09.pa.jans_mashup_platform.common.util.ExceptionMP;
import ch.ffhs.inf09.pa.jans_mashup_platform.common.util.FileMP;
import ch.ffhs.inf09.pa.jans_mashup_platform.common.util.LoggerMP;
import ch.ffhs.inf09.pa.jans_mashup_platform.web.Environment;
import ch.ffhs.inf09.pa.jans_mashup_platform.web.view.ViewAccount;
import ch.ffhs.inf09.pa.jans_mashup_platform.config.Config;

import org.apache.commons.fileupload.FileUploadException;

public class ControllerAccount extends ControllerApplication {

	public static final String PLACEHOLDER_IO_OUTPUT = "[__IO_OUTPUT__]";
	public static final String PLACEHOLDER_DIRECTORY = "[__DIRECTORY__]";

	public ControllerAccount(Environment environment) throws ExceptionMP {
		super(environment, new ViewAccount(environment));

		// Upload File if there is one
		if (environment.getFilename() != null) {
			try {
				FileMP.writeFile(Config.FILE_PATH_MASHUPS,
						environment.getFilename(), environment.getFileItem());
				this.view.replaceContent(PLACEHOLDER_IO_OUTPUT,
						"This Mashup has been uploaded");
			} catch (FileUploadException ex) {
				LoggerMP.writeError(ex);
				ex.printStackTrace();
				this.view.replaceContent(PLACEHOLDER_IO_OUTPUT, ex.toString());
			} catch (Exception e) {
				LoggerMP.writeError(e);
				e.printStackTrace();
				this.view.replaceContent(PLACEHOLDER_IO_OUTPUT, e.toString());
			}
		}

		// Create Directory Content
		File folder = new File(Config.FILE_PATH_MASHUPS);
		File[] listOfFiles = folder.listFiles();
		String content = "";

		if (listOfFiles.length == 0) {
			content = "No uploaded Mashups";
		} else {

			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					content =  content + listOfFiles[i].getName() + "<br>" + "\r";
				}
			}
		}

		// Draw Directory
		this.view.replaceContent(PLACEHOLDER_DIRECTORY, content);
		
	}

}