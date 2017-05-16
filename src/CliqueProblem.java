import java.util.*;
import java.io.*;

public class CliqueProblem
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

		public boolean find(int key) {
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

		public Node search(Node root, int key) {
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

		public void del(int key) {
			Node toBeRemoved = search(root, key);
			if (toBeRemoved != null) {
				if (toBeRemoved.cnt == 1)
					delete(toBeRemoved);
				else {
					toBeRemoved.cnt--;
					while (toBeRemoved != null) {
						adjustHeight(toBeRemoved);
						toBeRemoved = toBeRemoved.parent;
					}
				}
			}
		}

		public int countGreaterThanOrEqual(Node root , int  key)
		{
			if(root != null){
				if(root.key < key)
					return countGreaterThanOrEqual(root.right, key);
				else
					return root.cnt + size(root.right) + countGreaterThanOrEqual(root.left, key);
			}
			else
				return 0;
		}
		public int countGreaterThanOrEqual(int key){
			return countGreaterThanOrEqual(root, key);	    
		}
		public void delete(Node N) {

			if (N.left == null && N.right == null) {
				if (N == root)
					root = null;
				else {
					Node par = N.parent;
					if (par.left == N)
						par.left = null;
					else
						par.right = null;

					Node orig_par = par;
					while (par != null) {
						adjustHeight(par);
						par = par.parent;
					}
					root = rebalance(orig_par);
				}
			} else if (N.right == null && N.left != null) {
				Node par = N.parent;
				if (par != null) {
					if (par.left == N)
						par.left = N.left;
					else
						par.right = N.left;

					N.left.parent = par;
					Node orig_par = par;
					while (par != null) {
						adjustHeight(par);
						par = par.parent;
					}

					root = rebalance(orig_par);

				} else {
					root = root.left;
					root.parent = null;
					adjustHeight(root);
				}
			} else if (N.left == null && N.right != null) {
				Node par = N.parent;
				if (par != null) {
					if (par.left == N)
						par.left = N.right;
					else
						par.right = N.right;

					N.right.parent = par;

					Node orig_par = par;
					while (par != null) {
						adjustHeight(par);
						par = par.parent;
					}
					root = rebalance(orig_par);
				} else {
					root = root.right;
					root.parent = null;
					adjustHeight(root);
				}
			} else {
				Node small = smallest(N.right);
				N.key = small.key;
				N.cnt = small.cnt;  // !!! VERY VERY IMPORTANT
				delete(small);
			}
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

	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int N = s1.nextInt();
		int arr[][] = new int[N][];
		for(int i=0;i<N;i++)
			arr[i] = s1.nextIntArray(2);
		
		int maxSize = 0;
		Arrays.sort(arr , (p1 , p2) -> Integer.compare(p1[0], p2[0]));
		AVLTree tree = new AVLTree();
		
		for(int i=N-1;i>=0;i--) {
			maxSize = Math.max(maxSize,tree.countGreaterThanOrEqual(arr[i][0] + arr[i][1]) + 1);
			tree.add(arr[i][0] - arr[i][1]);
		}
		
		out.println(maxSize);
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
		int[]  nextIntArray(int n)         {int[] a= new int[n];   int i=0;while(i<n){a[i++]=nextInt();}  return a;}
		long[] nextLongArray(int n)        {long[]a= new long[n];  int i=0;while(i<n){a[i++]=nextLong();} return a;}	
		int[]  nextIntArrayOneBased(int n) {int[] a= new int[n+1]; int i=1;while(i<=n){a[i++]=nextInt();} return a;}	    	
		long[] nextLongArrayOneBased(int n){long[]a= new long[n+1];int i=1;while(i<=n){a[i++]=nextLong();}return a;}	    	
		void   close(){try{reader.close();}catch(IOException e){e.printStackTrace();}}				
	}
	
	/************************ TEMPLATE ENDS HERE ************************/
}