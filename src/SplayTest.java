import java.util.*;
import java.io.*;
public class SplayTest
{

    
    /************************ SOLUTION STARTS HERE ***********************/


    static class SplayTree {
 	private Vertex root = null;

 	static class Vertex {
 	    int key;
 	    int size;
 	    int cnt;
 	    Vertex left;
 	    Vertex right;
 	    Vertex parent;

 	    Vertex(int key, Vertex left, Vertex right, Vertex parent) {
 		this.key = key;
 		this.size = 1;
 		this.cnt = 1;
 		this.left = left;
 		this.right = right;
 		this.parent = parent;
 	    }

 	    @Override
 	    public String toString() {
 		return "[key = " + key + "]" + " p = " + (parent != null ? parent.key : "null") + " l = "
 			+ (left != null ? left.key : "null") + " r = " + (right != null ? right.key : "null");
 	    }
 	}

 	static class VertexPair {
 	    Vertex left;
 	    Vertex right;

 	    VertexPair() {
 	    }

 	    VertexPair(Vertex left, Vertex right) {
 		this.left = left;
 		this.right = right;
 	    }
 	}

 	private void update(Vertex v) {
 	    if (v == null)
 		return;
 	    v.size = (v.cnt + (v.left != null ? v.left.size : 0) + (v.right != null ? v.right.size : 0));
 	    if (v.left != null) {
 		v.left.parent = v;
 	    }
 	    if (v.right != null) {
 		v.right.parent = v;
 	    }
 	}

 	private void smallRotation(Vertex v) {
 	    Vertex parent = v.parent;
 	    if (parent == null) {
 		return;
 	    }
 	    Vertex grandparent = v.parent.parent;
 	    if (parent.left == v) {
 		Vertex m = v.right;
 		v.right = parent;
 		parent.left = m;
 	    } else {
 		Vertex m = v.left;
 		v.left = parent;
 		parent.right = m;
 	    }

 	    update(parent);
 	    update(v);
 	    v.parent = grandparent;
 	    if (grandparent != null) {
 		if (grandparent.left == parent) {
 		    grandparent.left = v;
 		} else {
 		    grandparent.right = v;
 		}
 	    }
 	    update(grandparent);

 	}

 	private void bigRotation(Vertex v) {
 	    if (v.parent.left == v && v.parent.parent.left == v.parent) {
 		// Zig-zig left
 		/*       a
 		 *      /
 		 *     /
 		 *    b  
 		 */
 		smallRotation(v.parent);
 		smallRotation(v);
 	    } else if (v.parent.right == v && v.parent.parent.right == v.parent) {
 		// Zig-zig right
 		/*
 		 * 	a
 		 * 	 \
 		 * 	  \
 		 * 	   b
 		 */
 		smallRotation(v.parent);
 		smallRotation(v);
 	    } else {
 		/* Zig-zag
 		 *    a    a
 		 *    /    \
 		 *    \	   /
 		 *     b   b
 		 */
 		smallRotation(v);
 		smallRotation(v);
 	    }
 	}

 	private Vertex splay(Vertex v) {
 	    if (v == null)
 		return null;
 	    while (v.parent != null) {
 		if (v.parent.parent != null)
 		    bigRotation(v);
 		else
 		    smallRotation(v);
 	    }
 	    return v;
 	}

 	// Searches for the given key in the tree with the given root
 	// and calls splay for the deepest visited node after that.
 	// Returns pair of the result and the new root.
 	// If found, result is a pointer to the node with the given key.
 	// Otherwise, result is a pointer to the node with the smallest
 	// bigger key (next value in the order).
 	// If the key is bigger than all keys in the tree,
 	// then result is null.
 	private VertexPair find(Vertex root, int key) {  //searches for a elem and splays the last touched node.
 	    Vertex v = root;	// Returns a vertex pair with 
 	    Vertex last = root; // Left node  = ceil of the search
 	    Vertex next = null; // Right node = last touched node in the search
 	    while (v != null) {
 		if (v.key >= key && (next == null || v.key < next.key)) {
 		    next = v;
 		}
 		last = v;
 		if (v.key == key) {
 		    break;
 		}
 		if (v.key < key) {
 		    v = v.right;
 		} else {
 		    v = v.left;
 		}
 	    }
 	    root = splay(last);
 	    return new VertexPair(next, root);
 	}

 	private static void print(Vertex root) {
 	    if (root != null) {
 		print(root.left);
 		System.out.println(root);
 		print(root.right);
 	    }
 	}

 	private VertexPair split(Vertex root, int key) {
 	    VertexPair result = new VertexPair();
 	    VertexPair findAndRoot = find(root, key);
 	    root = findAndRoot.right;
 	    result.right = findAndRoot.left;
 	    if (result.right == null) { // Key is largest in the tree
 		result.left = root;
 		return result;
 	    }
 	    result.right = splay(result.right);
 	    result.left = result.right.left;
 	    result.right.left = null;
 	    if (result.left != null)
 		result.left.parent = null;

 	    update(result.left);
 	    update(result.right);

 	    return result;
 	}

 	private Vertex merge(Vertex left, Vertex right) {
 	    if (left == null)
 		return right;
 	    if (right == null)
 		return left;
 	    left = find(left, Integer.MAX_VALUE).right; // root after the find and splay , Find the max in left tree							 
 	    left.right = right;
 	    left.parent = null;
 	    update(left);
 	    return left;
 	}

 	public void insert(int x) {      // Split the tree at x and then the left tree has all elements less
 	    			        // than x and right tree has all elements greater now create a new node
 		Vertex left = null;    // and merge the left-->new node-->right
 		Vertex right = null;
 		Vertex new_vertex = null;
 		VertexPair leftRight = split(root, x);
 		left = leftRight.left;
 		right = leftRight.right;
 		if (right == null || right.key != x) {
 		    new_vertex = new Vertex(x, null, null, null);
 		}
 		else if(right != null && right.key == x)
 		    right.cnt++;
 		
 		root = merge(merge(left, new_vertex), right);
 		root.parent = null;
 		update(root);
 	    
 	}

 	public void erase(int x) {
 	    VertexPair vp = find(root, x);
 	    root = vp.right;
 	    if (root != null)
 		root.parent = null;
 	    update(root);
 	    if (vp.right != null && vp.right.key == x) {
 		if (vp.right.right == null) {
 		    root = vp.right.left;
 		    if (root != null)
 			root.parent = null;
 		    update(root);
 		} else {   		//First splay the ceil of x then splay x now the ceil of x is in the right , 		    				
 		    find(root, x + 1);  //so we can just redirect the root = root.right 
 		    vp = find(root, x);
 		    root = vp.right.right;
 		    root.left = vp.right.left;
 		    root.parent = null;
 		    update(root);
 		}
 	    }
 	}

 	public boolean find(int x) {
 	    VertexPair vp = find(root, x);
 	    root = vp.right;
 	    return root != null && root.key == x;
 	}

 	public int countLess(int n)
 	{
 	    VertexPair pair = split(root, n + 1);
 	    int count = (pair.left == null) ? 0 : pair.left.size;
 	    root = merge(pair.left, pair.right);
 	    return count;
 	}
 	private StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb, Vertex root) {

 	    if (root == null) {
 		sb.append("Tree Empty");
 		return sb;
 	    }

 	    if (root.right != null) {
 		toString(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb, root.right);
 	    }
 	    sb.append(prefix).append(isTail ? "└── " : "┌── ").append(root.key).append("\n");
 	    if (root.left != null) {
 		toString(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb, root.left);
 	    }
 	    return sb;
 	}

 	@Override
 	public String toString() {
 	    return this.toString(new StringBuilder(), true, new StringBuilder(), root).toString();
 	}
     }

    private static void solve(FastScanner s1, PrintWriter out){

	int n = s1.nextInt();
	int m = s1.nextInt();
	SplayTree tree = new SplayTree();
	while(n-->0)
	    tree.insert(s1.nextInt());
	while(m-->0)
	    out.print(tree.countLess(s1.nextInt()) + " ");
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