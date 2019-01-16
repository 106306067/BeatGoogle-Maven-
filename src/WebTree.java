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

	 public void printTree() {
	 printTree(root);
	 }
	
	 private void printTree(WebNode startNode) {
//	 System.out.print(createBlank(startNode.getDepth()) + "(" +
//	 startNode.webPage. +" "+ startNode.nodeScore);
	 if(startNode.children.size() == 0)
	 System.out.println(")");
	 else {
	 System.out.println("");
	 for(int i = 0; i < startNode.children.size(); i++){
	 WebNode node = startNode.children.get(i);
	 printTree(node);
	 }
	 System.out.println(createBlank(startNode.getDepth()) + ")");
	 }
	 }
	 private String createBlank(int n) {
	 StringBuilder sb = new StringBuilder("");
	 for(int i = 0 ; i <n; i++) {
	 sb.append(" ");
	 }
	 return sb.toString();
	 }
}
