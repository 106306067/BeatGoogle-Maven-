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
	// private String content;
	private ArrayList<String> wcURLList;
	private String urlStr;
	private ArrayList<String> contentList = new ArrayList<String>();
	// public ArrayList<String> scoreList1 = new ArrayList<String>();
	// private ArrayList<String> scoreList2 = new ArrayList<String>();

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
				// String retValStr = new String(retVal.getBytes("ISO-8859-1"),"BIG5");
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

	// public void countKeyword(String keyword) throws IOException {
	// ArrayList<String> runList = fetchContent();
	// //System.out.println(runList.get(0));
	//
	// if (content == null) {
	// for (int i = 0; i < runList.size(); i++) {
	//
	// content = runList.get(i);
	// ArrayList<String> keywordlist = new ArrayList(
	// Arrays.asList("韓國", "偶像", "音樂", "KPOP", "韓團", "一位", "Mnet", "回歸", "音源",
	// "應援"));
	// int key0 = 0;
	// int key1 = 0;
	// int key2 = 0;
	// int key3 = 0;
	// int key4 = 0;
	// int key5 = 0;
	// int key6 = 0;
	// int key7 = 0;
	// int key8 = 0;
	// int key9 = 0;
	// int keyE = 0;
	// // int score1 = TypeCounter.getScore1();
	// // int score2=(key0+key1+key2+key3+key4)*2+(key5+key6+key7+key8+key9)+keyE*5;
	//
	// content = content.toUpperCase();
	// keyword = keyword.toUpperCase();
	//
	// int type = 0;
	// int score1 = type * 100;
	// for (int n = 0; content.indexOf(keywordlist.get(n)) >= 0 && n <= 9; n++) {
	// type++;
	// }
	// String score1Str = Integer.toString(score1);
	// scoreList1.add(score1Str);
	//
	// String k0 = keywordlist.get(0).toUpperCase();
	// int m0 = k0.length();
	// int n0 = content.length();
	// int conIndex0 = 0; // 開始找的位置
	// int keyIndex0 = 0;// 出現位置
	//
	// for (key0 = 0; conIndex0 <= (content.length() - 1); key0++) {
	// keyIndex0 = content.indexOf(k0, conIndex0);
	// if (keyIndex0 == -1) {
	// break;
	// }
	// conIndex0 = keyIndex0 + k0.length();
	// }
	//
	// String k1 = keywordlist.get(1).toUpperCase();
	// int m1 = k1.length();
	// int n1 = content.length();
	// int conIndex1 = 0; // 開始找的位置
	// int keyIndex1 = 0;// 出現位置
	//
	// for (key1 = 0; conIndex1 <= (content.length() - 1); key1++) {
	// keyIndex1 = content.indexOf(k1, conIndex1);
	// if (keyIndex1 == -1) {
	// break;
	// }
	// conIndex1 = keyIndex1 + k1.length();
	// }
	//
	// String k2 = keywordlist.get(2).toUpperCase();
	// int m2 = k2.length();
	// int n2 = content.length();
	// int conIndex2 = 0; // 開始找的位置
	// int keyIndex2 = 0;// 出現位置
	//
	// for (key2 = 0; conIndex2 <= (content.length() - 1); key2++) {
	// keyIndex2 = content.indexOf(k2, conIndex2);
	// if (keyIndex2 == -1) {
	// break;
	// }
	// conIndex2 = keyIndex2 + k2.length();
	// }
	//
	// String k3 = keywordlist.get(3).toUpperCase();
	// int m3 = k3.length();
	// int n3 = content.length();
	// int conIndex3 = 0; // 開始找的位置
	// int keyIndex3 = 0;// 出現位置
	//
	// for (key3 = 0; conIndex3 <= (content.length() - 1); key3++) {
	// keyIndex3 = content.indexOf(k3, conIndex3);
	// if (keyIndex3 == -1) {
	// break;
	// }
	// conIndex3 = keyIndex3 + k2.length();
	// }
	//
	// String k4 = keywordlist.get(4).toUpperCase();
	// //
	// int m4 = k4.length();
	// int n4 = content.length();
	// int conIndex4 = 0; // 開始找的位置
	// int keyIndex4 = 0;// 出現位置
	//
	// for (key4 = 0; conIndex4 <= (content.length() - 1); key4++) {
	// keyIndex4 = content.indexOf(k4, conIndex4);
	// if (keyIndex4 == -1) {
	// break;
	// }
	// conIndex4 = keyIndex4 + k4.length();
	// }
	// String k5 = keywordlist.get(5).toUpperCase();
	// //
	// int m5 = k5.length();
	// int n5 = content.length();
	// int conIndex5 = 0; // 開始找的位置
	// int keyIndex5 = 0;// 出現位置
	//
	// for (key5 = 0; conIndex5 <= (content.length() - 1); key5++) {
	// keyIndex5 = content.indexOf(k5, conIndex5);
	// if (keyIndex5 == -1) {
	// break;
	// }
	// conIndex5 = keyIndex5 + k5.length();
	// }
	//
	// String k6 = keywordlist.get(6).toUpperCase();
	// //
	// int m6 = k6.length();
	// int n6 = content.length();
	// int conIndex6 = 0; // 開始找的位置
	// int keyIndex6 = 0;// 出現位置
	//
	// for (key6 = 0; conIndex6 <= (content.length() - 1); key6++) {
	// keyIndex6 = content.indexOf(k6, conIndex6);
	// if (keyIndex6 == -1) {
	// break;
	// }
	// conIndex6 = keyIndex6 + k6.length();
	// }
	//
	// String k7 = keywordlist.get(7).toUpperCase();
	// //
	// int m7 = k7.length();
	// int n7 = content.length();
	// int conIndex7 = 0; // 開始找的位置
	// int keyIndex7 = 0;// 出現位置
	//
	// for (key7 = 0; conIndex7 <= (content.length() - 1); key7++) {
	// keyIndex7 = content.indexOf(k7, conIndex7);
	// if (keyIndex7 == -1) {
	// break;
	// }
	// conIndex7 = keyIndex7 + k7.length();
	// }
	// String k8 = keywordlist.get(8).toUpperCase();
	// //
	// int m8 = k8.length();
	// int n8 = content.length();
	// int conIndex8 = 0; // 開始找的位置
	// int keyIndex8 = 0;// 出現位置
	//
	// for (key8 = 0; conIndex8 <= (content.length() - 1); key8++) {
	// keyIndex8 = content.indexOf(k8, conIndex8);
	// if (keyIndex8 == -1) {
	// break;
	// }
	// conIndex8 = keyIndex8 + k8.length();
	// }
	// String k9 = keywordlist.get(9).toUpperCase();
	// //
	// int m9 = k9.length();
	// int n9 = content.length();
	// int conIndex9 = 0; // 開始找的位置
	// int keyIndex9 = 0;// 出現位置
	//
	// for (key9 = 0; conIndex9 <= (content.length() - 1); key9++) {
	// keyIndex9 = content.indexOf(k9, conIndex9);
	// if (keyIndex9 == -1) {
	// break;
	// }
	// conIndex9 = keyIndex9 + k9.length();
	// }
	//
	// String kE = keyword.toUpperCase(); // 原本寫urlSt.toUpperCase 但應該是keyword才對?
	// //
	// int mE = kE.length();
	// int nE = content.length();
	// int conIndexE = 0; // 開始找的位置
	// int keyIndexE = 0;// 出現位置
	//
	// for (keyE = 0; conIndexE <= (content.length() - 1); keyE++) {
	// keyIndexE = content.indexOf(kE, conIndexE);
	// if (keyIndexE == -1) {
	// break;
	// }
	// conIndexE = keyIndexE + kE.length();
	// }
	//
	// int score2 = (key0 + key1 + key2 + key3 + key4) * 2 + (key5 + key6 + key7 +
	// key8 + key9) + keyE * 5;
	// if (score2 > score1) {
	// score2 = score1;
	// }
	//
	// String score2Str = Integer.toString(score2);
	// scoreList2.add(score2Str);
	//
	// System.out.println(score1Str);
	//
	// }
	// }
	// }
	public ArrayList<Integer> countKeywords(String con, String searchKeyword) throws IOException {
		// ArrayList<String> runList = contentList;
		// runList = fetchContent();
		// System.out.println(runList.get(0));
		// keyword設的不太準確 應該要加 女團/男團/出道/粉絲
		ArrayList<String> keywordlist = new ArrayList(
				Arrays.asList(searchKeyword,"twice", "綜藝","男團", "女團", "擔當", "團寵", "出演", "MUSIC BANK", "出道", "忙內","舞蹈","偶像", "音樂", "KPOP","韓國", "韓團", "歌手", "一位", "Mnet", "回歸", "應援", "隊長", "SM娛樂", "JYP", "IDOL", "粉絲","專輯"));
		ArrayList<Integer> countList = new ArrayList<>();
		// for (int i = 0; i < runList.size(); i++) {
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
			// TO DO:indexOf(keyword)
			// int count = 0;
			// while (content.indexOf(keyword) != -1) {
			// int cur = content.indexOf(keyword);
			// content = content.substring(content.indexOf(keyword) + keyword.length(),
			// content.length());
			// count++;
			// }

			countList.add(retVal);
		}
		System.out.println(countList);
		return countList;

		// }

		// brute-force algorithm :time complexcity: O(mn) -> while exhits the m time of
		// P.length()
		// n shows that the occurence occures n time (n.length = n)
		// return countList ;
	}

	// public ArrayList<Integer> addScoreList() {
	// ArrayList<Integer> totalScoreList = new ArrayList<Integer>();
	// for (int i = 0; i < scoreList1.size() && i < scoreList2.size(); i++) {
	// int s1 = Integer.parseInt(scoreList1.get(i));
	// int s2 = Integer.parseInt(scoreList2.get(i));
	// int total = s1 + s2;
	// // String totalStr = Integer.toString(total);
	// totalScoreList.add(total);
	// }
	// //System.out.println(score);
	// return totalScoreList;
	// }

}
