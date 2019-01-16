import java.io.IOException;
import java.util.ArrayList;

public class WebPage {
	public WordCounter counter;
	public int score1;
	public int score2;
	public String url;

	public WebPage(String url) {
		this.url = url;
	}

	public int setScore(ArrayList<Integer> countList) throws IOException {
		this.score1 = 0;
		this.score2 = 0;
		int type = 0;
		int a = 0;
		int b = 0;

		for (int i = 1; i < countList.size(); i++) {
			if (countList.get(i) > 0)
				type++;
		}
		score1 = type * 300;
		for (int i = 1; i <= 10; i++) {
			a += countList.get(i);// 權重高關鍵字出現總次數
		}
		for (int i = 11; i <= 27; i++) {
			b += countList.get(i);// 權重低關鍵字出現總次數
		}

		
		
		if (score1 == 0) {
			score2 = 0;
		}
		else if (score1 > 0 && score1 < score2 ) {
			score2 = score1;
		}
		else {
			score2 = countList.get(0) * 1 + a * 10 + b*5;
		}
		
		//if (score1 < score2)
			//score2 = score1;
		System.out.println(score1);
		System.out.println(score2);

		int totalScore = score1 + score2;
		System.out.println(totalScore);
		return totalScore;
	}

}
