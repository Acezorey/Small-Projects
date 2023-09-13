/**
 * @author Ace Reyes
 * Created on August 1, 2023
 */

package wordCounter;

import java.util.ArrayList;

public class SearchTree {
	private class Node{
		String word;
		long iteration;
		private Node left;
		private Node right;
		
		private Node(String word, long iteration) {
			this.word = word;
			this.iteration = iteration;
			left = null;
			right = null;
		}
	}
	
	String result = "";
	Node root = null;
	
	public void analyze(ArrayList<String> list) {
		//1: Iterate through list and push values onto tree
		for(String word: list) {
			add(word);
		}
		
		//2: Count number of nodes in tree and tree max depth
		int numNodes = nodeCounter(root);
		int treeDepth = depthCounter(root);
		
		//3: Take tree and parse each value into a Node list
		Node[] frequencyList = toList(root);
		
		//4: Sort list
		Node[] sortedFrequencyList = sort(frequencyList);
		
		
		//5: Create string listing top 10 most used words
		result = stringMaker(numNodes, treeDepth, sortedFrequencyList);
	}
	
	public void add(String word) {
		root = add(word, root);
	}
	
	public Node add(String word, Node node) {
		if(node == null) {
			return new Node(word, 1);
		}
		
		if(word.compareTo(node.word) == 0) {
			node.iteration++;
		}
		else if(word.compareTo(node.word) > 0) {
			if(node.right != null) {
				add(word, node.right);
			}
			else {
				node.right = new Node(word, 1);
			}
		}
		else if(word.compareTo(node.word) < 0) {
			if(node.left != null) {
				add(word, node.left);
			}
			else {
				node.left = new Node(word, 1);
			}
		}
		
		return node;
	}
	
	public int nodeCounter(Node root) {
		if(root == null) {
			return 0;
		}
		else {
			return 1 + nodeCounter(root.right) + nodeCounter(root.left);
		}
	}
	
	public int depthCounter(Node root) {
		if(root == null) {
			return 0;
		}
		else {
			return 1 + Math.max(depthCounter(root.right), depthCounter(root.left));
		}
	}
	
	public Node[] toList(Node root) {
		ArrayList<Node> array = new ArrayList<>();
		array = buildList(array, root);
		Node[] resultingList = new Node[array.size()];
		
		for(int i = 0; i < array.size(); i++) {
			resultingList[i] = array.get(i);
		}
		
		return resultingList;
	}
	
	public ArrayList<Node> buildList(ArrayList<Node> array, Node root){
		if(root == null) {
			return array;
		}
		else {
			array.add(root);
			buildList(array, root.right);
			buildList(array, root.left);
		}
		
		return array;
	}
	
	public Node[] sort(Node[] list){ 
		for(int i = 1; i < list.length; i++) {
			Node compare = list[i];
			int past = i - 1;
			while((past > -1) && (compare.iteration > list[past].iteration)) {
				list[past + 1] = list[past];
				past--;
			}
			list[past + 1] = compare;
		}
		return list;
	}
	
	public String stringMaker(int numNodes, int treeDepth, Node[] list) {
		
		String tenMostCommon = "";
		int length = (list.length > 10)? 10: list.length;
		for(int i = 0; i < length; i++) {
			tenMostCommon = tenMostCommon + list[i].word + ", " + list[i].iteration + "\n";
		}
		
		return 
		"Number of nodes in tree: " + numNodes + "\n" +
		"Maximum depth of tree: " + treeDepth + " nodes\n" +
		"\nMost frequently used words:\n" + 
		tenMostCommon;
	}
	
	public String getResult() {
		return result;
	}
}
