package test;
import java.util.ArrayList;

public class SortList {
	public String urlstr;
	public int score;
	public String webTitle;
	public String title;

	public SortList(String title, String urlstr, int score) {
		// super();
		this.title = title;
		this.urlstr = urlstr;
		this.score = score;
	}

	@Override
	public String toString() {
		return "[" + urlstr + ", " + score + "]" + "\n";
	}

}
