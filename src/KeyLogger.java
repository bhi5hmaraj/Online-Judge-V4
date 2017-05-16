import java.util.*;
import java.io.*;
public class KeyLogger
{

	/************************ SOLUTION STARTS HERE ***********************/

	static class Node {
		Node left , right;
		char ch;
		Node(char ch) {
			this.ch = ch;
			left = right = null;
		}
		@Override
		public String toString() {
			return "["+ch+"]";
		}
		
	}

	static Node insert(Node n , char ch) {
		Node newNode = new Node(ch);
		if(n == null) {
			if(root != null){
				newNode.right = root;
				root.left = newNode;
			}
			root = newNode;
			return newNode;
		}
		
		if(n.right == null) {
			n.right = newNode;
			newNode.left = n;
		}
		else {
			Node temp = n.right;
			n.right = newNode;
			newNode.left = n;
			newNode.right = temp;
			temp.left = newNode;
		}
		return newNode;
	}
	static Node delete(Node n) {
		if(n == null)
			return n;
		if(n == root) {
			root = root.right;
			if(root != null)
				root.left = null;
			return null;
		}
		
		Node l = n.left;
		Node r = n.right;
		if(l != null)
			l.right = r;
		if(r != null)
			r.left = l;
		
		return l;
	}
	static Node cursor;
	static Node root;
	private static void solve(FastScanner s1, PrintWriter out){
		int t = s1.nextInt();
		while(t-->0) {
			String str  = s1.nextLine();
			root = cursor = null;
			for(int i=0,len=str.length();i<len;i++) {
				char curr = str.charAt(i);
				// System.out.println("root = " + root + " cursor = " + cursor);
				if(Character.isAlphabetic(curr) || Character.isDigit(curr)) 
					cursor = insert(cursor, curr);
				else if(curr == '-' && root != null) 
					cursor = delete(cursor);
				else if(curr == '>'){ 
					if(cursor == null && root != null)
						cursor = root;
					else if(cursor != null && cursor.right != null)
						cursor = cursor.right;
				}
				else if(curr == '<' && cursor != null)
					cursor = cursor.left;
			}
			for(;root != null;root = root.right)
				out.print(root.ch);
			out.println();
		}
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