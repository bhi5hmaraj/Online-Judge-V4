import java.util.*;
import java.io.*;
public class PersistentBookcase
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int N = s1.nextInt();
		int M = s1.nextInt();
		int Q = s1.nextInt();
		
		ArrayList<BitSet> states = new ArrayList<>();
		int position[][] = new int[Q + 1][N + 1];
		states.add(new BitSet(M + 1));
		int cnt[] = new int[Q + 1];
		int books = 0;
		for(int q = 1;q <= Q;q++){
			int choice = s1.nextInt();
			System.arraycopy(position[q - 1], 0, position[q], 0, N + 1);
			if(1 == choice){
				int i = s1.nextInt();
				int j = s1.nextInt();
				BitSet b = states.get(position[q - 1][i]);
				if(!b.get(j)){
					b = (BitSet) b.clone();
					books++;
					b.set(j);
				}
				position[q][i] = states.size();
				states.add(b);
				cnt[q] = books;
			}
			else if(2 == choice){
				int i = s1.nextInt();
				int j = s1.nextInt();
				BitSet b = states.get(position[q - 1][i]);
				if(b.get(j)){
					b = (BitSet) b.clone();
					books--;
					b.set(j, false);
				}
				position[q][i] = states.size();
				states.add(b);
				cnt[q] = books;
			}
			else if(3 == choice){
				int i = s1.nextInt();
				BitSet b = states.get(position[q - 1][i]);
				int popCnt = b.cardinality();
				b = (BitSet) b.clone();
				b.flip(1, M + 1);
				books = books - (2 * popCnt) + M;
				position[q][i] = states.size();
				states.add(b);
				cnt[q] = books;
			}
			else {
				int k = s1.nextInt();
				for(int i=1;i<=N;i++)
					position[q][i] = position[k][i];
				books = cnt[k];
				cnt[q] = books;
			}
			out.println(books);
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