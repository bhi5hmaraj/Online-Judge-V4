import java.util.*;
import java.io.*;
public class LakesinBerland
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static int x[] = {-1 , 0 , 1 , 0}; //up , right , down and left 
	static int y[] = {0 , 1 , 0 , -1};
	static int R , C;
	static boolean marked[][];
	static char grid[][];
	static boolean isValid(int i , int j) {
		return i >= 0 && i < R && j >= 0 && j < C;
	}
	static boolean isEdge(int i , int j) {
		return i == 0 || i == R - 1 || j == 0 || j == C - 1;
	}
	
	
	static int dfsCount(int i , int j) {
		int cnt = 1;
		marked[i][j] = true;
		boolean flag = isEdge(i, j);
		
		for(int k = 0;k<4;k++)
			if(isValid(x[k]+i, y[k]+j) && grid[x[k]+i][y[k]+j] == '.' && !marked[x[k] + i][y[k] + j]) {
				int res = dfsCount(x[k] + i, y[k] + j);
				if(res > 0 )
					cnt += res;
				flag |= res == -1;
			}
				 
		return flag ? -1 : cnt;
	}
	
	static void fill(int i , int j) {
		marked[i][j] = true;
		grid[i][j] = '*';
		for(int k = 0;k<4;k++)
			if(isValid(x[k]+i, y[k]+j) && grid[x[k]+i][y[k]+j] == '.' && !marked[x[k] + i][y[k] + j])
				fill(i + x[k], j + y[k]);
	}
	
	static class Lake implements Comparable<Lake> {
		int x , y , size;
		Lake(int _x , int _y , int _size) {
			x = _x;
			y = _y;
			size = _size;
		}
		@Override
		public int compareTo(Lake o) {
			return Integer.compare(this.size, o.size);
		}
		@Override
		public String toString() {
			return "["+x+", " + y+"] " + "sz " + size;
		}
	}
	private static void solve(FastScanner s1, PrintWriter out){
		
		R = s1.nextInt();
		C = s1.nextInt();
		int K =  s1.nextInt();
		grid = new char[R][];
		marked = new boolean[R][C];
		for(int i=0;i<R;i++)
			grid[i] = s1.nextLine().toCharArray();
		
		ArrayList<Lake> lakes = new ArrayList<>();
		
		for(int i=0;i<R;i++)
			for(int j=0;j<C;j++)
				if(!marked[i][j] && grid[i][j] == '.') {
					int sz = dfsCount(i, j);
					if(sz > 0)
						lakes.add(new Lake(i, j, sz));
				}
					
		
		Collections.sort(lakes);
		int cnt = 0;
		marked = new boolean[R][C];
		for(int i=0,need = lakes.size() - K;i < need;i++) {
			fill(lakes.get(i).x, lakes.get(i).y);
			cnt += lakes.get(i).size;
		}
		
		
		out.println(cnt);
		for(int i=0;i<R;i++)
			out.println(new String(grid[i]));
		
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