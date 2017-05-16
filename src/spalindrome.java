import java.util.*;
import java.io.*;
public class spalindrome
{


	/************************ SOLUTION STARTS HERE ***********************/



	private static void solve(FastScanner s1, PrintWriter out){

		HashMap<Character,Character> map = new HashMap<>();
		map.put('A', 'A');
		map.put('b', 'd');
		map.put('d', 'b');
		map.put('H', 'H');
		map.put('I', 'I');
		map.put('M', 'M');
		//map.put('m', 'm');
		map.put('O', 'O');
		map.put('o', 'o');
		map.put('p', 'q');
		map.put('q', 'p');
		map.put('T', 'T');
		map.put('U', 'U');
		for(char ch = 'V';ch < 'Z';ch++)
			map.put(ch, ch);
		for(char ch = 'v';ch < 'z';ch++)
			map.put(ch, ch);
		map.remove('y');

		String str = s1.nextLine();
		boolean flag = true;
		try{
			for(int i=0 , len = str.length(); i<=len/2 ; i++)
				flag &= str.charAt(len - i - 1) == map.get(str.charAt(i));
		}
		catch(Exception e){
			out.println("NIE");
			return;
		}
		out.println(flag ? "TAK" : "NIE");

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