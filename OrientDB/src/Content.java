import java.util.ArrayList;

public class Content {

	private ArrayList<Content> contents;
	private String caption;
	private static int level;

	public Content(String caption) {
		this.caption = caption;
		this.contents = new ArrayList<Content>();
	}

	public void addContent(Content content) {
		this.contents.add(content);
	}

	public String getCaption() {
		return this.caption;
	}

	public ArrayList<Content> getContents() {
		return this.contents;
	}

	public String toString() {
		String contentString = caption;

		for (Content content : this.contents) {
			level++;
			contentString += "\n" + getSpaces(level * 3) + content.toString();
			level--;
		}

		return contentString;
	}

	public String getSpaces(int number) {
		String spaces = "";
		for (int i = 0; i < number; i++) {
			spaces += " ";
		}
		return spaces;
	}

}
