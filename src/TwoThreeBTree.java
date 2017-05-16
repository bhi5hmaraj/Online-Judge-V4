import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class TwoThreeBTree {

	static class Node {
		ArrayList<Integer> data;
		Node adj[];
		int size , level;
		Node(int key){
			data = new ArrayList<>();
			data.add(key);
			adj = new Node[4];
			size = 1;
			level = 1;
		}
		void update(){
			Collections.sort(data);
			size = 1;
			int max = 0;
			for(int i=0;i<3;i++)
				if(adj[i] != null) {
					size += adj[i].size;
					max = Math.max(max,adj[i].level);
				}
			level = 1 + max;
		}
		@Override
		public String toString() {
			return data.toString() /*+ "sz " + size + " lev " + level*/;
		}
	}

	Node root;
	private Node[] splitNode(Node root){
		root.data.remove(1);
		Node newNode = new Node(root.data.get(1));
		root.data.remove(1);
		newNode.adj[0] = root.adj[2];
		newNode.adj[1] = root.adj[3];
		root.adj[2] = root.adj[3] = null;
		root.update();
		newNode.update();
		return new Node[]{root , newNode};
	}

	void print(){
		ArrayDeque<Node> queue = new ArrayDeque<>();
		queue.add(root);
		int prev = 0;
		while(!queue.isEmpty()) {
			Node curr = queue.remove();
			if(curr.level != prev)
				System.out.println();
			
			int space = curr.adj[0] != null ? curr.adj[0].size : 0;
			for(int i=0;i<space*3;i++)
				System.out.print(" ");
			
			prev = curr.level;
			System.out.print(curr);
			for(int i=0;i<4;i++)
				if(curr.adj[i] != null) 
					queue.add(curr.adj[i]);
		}

	}
	void insert(int key){
		insert(root, null, key);
	}

	private void insert(Node root , Node parent , int key){
		if(root == null)
			this.root =  new Node(key);
		else {
			if(root.adj[0] != null || root.adj[1] != null || root.adj[2]!= null || root.adj[3] != null){ // Internal node
				int pos = 0;
				for(;pos < root.data.size() && key > root.data.get(pos);pos++)
					;

				// System.out.println("key " + key + "position "+ pos);

				if(pos < root.data.size() && key == root.data.get(pos))
					return;
				else 
					insert(root.adj[pos], root, key);
			}
			else {
				root.data.add(key);
				root.update();
			}

			if(root.data.size() > 2){
				// System.out.println("overflow " + root);
				int median = root.data.get(1);
				Node split[] = splitNode(root);
				if(parent == null){
					parent = new Node(median);
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
					for(int i=3;i>pos+1;i--)
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

    private void pprint(String prefix, boolean isTail , Node n) {
        System.out.println(prefix + (isTail ? "└── " : "├── ") + n.data);
        for (int i = 3; i > 0; i--) {
        	if(n.adj[i] != null)
        		pprint(prefix + (isTail ? "    " : "│   "), false,n.adj[i]);
        }
        if (n.size > 1) {
            pprint(prefix + (isTail ?"    " : "│   "), true , n.adj[0]);
        }
    }
	public static void main(String[] args) {
		TwoThreeBTree tree = new TwoThreeBTree();
		Scanner s1 = new Scanner(System.in);
		int N = 10;
		ArrayList<Integer> random = new ArrayList<>();
		for(int i=1;i<=N;i++)
			random.add(i);
		Collections.shuffle(random);
		System.out.println("Insert " + random);
		/*
		int N = s1.nextInt();
		while(N-->0){
			tree.insert(s1.nextInt());
			tree.pprint();
		}
		System.out.println("Height " + tree.root.level);*/
		s1.close();
	}
}
