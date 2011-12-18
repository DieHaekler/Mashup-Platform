package ch.ffhs.inf09.pa.mashup_platform.core.system.model.fetcher.flickr;

/**
 * This class is a simple container to keep the result of a Flickr search.
 * 
 * @author Malte
 * 
 */
public class ResultFlickr {
	private String url;
	private String imgURL;
	private String published;
	private String tags;

	public void setURL(String url) {
		this.url = url;
	}

	public void setImgURL(String url) {
		this.imgURL = url;
	}

	public void setPublished(String published) {
		this.published = published;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getURL() {
		return url;
	}

	public String getImgURL() {
		return imgURL;
	}

	public String getPublished() {
		return published;
	}

	public String getTags() {
		return tags;
	}
}