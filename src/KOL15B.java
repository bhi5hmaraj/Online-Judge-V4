import java.util.*;
import java.io.*;
class KOL15B
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static class MultiSet<T> extends TreeMap<T, Integer> {
		public void add(T key) {
			Integer q = super.get(key);
			if (q == null)
				super.put(key, 1);
			else
				super.put(key, q + 1);
		}

		@Override
		public Integer remove(Object key) {
			Integer q = super.get(key);
			if (q != null) {
				if (q == 1)
					super.remove(key);
				else
					super.put((T) key, q - 1);
			} else
				throw new NullPointerException("The specified key does not exist");

			return q;
		}
	}

	
	@SuppressWarnings("unchecked")
	private static void solve(FastScanner s1, PrintWriter out){
		
		int t = s1.nextInt();
		while(t-->0)
		{
			int R = s1.nextInt();
			int C = s1.nextInt();
			int arr[][] = new int[R + 1][];
			for(int i=1;i<=R;i++)
				arr[i] = s1.nextIntArrayOneBased(C);   // Input 1 indexed
			
			int rowPrefix[][] = new int[R + 1][C + 1];
			int colPrefix[][] = new int[R + 1][C + 1];   // Calculate Prefix Sum for each row , column
			for(int i=1;i<=R;i++)
				for(int j=1;j<=C;j++) {
					rowPrefix[i][j] = arr[i][j] + rowPrefix[i][j - 1];
					colPrefix[i][j] = arr[i][j] + colPrefix[i - 1][j];
				}
			
			MultiSet<Integer>[] row = new MultiSet[R + 1];  
			MultiSet<Integer>[] col = new MultiSet[C + 1];
			for(int i=0;i<=R;i++)
				row[i] = new MultiSet<>();
			for(int i=0;i<=C;i++)
				col[i] = new MultiSet<>();
			
			for(int i=1;i<=R;i++)
				for(int j=1;j<=C;j++) {
					row[i].add(rowPrefix[i][j]);
					col[j].add(colPrefix[i][j]);
				}
			int maxCol[] = new int[C + 1];
			int minSum = Integer.MAX_VALUE;
			
			for(int i=1;i<=R;i++) {
				int maxRow = 0;
				for(int j=1;j<=C;j++) {
					int rightRow = row[i].firstKey();
					int leftRow = maxRow;
					int rightCol = col[j].firstKey();
					int leftCol = maxCol[j];
					int currSum = (rightRow - leftRow) + (rightCol - leftCol) - arr[i][j];
					minSum = Math.min(minSum,currSum);
					int rowPre = rowPrefix[i][j];
					int colPre = colPrefix[i][j];
					maxRow = Math.max(maxRow,rowPre);
					maxCol[j] = Math.max(maxCol[j],colPre);
					row[i].remove(rowPre);
					col[j].remove(colPre);
				}
			}
			
			out.println(minSum);
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