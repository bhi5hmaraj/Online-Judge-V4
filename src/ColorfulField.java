import java.util.*;
import java.io.*;

public class ColorfulField{
	
	
	
	/************************ SOLUTION STARTS HERE ************************/
	
	
	
	private static void solve() {
		
		int n = nextInt();
		int m = nextInt();
		int k = nextInt();
		int t = nextInt();
		
		String names[] = {"Carrots" , "Kiwis" , "Grapes"};
		
		HashSet<Integer> set = new HashSet<>();
		int rowPrefix[] = new int[n + 1];
		
		ArrayList<Integer>[] wastes = new ArrayList[n + 1];
		for(int i = 1; i <= n; i++)
			wastes[i] = new ArrayList<>();
		
		
		while(k-->0) {
			int pt[] = nextIntArray(2);
			set.add(pt[0] * m + pt[1]);
			rowPrefix[pt[0]]++;
			wastes[pt[0]].add(pt[1]);
		}
		
		for(int i = 1; i <= n; i++) {
			Collections.sort(wastes[i]);
			rowPrefix[i] += rowPrefix[i - 1];
		}
		
		while(t-->0) {
			int x = nextInt();
			int y = nextInt();
			
			if(set.contains(x * m + y)) {
				println("Waste");
				continue;
			}
			
			int totalWaste = rowPrefix[x - 1];
			int lo = 0;
			int hi = wastes[x].size() - 1;
			
			while(lo <= hi) {
				int mid = (lo + hi) >> 1;
				if(y >= wastes[x].get(mid)) {
					totalWaste += mid - lo + 1;
					lo = mid + 1;
				} else 
					hi = mid - 1;
			}
			
//			System.out.println("waste " + totalWaste);
			
			println(names[((x - 1) * m + y - totalWaste - 1) % 3]);
			
		}
		
	}
	
	
	
	/************************ SOLUTION ENDS HERE ************************/
	
	
	
	
	
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