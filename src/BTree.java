/*
		MIT License
		
		Copyright (c) [2016] [Bhishmaraj , Anirudh Badri , Hari Prasath]
		
		Permission is hereby granted, free of charge, to any person obtaining a copy
		of this software and associated documentation files (the "Software"), to deal
		in the Software without restriction, including without limitation the rights
		to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
		copies of the Software, and to permit persons to whom the Software is
		furnished to do so, subject to the following conditions:
		
		The above copyright notice and this permission notice shall be included in all
		copies or substantial portions of the Software.
		
		THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
		IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
		FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
		AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
		LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
		OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
		SOFTWARE.
		
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class BTree {

	static class Node {
		ArrayList<Integer> data;  // Stores the integers
		Node adj[]; 			  // Stores the references to the child nodes
		int size ,                // Size of the subtree rooted at the current node
			level , 			  // Level of the current (one based indexing) 
			capacity; 			  // Maximum capacity that a node can hold
		Node(int capacity){
			data = new ArrayList<>();
			adj = new Node[capacity + 2];
			size = 1;
			level = 1;
			this.capacity = capacity;
		}
		void update(){   		 // Updates the node by sorting it and also updates its size and level
			Collections.sort(data);
			size = 1;
			int max = 0;
			for(int i=0;i < capacity + 1;i++)
				if(adj[i] != null) {
					size += adj[i].size;
					max = Math.max(max,adj[i].level);
				}
			level = 1 + max;
		}
		@Override
		public String toString() {
			return data.toString();
		}
	}

	Node root;
	int capacity;
	public BTree() { // Default size is 2
		this(2);
	}
	public BTree(int capacity){
		root = null;
		this.capacity = capacity;
	}
	private Node[] splitNode(Node root){   // Splits the node into 2 equals halves
		int mid = (capacity + 1) / 2;
		root.data.remove(mid);

		Node newNode = new Node(capacity);
		int right_size = mid-(capacity % 2);
		for(int i=0;i<right_size;i++)
			newNode.data.add(root.data.remove(mid));

		for(int i=0;i<right_size+1;i++){
			newNode.adj[i] = root.adj[mid + i + 1];
			root.adj[mid + i + 1] = null;
		}
		root.update();
		newNode.update();
		return new Node[]{root , newNode};
	}
	public void insert(int key){          // Public insert method 
		insert(root, null, key);
	}
	private boolean search(Node root , int key){   // private search method 
		if(root == null)
			return false;
		else {
			int idx = Collections.binarySearch(root.data, key);
			return idx >= 0 ? true : search(root.adj[-(idx + 1)], key);
		}
	}
	public boolean search(int key){      // Public search method
		return search(root, key);
	}
	
	/*
	Private insert method , searches for a leaf node to insert and then inserts into it. 
	If the node is full then , the node is split and it's median is sent to the parent and this invariant is maintained recursively
	*/
	
	private void insert(Node root , Node parent , int key){    
		if(root == null) {
			this.root =  new Node(capacity);
			this.root.data.add(key);
		}
		else {
			int idx = Collections.binarySearch(root.data, key);
			if(idx >= 0)
				return;
			if(root.size > 1) // Internal node
				insert(root.adj[-(idx + 1)], root, key);
			else {
				root.data.add(key);
				root.update();
			}

			if(root.data.size() > capacity){
				// System.out.println("overflow " + root);
				int mid = (capacity + 1) / 2;
				int median = root.data.get(mid);
				Node split[] = splitNode(root);
				if(parent == null){
					parent = new Node(capacity);
					parent.data.add(median);
					parent.adj[0] = split[0];
					parent.adj[1] = split[1];
					this.root = parent;
					parent.update();
				}
				else {
					parent.data.add(median);
					parent.update();
					int pos = 0;
					for(;pos < parent.data.size() - 1 && parent.adj[pos] != root;pos++)
						;
					for(int i= capacity + 1;i>pos+1;i--)
						parent.adj[i] = parent.adj[i - 1];
					parent.adj[pos] = split[0];
					parent.adj[pos + 1] = split[1];
					parent.update();
				}
			}
		}

	}
	
	public void pprint() {
		pprint("", true , root);
	}

	private void pprint(String prefix, boolean isTail , Node n) {   // (pretty print) Performs a dfs and prints a tree for better debugging
		System.out.println(prefix + (isTail ? "└── " : "├── ") + n.data);
		for (int i = capacity + 1; i > 0; i--) {
			if(n.adj[i] != null)
				pprint(prefix + (isTail ? "    " : "│   "), false,n.adj[i]);
		}
		if (n.size > 1) {
			pprint(prefix + (isTail ?"    " : "│   "), true , n.adj[0]);
		}
	}
	public static void main(String[] args) {
		Scanner s1 = new Scanner(System.in);
		System.out.println("Enter the capacity of the node and the number of integers to be inserted and number of integers to be searched ");
		BTree tree = new BTree(s1.nextInt());   
		int N = s1.nextInt();
		int M = s1.nextInt();
		ArrayList<Integer> random = new ArrayList<>();
		for(int i=1;i<=N;i++)
			random.add(i);
		Collections.shuffle(random);
		long start = System.nanoTime();
		for(int a:random)
			tree.insert(a);
		long stop = System.nanoTime();

		System.out.println("Time taken = " + ((stop - start) / 1e9) + "s");
		/*while(N-->0){
			tree.insert(s1.nextInt());
			tree.pprint();
		}*/
		while(M-->0){
			System.out.println(tree.search(s1.nextInt()));
		}
		System.out.println("Height " + tree.root.level);
		
		s1.close();
	}
}
