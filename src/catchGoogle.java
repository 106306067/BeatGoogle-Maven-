import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class catchGoogle {
	public String searchKeyword;
	public String url;
	public String content;
	public ArrayList<String> urlList = new ArrayList<String>();
	public ArrayList<String> titleList = new ArrayList<String>();

	public catchGoogle(String searchKeyword) {
		this.searchKeyword = searchKeyword;
		this.url = "https://www.google.com.tw/search?q=" + searchKeyword + "&oe=utf8&num=20";
	}

	private String fetchContent() throws IOException {
		String retVal = "";
		URL urlStr = new URL(this.url);
		URLConnection connection = urlStr.openConnection();
		connection.setRequestProperty("User-Agent",
				"Mozilla/5.0(Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");// ???
		connection.connect();
		InputStream inputStream = connection.getInputStream();
		InputStreamReader inReader = new InputStreamReader(inputStream, "UTF8");
		BufferedReader bf = new BufferedReader(inReader);

		String line = null;
		while ((line = bf.readLine()) != null) {
			retVal += line;
		}
		return retVal;
	}

	public ArrayList<String> queryUrl() throws IOException {
		if (this.content == null) {
			this.content = fetchContent();
		}

		Document document = Jsoup.parse(this.content);
		Elements lis = document.select("div.g");

		for (Element li : lis) {
			try {
				Element h3 = li.select("h3.r").get(0);
				String title = h3.text();

				Element cite = li.getElementsByTag("a").first();
				String citeUrl = "https://www.google.com.tw" + cite.attr("href");
				titleList.add(title);
				urlList.add(citeUrl);
			} catch (IndexOutOfBoundsException e) {
				// Do nothing
			}
		}
		return urlList;
	}
	public ArrayList<String> queryTitle() throws IOException {
		if (this.content == null) {
			this.content = fetchContent();
		}

		Document document = Jsoup.parse(this.content);
		Elements lis = document.select("div.g");

		for (Element li : lis) {
			try {
				Element h3 = li.select("h3.r").get(0);
				String title = h3.text();

				
				titleList.add(title);
				
			} catch (IndexOutOfBoundsException e) {
				// Do nothing
			}
		}
		return titleList;
	}

}
