public class DBGoogleNews extends DB {

	public Content fillIn(Content content, int start, int limit,
			String arguments) {

		if (limit < 0 || limit > content.getContents().size()) {
			limit = content.getContents().size();
		}

		for (int i = start; i < limit; i++) {
			executeYQL(content.getContents().get(i));
		}

		return content;
	}

	protected Content executeYQL(Content content) {

		Content newsContent = new Content("All News from "
				+ content.getCaption());

		Content news1 = new Content("1st News from " + content.getCaption());
		Content news2 = new Content("2nd News from " + content.getCaption());
		Content news3 = new Content("3rd News from " + content.getCaption());

		newsContent.addContent(news1);
		newsContent.addContent(news2);
		newsContent.addContent(news3);

		content.addContent(newsContent);

		return content;

	}

}
