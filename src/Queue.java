import java.io.*;
import java.util.*;

public class Queue {

 
    
    /************************ SOLUTION STARTS HERE ************************/
    


    
    private static void solve() {
        
    	int n = nextInt();
    	int arr[] = nextIntArray(n);
    	
    	AVLTree AVL = new AVLTree();
    	for(int i = n - 1; i >= 0; i--) {
    		AVL.add(arr[i], i);
    		arr[i] = Math.max(-1, AVL.lastSmall(arr[i]) - i - 1);
    	}
    	
    	Arrays.stream(arr).forEach(x -> print(x + " "));
    	
    	
    }
    
    
    
    /************************ SOLUTION ENDS HERE ************************/
    
    

	static class AVLTree {
		static class Node {
			long key;
			int height;
			int cnt;
			int size;
			int max;
			int lastIndex;
			
			Node left, right, parent;
			
			Node(long key, int index) {
				this.key = key;
				height = 1;
				cnt = 1;
				size = 1;
				lastIndex = index;
				max = index;
				left = right = parent = null;
			}

			@Override
			public String toString() {
				return "[key = " + key + " max = "+max+" sz = "+size+ "]" + " P = " + (parent != null ? parent.key : "null")
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

		public void add(long key, int index) {
			root = add(key, index, root, null);
			Node N = search(root, key);
			root = rebalance(N);
		}

		public boolean find(long key) {
			return search(root, key) != null;
		}
		
		public int size(Node n){
			return n == null ? 0 : n.size;
		}
		
		private int max(Node n) {
			return n == null ? 0 : n.max;
		}
		
		public void adjustHeight(Node N) {
			N.height = 1 + Math.max(height(N.left), height(N.right));
			if (N.left != null)
				N.left.parent = N;
			if (N.right != null)
				N.right.parent = N;
			
			adjustSize(N);
			adjustMax(N);
		}

		private void adjustMax(Node n) {
			n.max = Math.max(n.lastIndex, Math.max(max(n.left), max(n.right)));
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

		
		public int prefixMax(Node root , long  key) {
			
			if(root == null)
				return 0;
			
			if(key < root.key)
				return prefixMax(root.left, key);
			else if(key == root.key)
				return max(root.left);
			else
				return Math.max(root.lastIndex, Math.max(max(root.left), prefixMax(root.right, key)));
						
			
		}
		
		public int lastSmall(long key){
			return prefixMax(root, key);	    
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

		public Node add(long key, int index, Node root, Node parent) {
			if (root == null) {
				Node newNode = new Node(key, index);
				newNode.parent = parent;
				return newNode;
			} else {
				if (key < root.key)
					root.left = add(key, index, root.left, root);
				else if (key > root.key)
					root.right = add(key, index, root.right, root);
				else {
					root.lastIndex = Math.max(root.lastIndex, index);
					root.cnt++;
				}
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
			sb.append(prefix).append(isTail ? "|-- " : "|-- ").append(root).append("\n");
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
    
    
    /************************ TEMPLATE STARTS HERE **********************/
    
    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
        st     = null;
        solve();
        reader.close();
        writer.close();
    }
    
    static BufferedReader reader;
    static PrintWriter    writer;
    static StringTokenizer st;
    
    static String next()
    {while(st == null || !st.hasMoreTokens()){try{String line = reader.readLine();if(line == null){return null;}            
    st = new StringTokenizer(line);}catch (Exception e){throw new RuntimeException();}}return st.nextToken();}
    static String nextLine()  {String s=null;try{s=reader.readLine();}catch(IOException e){e.printStackTrace();}return s;}             
    static int    nextInt()   {return Integer.parseInt(next());}
    static long   nextLong()  {return Long.parseLong(next());}     
    static double nextDouble(){return Double.parseDouble(next());}
    static char   nextChar()  {return next().charAt(0);}
    static int[]  nextIntArray(int n)         {int[] a= new int[n];   int i=0;while(i<n){a[i++]=nextInt();}  return a;}
    static long[] nextLongArray(int n)        {long[]a= new long[n];  int i=0;while(i<n){a[i++]=nextLong();} return a;}    
    static int[]  nextIntArrayOneBased(int n) {int[] a= new int[n+1]; int i=1;while(i<=n){a[i++]=nextInt();} return a;}            
    static long[] nextLongArrayOneBased(int n){long[]a= new long[n+1];int i=1;while(i<=n){a[i++]=nextLong();}return a;}            
    static void   print(Object o)  { writer.print(o);  }
    static void   println(Object o){ writer.println(o);}
    
    /************************ TEMPLATE ENDS HERE ************************/
    
}