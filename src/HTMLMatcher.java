import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;

public class HTMLMatcher {

	// 等待爬取的url
	private static List<String> allwaiturl = new ArrayList<>();
	// 爬取過的url
	private static Set<String> alloverurl = new HashSet<>();
	// 記錄所有url的深度進行爬取判斷
	private static Map<String, Integer> allurldepth = new HashMap<>();
	// 爬取得深度
	private static int maxdepth = 1;

	public static List<String> urlList = new ArrayList<>();

	public String urlStr;

	public HTMLMatcher(String urlStr) {
		this.urlStr = urlStr;
	}

	public static List<String> workurl(String strurl, int depth) {
		// 判斷當前url是否爬取過
		if (!alloverurl.contains(strurl) && depth <= maxdepth) {
			// 創建url爬取核心對象
			try {
				URL url = new URL(strurl);
				// 通過url創建與網頁的連接
				URLConnection conn = url.openConnection();
				// 通過鏈接取得網頁返回的數據
				InputStream is = conn.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is, "GB2312"));

				String line = null;
				// 正則表達式的匹配規則提取該網頁的鏈接
				Pattern p = Pattern.compile("<a .*href=.+</a>");

				while ((line = br.readLine()) != null) {
					// System.out.println(line);
					// 編寫正則，匹配超鏈接地址

					Matcher m = p.matcher(line);
					while (m.find()) {
						String href = m.group();
						// 找到超鏈接地址並截取字符串
						// 有無引號
						href = href.substring(href.indexOf("href="));
						if (href.charAt(5) == '\"') {
							href = href.substring(6);
						} else {
							href = href.substring(5);
						}
						// 截取到引號或者空格或者到">"結束
						try {
							href = href.substring(0, href.indexOf("\""));
						} catch (Exception e) {
							try {
								href = href.substring(0, href.indexOf(" "));
							} catch (Exception e1) {
								href = href.substring(0, href.indexOf(">"));
							}
						}
						if (href.startsWith("http:") || href.startsWith("https:")) {
							// 輸出該網頁存在的鏈接
							// System.out.println(href);
							// 將url地址放到隊列中
							allwaiturl.add(href);
							allurldepth.put(href, depth + 1);

							urlList.add(href);
							System.out.println(href);
						}

					}

				}
				br.close();
			} catch (Exception e) {
			}
			// 將當前url歸列到alloverurl中
			alloverurl.add(strurl);
			// System.out.println(strurl + "網頁爬取完成，已爬取數量：" + alloverurl.size() + "，剩餘爬取數量："

		}

		return urlList;
	}

}
