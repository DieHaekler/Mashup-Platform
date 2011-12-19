package ch.ffhs.inf09.pa.mashup_platform.core.system.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import org.junit.Test;

import ch.ffhs.inf09.pa.mashup_platform.common.util.ExceptionMP;

public class ContentTest {

	private String caption = "caption";
	private String heading = "heading";
	private String body = "body";
	private String footer = "footer";
	private String imageURL = "imageURL";
	private String intro = "intro";
	private String keyword1 = "keyword1";
	private String keyword2 = "keyword2";
	private String keyword3 = "keyword3";
	private String publishedDate = "2011-12-03 12:00:00";
	private String publisher = "publisher";
	private String publisherURL = "publisherURL";
	private String url = "url";
	private ArrayList<String> keywords = new ArrayList<String>(Arrays.asList(
			keyword1, keyword2));

	private String newCaption = "newCaption";
	private String newHeading = "newHeading";
	private String newBody = "newBody";
	private String newFooter = "newFooter";
	private String newImageURL = "newImageURL";
	private String newIntro = "newIntro";
	private String newKeyword1 = "newKeyword1";
	private String newKeyword2 = "newKeyword2";
	private String newPublishedDate = "2011-12-03 12:00:01";
	private String newPublisher = "newPublisher";
	private String newPublisherURL = "newPublisherURL";
	private String newUrl = "newUrl";
	private ArrayList<String> newKeywords = new ArrayList<String>(
			Arrays.asList(newKeyword1, newKeyword2));

	private String sectionContentCaption = "sectionContentCaption";
	private String sectionCaption = "sectionCaption";
	private String sectionContentHeading = "sectionContentHeading";
	private String sectionContentBody = "sectionContentBody";
	private String sectionContentFooter = "sectionContentFooter";
	private String sectionContentImageURL = "sectionContentImageURL";
	private String sectionContentIntro = "sectionContentIntro";
	private String sectionKeyword1 = "sectionKeyword1";
	private String sectionKeyword2 = "sectionKeyword2";
	private String sectionContentPublishedDate = "2011-12-03 12:00:03";
	private String sectionContentPublisher = "sectionContentPublisher";
	private String sectionContentPublisherURL = "sectionContentPublisherURL";
	private String sectionContentURL = "sectionContentUrl";
	private ArrayList<String> sectionContentKeywords = new ArrayList<String>(
			Arrays.asList(sectionKeyword1, sectionKeyword2));

	private Calendar cal = Calendar.getInstance();

	@Test
	public void mainTest() throws ExceptionMP {

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

		content.update(newContent);
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

		content.clearKeywords();

		assertEquals(0, content.getKeywords().size());

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

		content.addSection(sectionCaption, section);
		assertEquals(content.getSection(sectionCaption), section);
		assertEquals(
				"{\"caption\":\"newCaption\",\"imgURL\":\"newImageURL\",\"intro\":\"newIntro\",\"heading\":\"newHeading\",\"body\":\"newBody\",\"footer\":\"newFooter\",\"url\":\"newUrl\",\"publisher\":\"newPublisher\",\"publisherURL\":\"newPublisherURL\",\"publishedDate\":\"2011-12-03 12:00:01\",\"sectionCaption\":{\"parts\":[{\"caption\":\"sectionContentCaption\",\"imgURL\":\"sectionContentImageURL\",\"intro\":\"sectionContentIntro\",\"heading\":\"sectionContentHeading\",\"body\":\"sectionContentBody\",\"footer\":\"sectionContentFooter\",\"url\":\"sectionContentUrl\",\"publisher\":\"sectionContentPublisher\",\"publisherURL\":\"sectionContentPublisherURL\",\"publishedDate\":\"2011-12-03 12:00:03\",\"keywords\":[\"sectionKeyword1\",\"sectionKeyword2\"]}]}}",
				content.getJSON());
		assertEquals("a1b24f3c6f54b0aab4ce1d948e4b2f26", content.getHashCode());

	}

}
