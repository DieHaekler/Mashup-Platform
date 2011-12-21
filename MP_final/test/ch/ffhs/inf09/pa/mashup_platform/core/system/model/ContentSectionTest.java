package ch.ffhs.inf09.pa.mashup_platform.core.system.model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This is a test class for the class ContentSection.
 * 
 * @author Alexander
 * 
 */
public class ContentSectionTest {

	@Test
	public void mainTest() {
		// create ContentSection instance
		String caption = "caption";
		ContentSection section = new ContentSection();
		section.setCaption(caption);

		// check caption
		assertEquals(caption, section.getCaption());

		// add new content to the section
		Content content = new Content();
		section.addPart(content);

		// check if the content has been added
		assertEquals(1, section.getParts().size());
		assertEquals(content, section.getParts().get(0));

	}
}