import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;

public class HTMLMatcher {

	// ���ݪ�����url
	private static List<String> allwaiturl = new ArrayList<>();
	// �����L��url
	private static Set<String> alloverurl = new HashSet<>();
	// �O���Ҧ�url���`�׶i�檦���P�_
	private static Map<String, Integer> allurldepth = new HashMap<>();
	// �����o�`�� �n���n��1?
	private static int maxdepth = 1;

	public static List<String> urlList = new ArrayList<>();

//		public static void main(String args[]) {
//			// �T�w�����������a�}�A���B�������������W���ϮѤ����i�h������
//			// ���}�� http://book.dangdang.com/
//			// String strurl="http://search.dangdang.com/?key=%BB%FA%D0%B5%B1%ED&act=input";
//			String strurl = "http://book.dangdang.com/";
	//
//			workurl(strurl, 1);
//		}
	public String urlStr;

	public HTMLMatcher(String urlStr) {
		this.urlStr = urlStr;
	}

	public static List<String> workurl(String strurl, int depth) {
		// �P�_��eurl�O�_�����L
		if (!alloverurl.contains(strurl) && depth <= maxdepth) {
			// �Ы�url�����֤߹�H
			try {
				URL url = new URL(strurl);
				// �q�Lurl�ЫػP�������s��
				URLConnection conn = url.openConnection();
				// �q�L�챵���o������^���ƾ�
				InputStream is = conn.getInputStream();

				// ****System.out.println(conn.getContentEncoding());
				// �@�����Ū�������ƾڡA�öi�椺�e���R
				// �]����BufferedReader�MInputStreamReader��r�`�y��Ƭ��r�Ŭy���w�Ĭy
				// �i���ഫ�ɡA�ݭn�B�z�s�X�榡���D
				BufferedReader br = new BufferedReader(new InputStreamReader(is, "GB2312"));

				// ����Ū���å��L
				String line = null;
				// ���h��F�����ǰt�W�h�����Ӻ������챵
				Pattern p = Pattern.compile("<a .*href=.+</a>");
				// �Ыؤ@�ӿ�X�y�A�Ω�O�s����,���ɦW������ɶ��A�H������
				//PrintWriter pw = new PrintWriter(new File(System.currentTimeMillis() + ".txt"));

				while ((line = br.readLine()) != null) {
					// System.out.println(line);
					// �s�g���h�A�ǰt�W�챵�a�}
					//pw.println(line);
					Matcher m = p.matcher(line);
					while (m.find()) {
						String href = m.group();
						// ���W�챵�a�}�úI���r�Ŧ�
						// ���L�޸�
						href = href.substring(href.indexOf("href="));
						if (href.charAt(5) == '\"') {
							href = href.substring(6);
						} else {
							href = href.substring(5);
						}
						// �I����޸��Ϊ̪Ů�Ϊ̨�">"����
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
							// ��X�Ӻ����s�b���챵
							// System.out.println(href);
							// �Nurl�a�}��춤�C��
							allwaiturl.add(href);
							allurldepth.put(href, depth + 1);

							urlList.add(href);
							System.out.println(href);
						}

					}

				}
				//pw.close();
				br.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				// continue;
			}
			// �N��eurl�k�C��alloverurl��
			alloverurl.add(strurl);
			// System.out.println(strurl + "�������������A�w�����ƶq�G" + alloverurl.size() + "�A�Ѿl�����ƶq�G"
			// + allwaiturl.size());
		}
		// �λ��k����k�~�򪦨���L�챵
//			String nexturl = allwaiturl.get(0);
//			allwaiturl.remove(0);
//			workurl(nexturl, allurldepth.get(nexturl));
//			
		return urlList;
	}

}
