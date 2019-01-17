import java.io.IOException;
import java.util.ArrayList;

public class WebNode {
	public WebNode parent;
	public ArrayList<WebNode> children;
	public WebPage webPage;
	public int nodeScore;

	public WebNode(WebPage webPage) {
		this.webPage = webPage;
		this.children = new ArrayList<WebNode>();
	}

	public int setNodeScore(ArrayList<Integer> countList) throws IOException {
		this.nodeScore = webPage.setScore(countList);

		for (WebNode child : children) {
			this.nodeScore += child.nodeScore;
		}
		return nodeScore;
	}

	public void addChild(WebNode child) {
		this.children.add(child);
		child.parent = this;
	}

	public int getDepth() {
		int retVal = 1;
		WebNode currNode = this;

		while (currNode.parent != null) {
			retVal++;
			currNode = currNode.parent;
		}
		return retVal;
	}
}