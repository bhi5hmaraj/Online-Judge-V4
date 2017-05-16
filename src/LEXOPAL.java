import java.util.*;
import java.io.*;
class LEXOPAL
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int t = s1.nextInt();
		while(t-->0)
		{
			
			String str = s1.nextLine();
			boolean flag = true;
			for(int i=0,len = str.length();i < (len/2);i++){
				if(Character.isAlphabetic(str.charAt(i)) && Character.isAlphabetic(str.charAt(len - i - 1)))
					flag &= str.charAt(i) == str.charAt(len - i - 1);
			}
			
			if(flag){
				char arr[] = str.toCharArray();
				for(int i=0,len = str.length();i < (len/2);i++){
					char a = arr[i];
					char b = arr[len - i - 1];
					if(a == '.' || b == '.'){
						if(b == '.' && a == '.'){
							arr[i] = 'a';
							arr[len - i - 1] = 'a';
						}
						else if(a == '.')
							arr[i] = arr[len - i - 1];
						else
							arr[len - i - 1] = arr[i];
					}
				}
				arr[arr.length / 2] = (arr.length % 2 == 1 && arr[arr.length / 2] == '.') ? 'a' : arr[arr.length / 2];
				out.println(new String(arr));
			}
			else
				out.println(-1);
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