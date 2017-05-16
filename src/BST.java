import java.util.Scanner;
public class BST {

	static class Node {
		int key;
		Node left , right;
		Node(int k){
			key = k;
		}
	}
	
	Node root;
	void insert(int key){
		root = insert(key, root);
	}
	Node insert(int key , Node root){
		if(root == null)
			return new Node(key);
		else{
			if(key < root.key)
				root.left = insert(key, root.left);
			else
				root.right = insert(key, root.right);
			
			return root;
		}
	}
	
	boolean search(int key , Node n){
		if(n == null)
			return false;
		else
			return n.key == key ? true : (key > n.key) ? search(key, n.right) : search(key, n.left);
	}
	
	void print(Node n){
		if(n != null){
			print(n.left);
			System.out.println(n.key);
			print(n.right);
		}
		
	}

	public static void main(String[] args) {
		BST tree = new BST();
		Scanner s1 = new Scanner(System.in);
		int t = s1.nextInt();
		while(t-->0)
			tree.insert(s1.nextInt());
		
		tree.print(tree.root);
	}
	
}
