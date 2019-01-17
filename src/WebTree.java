import java.io.IOException;
import java.util.ArrayList;

public class WebTree {

	public WebNode root;

	public WebTree(WebPage rootPage) {
		this.root = new WebNode(rootPage);
	}

	public int setPostOrderScore(ArrayList<Integer> countList) throws IOException {
		setPostOrderScore(root, countList);
		return root.setNodeScore(countList);
	}

	private void setPostOrderScore(WebNode startNode, ArrayList<Integer> countList) throws IOException {
		for (WebNode child : startNode.children) {
			setPostOrderScore(child, countList);
		}
		// System.out.println("here");
		startNode.setNodeScore(countList);
	}
}
