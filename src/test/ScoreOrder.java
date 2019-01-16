package test;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ScoreOrder {
	private ArrayList<SortList> SList;

	public ScoreOrder(ArrayList<SortList> SList) {
		this.SList = SList;
	}
	public ArrayList<SortList> getSList() {
		return SList;
	}
	public void sort() {
		this.SList = doQuickSort(this.SList);
	}

	private ArrayList<SortList> doQuickSort(ArrayList<SortList> li) {
		if (li.size() < 2)
			return li;

		ArrayList<SortList> result = new ArrayList<>();

		int pivotIndex = li.size() / 2;
		SortList pivotKeyword = li.get(pivotIndex);

		ArrayList<SortList> lessList = new ArrayList<>();
		ArrayList<SortList> equalList = new ArrayList<>();
		ArrayList<SortList> greatList = new ArrayList<>();

		for (int i = 0; i < li.size(); i++) {
			SortList k = li.get(i);

			if (k.score > pivotKeyword.score) {
				greatList.add(k);
			} else if (k.score < pivotKeyword.score) {
				lessList.add(k);
			} else {
				equalList.add(k);
			}
		}

		result.addAll(doQuickSort(greatList));
		result.addAll(equalList);
		result.addAll(doQuickSort(lessList));

		return result;
	}

	public void print() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < this.SList.size(); i++) {
			SortList k = this.SList.get(i);
			if (i > 0)
				sb.append(" ");
			sb.append(k.toString());
		}

		System.out.println(sb.toString());
	}

}
