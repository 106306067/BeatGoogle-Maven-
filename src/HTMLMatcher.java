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
	// �����o�`��
	private static int maxdepth = 1;

	public static List<String> urlList = new ArrayList<>();

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
				BufferedReader br = new BufferedReader(new InputStreamReader(is, "GB2312"));

				String line = null;
				// ���h��F�����ǰt�W�h�����Ӻ������챵
				Pattern p = Pattern.compile("<a .*href=.+</a>");

				while ((line = br.readLine()) != null) {
					// System.out.println(line);
					// �s�g���h�A�ǰt�W�챵�a�}

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
				br.close();
			} catch (Exception e) {
			}
			// �N��eurl�k�C��alloverurl��
			alloverurl.add(strurl);
			// System.out.println(strurl + "�������������A�w�����ƶq�G" + alloverurl.size() + "�A�Ѿl�����ƶq�G"

		}

		return urlList;
	}

}
