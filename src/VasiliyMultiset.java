import java.util.*;
import java.io.*;
public class VasiliyMultiset
{


	/************************ SOLUTION STARTS HERE ***********************/

	static class Node {
		Node zero;
		Node one;
	}

	static class Trie {

		Node root;
		static final int offset = 31;  //Maximum index of set bit
		Trie(){
			root = new Node();
		}

		void insert(int num){
			root = insert(root, num, offset);
		}
		
		
		
		Node insert(Node curr , int num , int idx){

			if(idx < 0)
				return new Node();
			else{
				if(curr == null)
					curr = new Node();
				if((num & (1 << idx)) == 0)
					curr.zero = insert(curr.zero, num, idx - 1);
				else
					curr.one = insert(curr.one, num, idx - 1);

				return curr;
			}

		}
		
		void remove(int num){
			remove(root, null, num, offset);
		}
		
		void remove(Node curr , Node parent , int num , int idx){

			if(idx >= 0){
				if((num & (1 << idx)) == 0)
					remove(curr.zero, curr, num, idx - 1);
				else
					remove(curr.one, curr, num, idx - 1);
			}

			if(curr.zero == null && curr.one == null){
				if(parent.zero == curr)
					parent.zero = null;
				else
					parent.one = null;
			}

		}
		
		int maxXOR(Node curr , int num , int idx , int maxElem){
			if(idx < 0)
				return num ^ maxElem;
			else{
				if((num & (1 << idx)) == 0){
					return curr.one != null ? maxXOR(curr.one,  num, idx - 1, maxElem | (1 << idx))
											: maxXOR(curr.zero, num, idx - 1, maxElem);
				}				
				else{
					return curr.zero != null ? maxXOR(curr.zero, num, idx - 1, maxElem)
											 : maxXOR(curr.one,  num, idx - 1, maxElem | (1 << idx));
				}
			}
		}
		
		int maxXOR(int num){
			return maxXOR(root, num, offset, 0);
		}
		
	}
	
	
	private static void solve(FastScanner s1, PrintWriter out){

		HashMap<Integer,Integer> multiSet = new HashMap<>();
		Trie trie = new Trie();
		trie.insert(0);
		int Q = s1.nextInt();
		
		while(Q-->0){
			
			char choice = s1.nextChar();
			int num = s1.nextInt();
			switch(choice){
			
			case '+':
				if(!multiSet.containsKey(num)){
					trie.insert(num);
					multiSet.put(num, 1);
				}
				else
					multiSet.put(num, multiSet.get(num) + 1);
				
				break;
				
			case '-':
				if(multiSet.containsKey(num)){
					
					int freq = multiSet.get(num);
					if(freq == 1){
						trie.remove(num);
						multiSet.remove(num);
					}
					else
						multiSet.put(num, freq - 1);
					
				}
				break;
				
			case '?':
				out.println(trie.maxXOR(num));
				break;
				
			}
			
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