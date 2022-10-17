package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//트리 순회
public class Main_1991 {

	static class Node {
		String name;
		Node left = null, right = null;
		
		Node(String name){
			this.name = name;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Node> nodeList = new ArrayList<>();
		Node root = null;
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String a = st.nextToken();
			String b = st.nextToken();
			String c = st.nextToken();

			boolean contains = false;
			Node curNode = null;
			for(Node n : nodeList) {
				if(n.name.equals(a)) {
					contains = true;
					curNode = n;
					break;
				}
			}
			
			if(!contains) {
				curNode = new Node(a);
				nodeList.add(curNode);
				if(a.equals("A"))
					root = curNode;
			}
			
			if(!b.equals(".")) {
				boolean flag = false;
				Node cur = null;
				for(Node n : nodeList) {
					if(n.name.equals(b)) {
						flag = true;
						cur = n;
						break;
					}
				}
				if(!flag) {
					Node newNode = new Node(b);
					curNode.left = newNode;
					nodeList.add(newNode);
				}
				else {
					curNode.left = cur;
				}
			}
			
			if(!c.equals(".")) {
				boolean flag = false;
				Node cur = null;
				for(Node n : nodeList) {
					if(n.name.equals(c)) {
						flag = true;
						cur = n;
						break;
					}
				}
				if(!flag) {
					Node newNode = new Node(c);
					curNode.right = newNode;
					nodeList.add(newNode);
				}
				else {
					curNode.right = cur;
				}
			}
		}
		
		preorder(root);
		System.out.println();
		inorder(root);
		System.out.println();
		postorder(root);
	}

	private static void preorder(Node node) {
	
		System.out.print(node.name);
		if(node.left != null)
			preorder(node.left);
		if(node.right != null)
			preorder(node.right);
	}
	
	private static void inorder(Node node) {
		
		if(node.left != null)
			inorder(node.left);	
		System.out.print(node.name);
		if(node.right != null)
			inorder(node.right);
	}

	private static void postorder(Node node) {
	
		if(node.left != null)
			postorder(node.left);
		if(node.right != null)
			postorder(node.right);
		System.out.print(node.name);
	}
}
