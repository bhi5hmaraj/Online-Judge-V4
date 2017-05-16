import java.util.*;
import java.io.*;
class GOGL16T5
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	

	static class AVLTree {
		static class Node {
			int key;
			int height;
			int cnt;
			int size;
			Node left, right, parent;

			Node(int key) {
				this.key = key;
				height = 1;
				cnt = 1;
				size = 1;
				left = right = parent = null;
			}

			@Override
			public String toString() {
				return "[key = " + key + "cnt = "+cnt+" sz = "+size+ "]" + " P = " + (parent != null ? parent.key : "null")
						+ " L = " + (left != null ? left.key : "null") + " R = " + (right != null ? right.key : "null");
			}
		}

		public Node root;

		public AVLTree() {
			root = null;
		}

		public int height(Node n) {
			return n == null ? 0 : n.height;
		}

		public void add(int key) {
			root = add(key, root, null);
			Node N = search(root, key);
			root = rebalance(N);
		}

		public boolean find(long key) {
			return search(root, key) != null;
		}

		public int size(Node n){
			return n == null ? 0 : n.size;
		}
		public void adjustHeight(Node N) {
			N.height = 1 + Math.max(height(N.left), height(N.right));
			if (N.left != null)
				N.left.parent = N;
			if (N.right != null)
				N.right.parent = N;

			adjustSize(N);
		}
		public void adjustSize(Node N){
			N.size = N.cnt + size(N.left) + size(N.right);
		}
		public Node rotateRight(Node N) {
			Node oldPar = N.parent;
			Node newN = N.left;
			N.left = newN.right;
			if (newN != null && newN.right != null && newN.right.parent != null)
				newN.right.parent = N;
			adjustHeight(N);
			newN.parent = oldPar;
			newN.right = N;
			adjustHeight(newN);
			N.parent = newN;
			if (oldPar != null) {
				if (oldPar.left == N)
					oldPar.left = newN;
				else
					oldPar.right = newN;

				adjustHeight(oldPar);
			}
			return newN;
		}

		public Node rotateLeft(Node N) {
			Node oldPar = N.parent;
			Node newN = N.right;
			N.right = newN.left;
			if (newN != null && newN.left != null && newN.left.parent != null)
				newN.left.parent = N;
			adjustHeight(N);
			newN.parent = oldPar;
			newN.left = N;
			N.parent = newN;
			adjustHeight(newN);
			if (oldPar != null) {
				if (oldPar.left == N)
					oldPar.left = newN;
				else
					oldPar.right = newN;

				adjustHeight(oldPar);
			}
			return newN;
		}

		public Node search(Node root, long key) {
			if (root != null)
				return ((root.key == key) ? root : (key < root.key ? search(root.left, key) : search(root.right, key)));
			else
				return null;
		}

		public Node smallest(Node root) {
			return (root.left == null) ? root : smallest(root.left);
		}

		public Node largest(Node root) {
			return (root.right == null) ? root : largest(root.right);
		}
		
		public int countLess(Node root , int key)
		{
			if(root != null){
				if(root.key == key){
					return size(root.left);
				}
				else if(root.key > key){
					return countLess(root.left, key);
				}
				else
					return root.cnt + size(root.left) + countLess(root.right, key);
			}
			else
				return 0;
		}
		public int countLess(int key){
			return countLess(root, key);	    
		}

		public Node rebalance(Node N) {
			Node par = N.parent;
			if (height(N.left) - height(N.right) >= 2) {
				Node M = N.left;
				if (height(M.right) > height(M.left))
					M = rotateLeft(M);
				N = rotateRight(N);
			}
			if (height(N.right) - height(N.left) >= 2) {
				Node M = N.right;
				if (height(M.left) > height(M.right))
					M = rotateRight(M);

				N = rotateLeft(N);
			}

			if (par != null)
				return rebalance(par);
			else
				return N;

		}

		public Node add(int key, Node root, Node parent) {
			if (root == null) {
				Node newNode = new Node(key);
				newNode.parent = parent;
				return newNode;
			} else {
				if (key < root.key)
					root.left = add(key, root.left, root);
				else if (key > root.key)
					root.right = add(key, root.right, root);
				else
					root.cnt++;

				adjustHeight(root);
				return root;
			}
		}

		public void print(Node root) {
			if (root != null) {
				print(root.left);
				System.out.println(root);
				print(root.right);
			}
		}
		private StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb, Node root) {

			if (root == null) {
				sb.append("Tree Empty\n");
				return sb;
			}
			if (root.right != null) {
				toString(new StringBuilder().append(prefix).append(isTail ? "|   " : "    "), false, sb, root.right);
			}
			sb.append(prefix).append(isTail ? "|-- " : "|-- ").append(root.key).append("\n");
			if (root.left != null) {
				toString(new StringBuilder().append(prefix).append(isTail ? "    " : "|   "), true, sb, root.left);
			}
			return sb;
		}

		@Override
		public String toString() {
			return this.toString(new StringBuilder(), true, new StringBuilder(), root).toString();
		}
	}

	static class EndPoint {
		int idx , key;
		boolean isLeft;
		EndPoint(int idx , int key , boolean isLeft) {
			this.idx = idx;
			this.key = key;
			this.isLeft = isLeft;
		}
	}
	
	@SuppressWarnings("unchecked")
	private static void solve(FastScanner s1, PrintWriter out){
		
		int N = s1.nextInt();
		int Q = s1.nextInt();
		AVLTree tree = new AVLTree();
		int arr[] = s1.nextIntArrayOneBased(N);
		ArrayList<EndPoint>[] offline = new ArrayList[N + 1];
		for(int i=0;i<=N;i++)
			offline[i] = new ArrayList<>();
		
		for(int i=0;i < Q;i++) {
			int L = s1.nextInt();
			int R = s1.nextInt();
			int key = s1.nextInt();
			offline[L - 1].add(new EndPoint(i, key, true));
			offline[R].add(new EndPoint(i, key, false));
		}
		
		int ans[] = new int[Q];
		
		for(int i=0;i<=N;i++) {
			if(i > 0) tree.add(arr[i]);
			for(EndPoint e : offline[i]) {
				if(e.isLeft)
					ans[e.idx] = tree.countLess(e.key);
				else
					ans[e.idx] = tree.countLess(e.key) - ans[e.idx];
			}
		}
		
		for(int a : ans)
			out.println(a);
		
	}
	
	
	
	/************************ SOLUTION ENDS HERE ************************/
	
	
	
	
	
	/************************ TEMPLATE STARTS HERE *********************/
	
	public static void main(String []args) throws IOException {
		FastScanner in  = new FastScanner(System.in);
		PrintWriter out = 
				new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false); 
		solve(in, out);
		in.close();
		out.close();
	}    
	
	static class FastScanner{
		BufferedReader reader;
		StringTokenizer st;
		FastScanner(InputStream stream){reader=new BufferedReader(new InputStreamReader(stream));st=null;}	
		String next()
		{while(st == null || !st.hasMoreTokens()){try{String line = reader.readLine();if(line == null){return null;}		    
		st = new StringTokenizer(line);}catch (Exception e){throw new RuntimeException();}}return st.nextToken();}
		String nextLine()  {String s=null;try{s=reader.readLine();}catch(IOException e){e.printStackTrace();}return s;}	    	  	
		int    nextInt()   {return Integer.parseInt(next());}
		long   nextLong()  {return Long.parseLong(next());}		
		double nextDouble(){return Double.parseDouble(next());}
		char   nextChar()  {return next().charAt(0);}
		int[]  nextIntArray(int n)         {int[] arr= new int[n];   int i=0;while(i<n){arr[i++]=nextInt();}  return arr;}
		long[] nextLongArray(int n)        {long[]arr= new long[n];  int i=0;while(i<n){arr[i++]=nextLong();} return arr;}	
		int[]  nextIntArrayOneBased(int n) {int[] arr= new int[n+1]; int i=1;while(i<=n){arr[i++]=nextInt();} return arr;}	    	
		long[] nextLongArrayOneBased(int n){long[]arr= new long[n+1];int i=1;while(i<=n){arr[i++]=nextLong();}return arr;}	    	
		void   close(){try{reader.close();}catch(IOException e){e.printStackTrace();}}				
	}
	
	/************************ TEMPLATE ENDS HERE ************************/
}