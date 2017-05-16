import java.util.*;
import java.io.*;
public class Passwords
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int N = s1.nextInt();
		int K = s1.nextInt();
		String arr[] = new String[N];
		for(int i=0;i<N;i++)
			arr[i] = s1.nextLine();
		
		String pass = s1.nextLine();
		Arrays.sort(arr, (str1 , str2) -> Integer.compare(str1.length(), str2.length()));
		int idx = 0;
		for(int i=0;i<N;i++) {
			if(arr[i].equals(pass)){
				idx = i;
				break;
			}
		}
		int first = 0;
		for(int i=0;i<N;i++) {
			if(arr[i].length() == pass.length()) {
				first = i;
				String temp = arr[idx];
				arr[idx] = arr[i];
				arr[i] = temp;
				break;
			}
		}
		
		int min = 0;
		int pen = 0;
		for(int i=0;i<N;i++) {
			min++;			
			pen++;
			if(arr[i].equals(pass)) 
				break;
			else if(pen == K) {
				min += 5;
				pen = 0;
			}
		}
		int last = 0;
		for(int i=0;i<N;i++){
			if(arr[i].length() == pass.length())
				last = i;
		}
		String temp = arr[first];
		arr[first] = arr[last];
		arr[last] = temp;
		int max = 0;
		pen = 0;
		for(int i=0;i<N;i++) {
			max++;			
			pen++;
			if(arr[i].equals(pass)) 
				break;
			else if(pen == K) {
				max += 5;
				pen = 0;
			}
		}
		
		out.println(min + " " + max);
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