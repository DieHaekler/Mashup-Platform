package ch.ffhs.inf09.pa.mashup_platform.core.system.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import org.junit.Test;

import ch.ffhs.inf09.pa.mashup_platform.common.util.ExceptionMP;

/**
 * This is a test class for the class Content.
 * 
 * @author Alexander
 * 
 */
public class ContentTest {

	@Test
	public void mainTest() throws ExceptionMP {
		// initialize test variables
		String caption = "caption";
		String heading = "heading";
		String body = "body";
		String footer = "footer";
		String imageURL = "imageURL";
		String intro = "intro";
		String keyword1 = "keyword1";
		String keyword2 = "keyword2";
		String keyword3 = "keyword3";
		String publishedDate = "2011-12-03 12:00:00";
		String publisher = "publisher";
		String publisherURL = "publisherURL";
		String url = "url";
		ArrayList<String> keywords = new ArrayList<String>(Arrays.asList(
				keyword1, keyword2));

		String newCaption = "newCaption";
		String newHeading = "newHeading";
		String newBody = "newBody";
		String newFooter = "newFooter";
		String newImageURL = "newImageURL";
		String newIntro = "newIntro";
		String newKeyword1 = "newKeyword1";
		String newKeyword2 = "newKeyword2";
		String newPublishedDate = "2011-12-03 12:00:01";
		String newPublisher = "newPublisher";
		String newPublisherURL = "newPublisherURL";
		String newUrl = "newUrl";
		ArrayList<String> newKeywords = new ArrayList<String>(Arrays.asList(
				newKeyword1, newKeyword2));

		String sectionContentCaption = "sectionContentCaption";
		String sectionCaption = "sectionCaption";
		String sectionContentHeading = "sectionContentHeading";
		String sectionContentBody = "sectionContentBody";
		String sectionContentFooter = "sectionContentFooter";
		String sectionContentImageURL = "sectionContentImageURL";
		String sectionContentIntro = "sectionContentIntro";
		String sectionKeyword1 = "sectionKeyword1";
		String sectionKeyword2 = "sectionKeyword2";
		String sectionContentPublishedDate = "2011-12-03 12:00:03";
		String sectionContentPublisher = "sectionContentPublisher";
		String sectionContentPublisherURL = "sectionContentPublisherURL";
		String sectionContentURL = "sectionContentUrl";
		ArrayList<String> sectionContentKeywords = new ArrayList<String>(
				Arrays.asList(sectionKeyword1, sectionKeyword2));
		Calendar cal = Calendar.getInstance();

		// create Content instance
		Content content = new Content();
		content.setCaption(caption);
		content.setHeading(heading);
		content.setBody(body);
		content.setFooter(footer);
		content.setImgURL(imageURL);
		content.setIntro(intro);
		content.setKeywords(keywords);
		content.setPublishedDate(publishedDate);
		content.setPublisher(publisher);
		content.setPublisherURL(publisherURL);
		content.setUrl(url);

		// check values
		assertEquals(caption, content.getCaption());
		assertEquals(heading, content.getHeading());
		assertEquals(body, content.getBody());
		assertEquals(footer, content.getFooter());
		assertEquals(imageURL, content.getImgURL());
		assertEquals(intro, content.getIntro());
		assertEquals(keyword1, content.getKeywords().get(0));
		assertEquals(publishedDate, content.getPublishedDate());
		assertEquals(publisher, content.getPublisher());
		assertEquals(publisherURL, content.getPublisherURL());
		assertEquals(url, content.getUrl());
		assertEquals(keywords, content.getKeywords());
		content.addKeyword(keyword3);
		assertEquals(keyword3, content.getKeywords().get(2));

		// create new Content
		Content newContent = new Content();
		newContent.setCaption(newCaption);
		newContent.setHeading(newHeading);
		newContent.setBody(newBody);
		newContent.setFooter(newFooter);
		newContent.setImgURL(newImageURL);
		newContent.setIntro(newIntro);
		newContent.setKeywords(newKeywords);
		newContent.setPublishedDate(newPublishedDate);
		newContent.setPublisher(newPublisher);
		newContent.setPublisherURL(newPublisherURL);
		newContent.setUrl(newUrl);

		// update old Content instance
		content.update(newContent);
		// check if the content has been updated
		assertEquals(newCaption, content.getCaption());
		assertEquals(newHeading, content.getHeading());
		assertEquals(newBody, content.getBody());
		assertEquals(newFooter, content.getFooter());
		assertEquals(newImageURL, content.getImgURL());
		assertEquals(newIntro, content.getIntro());
		assertEquals(newPublishedDate, content.getPublishedDate());
		assertEquals(newPublisher, content.getPublisher());
		assertEquals(newPublisherURL, content.getPublisherURL());
		assertEquals(newUrl, content.getUrl());
		assertEquals(newKeywords, content.getKeywords());

		// clear key words
		content.clearKeywords();
		assertEquals(0, content.getKeywords().size());

		// create new ContentSection instance and new Content instance within
		// this section
		ContentSection section = new ContentSection();
		Content sectionContent = new Content();
		sectionContent.setCaption(sectionContentCaption);
		sectionContent.setHeading(sectionContentHeading);
		sectionContent.setBody(sectionContentBody);
		sectionContent.setFooter(sectionContentFooter);
		sectionContent.setImgURL(sectionContentImageURL);
		sectionContent.setIntro(sectionContentIntro);
		sectionContent.setKeywords(sectionContentKeywords);
		sectionContent.setPublishedDate(sectionContentPublishedDate);
		sectionContent.setPublisher(sectionContentPublisher);
		sectionContent.setPublisherURL(sectionContentPublisherURL);
		sectionContent.setUrl(sectionContentURL);
		section.addPart(sectionContent);

		// add section to the existing content
		content.addSection(sectionCaption, section);

		// check values
		assertEquals(content.getSection(sectionCaption), section);
		assertEquals(
				"{\"caption\":\"newCaption\",\"imgURL\":\"newImageURL\",\"intro\":\"newIntro\",\"heading\":\"newHeading\",\"body\":\"newBody\",\"footer\":\"newFooter\",\"url\":\"newUrl\",\"publisher\":\"newPublisher\",\"publisherURL\":\"newPublisherURL\",\"publishedDate\":\"2011-12-03 12:00:01\",\"sectionCaption\":{\"parts\":[{\"caption\":\"sectionContentCaption\",\"imgURL\":\"sectionContentImageURL\",\"intro\":\"sectionContentIntro\",\"heading\":\"sectionContentHeading\",\"body\":\"sectionContentBody\",\"footer\":\"sectionContentFooter\",\"url\":\"sectionContentUrl\",\"publisher\":\"sectionContentPublisher\",\"publisherURL\":\"sectionContentPublisherURL\",\"publishedDate\":\"2011-12-03 12:00:03\",\"keywords\":[\"sectionKeyword1\",\"sectionKeyword2\"]}]}}",
				content.getJSON());
	}

}
