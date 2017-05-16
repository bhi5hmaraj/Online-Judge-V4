import java.util.*;
import java.io.*;
public class SoccerTeams
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int t = s1.nextInt();
		while(t-->0)
		{
			int d[] = s1.nextIntArrayOneBased(9);
			int cnt = 0;
			int size = 0;
			for(int i=1;i<=9;i++){
				cnt += d[i] - (d[i] % 2);
				size += d[i] % 2;
			}
			
			int rem[] = new int[size];
			int ptr = 0;
			int extra = Integer.MAX_VALUE;
			boolean flag = false;
			for(int i=1;i<=9;i++)
				if(d[i] % 2 == 1)
					rem[ptr++] = i;
			
			for(int i=1;i< (1 << size);i++){
				int sum = 0;
				for(int j=0;j<size;j++){
					if((i & (1 << j)) == 0)
						sum -= rem[j];
					else
						sum += rem[j];
				}
				if(sum >= 0 && (sum % 11 == 0)){
					flag = true;
					System.out.println(Integer.toBinaryString(i));
					System.out.println("sum " + sum);
					int pos_cnt = Integer.bitCount(i);
					int neg_cnt = size - pos_cnt;
					int num_digits ;
					if(pos_cnt > neg_cnt)
						num_digits = (2 * pos_cnt) - 1;
					else
						num_digits = 2 * neg_cnt;
					
					extra = Math.min(extra,num_digits);
				}
			}
			
			if(size == 0)
				out.println(cnt);
			else{
				if(flag)
					out.println(cnt + extra);
				else
					out.println(-1);
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