import java.awt.print.Printable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class WordCounter {
	private ArrayList<String> wcURLList;
	private String urlStr;
	private ArrayList<String> contentList = new ArrayList<String>();

	public WordCounter(ArrayList<String> wcURLList) {
		this.wcURLList = wcURLList;
	}

	public ArrayList<String> fetchContent() throws IOException { // 拋出異常
		for (int i = 0; i < wcURLList.size(); i++) {
			try {
				urlStr = wcURLList.get(i);
				if (urlStr.substring(0, 3) != "http") {
					// urlStr = "http://" + urlStr;

				}
				URL url = new URL(this.urlStr);
				URLConnection con = url.openConnection();
				InputStream in = con.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF8")); // 取得輸入

				String retVal = "";
				String line = null;

				while ((line = br.readLine()) != null) {
					retVal = retVal + line + "\n";
				}
				Document document = Jsoup.parse(retVal);

				retVal = document.text();
//				System.out.println(document.text());
				contentList.add(retVal);
			} catch (Exception e) {
				continue;
			}
		}

		return contentList;

	}

	public ArrayList<Integer> countKeywords(String con, String searchKeyword) throws IOException {

		ArrayList<String> keywordlist = new ArrayList(Arrays.asList(searchKeyword, "韓劇", "綜藝", "男團", "女團", "擔當", "團寵",
				"出演", "MUSIC BANK", "出道", "忙內", "舞蹈", "偶像", "音樂", "KPOP", "韓國", "韓團", "歌手", "一位", "Mnet", "回歸", "應援",
				"隊長", "SM娛樂", "JYP", "IDOL", "粉絲", "專輯"));
		ArrayList<Integer> countList = new ArrayList<>();

		String content = con;

		for (int k = 0; k < keywordlist.size(); k++) {
			// System.out.println(k);
			String keyword = keywordlist.get(k);
			// System.out.println(keyword);
			content = content.toUpperCase();
			keyword = keyword.toUpperCase();

			int retVal = 0;
			int fromIdx = 0;
			int found = -1;

			while ((found = content.indexOf(keyword, fromIdx)) != -1) {
				retVal++;
				fromIdx = found + keyword.length();
			}
			countList.add(retVal);
		}
		System.out.println(countList);
		return countList;
	}
}
