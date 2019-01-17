
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import test.SortList;

import test.ScoreOrder;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Main() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if (request.getParameter("keyword") == null) {
			String requestUri = request.getRequestURI();
			request.setAttribute("requestUri", requestUri);
			request.getRequestDispatcher("Search.jsp").forward(request, response);
			return;
		}

		String kK = request.getParameter("keyword") + "男團" + "女團";

		catchGoogle catchgoogle = new catchGoogle(kK);
		ArrayList<String> list = catchgoogle.queryUrl();
		ArrayList<String> Tlist = catchgoogle.queryTitle();
		// ArrayList<String> list = new ArrayList<String>();
		// list.add("https://www.google.com.tw/url?q=https://zh.wikipedia.org/zh-tw/Momo_(TWICE)&sa=U&ved=0ahUKEwjdjpLduOvfAhWTc3AKHVgCAuIQFgh6MBI&usg=AOvVaw3xsCWKbLZkOMLmFkkO8aD4");
		System.out.println("Url from Google:" + list);

		WordCounter wcounter = new WordCounter(list);

		ArrayList<String> conList = new ArrayList<String>(wcounter.fetchContent());// 網頁內容
		ArrayList<Integer> sList = new ArrayList<Integer>();
		for (int i = 0; i < conList.size(); i++) {

			WebPage rootPage = new WebPage(list.get(i));
			WebTree tree = new WebTree(rootPage);

			HTMLMatcher hMatcher = new HTMLMatcher(list.get(i));
			hMatcher.workurl(list.get(i), 1);

			for (int h = 0; h < hMatcher.urlList.size(); h++) {
				tree.root.addChild(new WebNode(new WebPage(hMatcher.urlList.get(h))));
			}

			sList.add(tree.setPostOrderScore(wcounter.countKeywords(conList.get(i), kK)));
		}

		ArrayList<SortList> sl = new ArrayList<SortList>();
		for (int s = 0; s < list.size() && s < sList.size(); s++) {
			String title = Tlist.get(s);
			String urlstr = list.get(s);
			int score = sList.get(s);
			sl.add(new SortList(title, urlstr, score));
		}

		System.out.println("result:");
		ScoreOrder so = new ScoreOrder(sl);
		so.sort();
		so.print();
		request.setAttribute("so", so);
		request.getRequestDispatcher("googleitem.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
