package ch.ffhs.inf09.pa.mashup_platform.core.system.model;

import static org.junit.Assert.*;

import org.junit.Test;


public class ContentSectionTest
{
	
	private String caption = "caption";
	
	@Test
	public void mainTest(){
		ContentSection section = new ContentSection();
		section.setCaption(caption);
		assertEquals(caption, section.getCaption());
		Content content = new Content();
		
		section.addPart(content);
		assertEquals(1, section.getParts().size());
		assertEquals(content, section.getParts().get(0));
		
	}
}