import java.util.*;
import java.io.*;
public class RankIt
{
    
    /************************ SOLUTION STARTS HERE ***********************/

    static class AVLTree {
	static class Node {
	    long key;
	    long sum;
	    int height;
	    int cnt;
	    int size;
	    Node left, right, parent;

	    Node(long key) {
		this.key = key;
		height = 1;
		sum = key;
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

	public void add(long key) {
	    root = add(key, root, null);
	    Node N = search(root, key);
	    root = rebalance(N);
	}

	public boolean find(long key) {
	    return search(root, key) != null;
	}

	public long sum(Node n) {
	    return n == null ? 0 : n.sum;
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
	    adjustSum(N); // Small hack , so that I need not call it explicitly!!
	    adjustSize(N);
	}

	public void adjustSum(Node N) {
	    N.sum = (N.key * (long)N.cnt) + sum(N.left) + sum(N.right);
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



	public int countGreater(Node root , long  key)
	{
	    if(root != null){
		if(root.key == key){
		    return size(root.right);
		}
		else if(root.key < key){
		    return countGreater(root.right, key);
		}
		else
		    return root.cnt + size(root.right) + countGreater(root.left, key);
	    }
	    else
		return 0;
	}
	public int countGreater(long key){
	    return countGreater(root, key);	    
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

	public Node add(long key, Node root, Node parent) {
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
    
    private static void solve1(FastScanner s1, PrintWriter out){ /* Causes TLE */

	int N = s1.nextInt();
	TreeSet<Integer> set = new TreeSet<>();
	while(N-->0)
	    set.add(s1.nextInt());
	
	int q = s1.nextInt();
	while(q-->0){
	    int rank = set.headSet(s1.nextInt(), true).size();
	    out.println(rank);
	}
    }
    static void shuffleArray(int[] array) {
	Random random = new Random();
	for (int i = array.length - 1; i > 0; i--) {
	    int index = random.nextInt(i + 1);
	    int temp = array[index];
	    array[index] = array[i];
	    array[i] = temp;
	}
    }

    private static void solve2(FastScanner s1, PrintWriter out){
	
	int N = s1.nextInt();
	int arr[] = s1.nextIntArray(N);
	Arrays.sort(arr);
	int Q = s1.nextInt();
	while(Q-->0)
	    out.println(Arrays.binarySearch(arr, s1.nextInt()) + 1);
    }
    
    private static void solve3(FastScanner s1, PrintWriter out){
	
	int N = s1.nextInt();
	int temp = N;
	AVLTree avl = new AVLTree();
	while(temp-->0)
	    avl.add(s1.nextInt());
	int Q = s1.nextInt();
	while(Q-->0)
	    out.println(N - avl.countGreater(s1.nextInt()));
    }
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE *********************/

    public static void main(String []args) throws IOException {
	FastScanner in  = new FastScanner(System.in);
	PrintWriter out = 
		new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false); 
	solve3(in, out);
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