import java.util.*;
import java.io.*;
public class CompletetheWord
{


	/************************ SOLUTION STARTS HERE ***********************/

	static boolean isPossible(HashMap<Character , Integer> map){
		for(Map.Entry<Character, Integer> e : map.entrySet())
			if(e.getKey() != '?' && e.getValue() > 1)
				return false;

		return true;
	}
	static String findNice(HashMap<Character , Integer> map , int start , String str) {
		char avail[] = new char[26];
		int ptr = 0;
		for(int i=0;i<26;i++)
			if(!map.containsKey((char)('A'+i)))
				avail[ptr++] = (char)('A'+i);
		ptr = 0;
		char change[] = str.toCharArray();
		for(int i=start;i<start+26;i++)
			if(str.charAt(i) == '?')
				change[i] = avail[ptr++];
		for(int i=0;i<change.length;i++)
			if(change[i] == '?')
				change[i] = 'A';

		return new String(change);
	}
	private static void solve(FastScanner s1, PrintWriter out){

		String str = s1.nextLine();
		int N = str.length();
		if(N < 26) {
			out.println(-1);
			return;
		}
		else {
			HashMap<Character , Integer> map = new HashMap<>();
			for(int i=0;i<26;i++)
				map.put(str.charAt(i), map.getOrDefault(str.charAt(i), 0) + 1);

			boolean flag = false;
			int start = 0;
			if(isPossible(map))
				flag = true;
			else {
				for(int i=26;i<N;i++) {
					int freq = map.get(str.charAt(i - 26));
					if(freq == 1)
						map.remove(str.charAt(i - 26));
					else
						map.put(str.charAt(i - 26), freq - 1);
					map.put(str.charAt(i), map.getOrDefault(str.charAt(i), 0) + 1);
					if(isPossible(map)){
						flag = true;
						start = i - 25;
						break;
					}
				}
			}
			
			if(flag)
				out.println(findNice(map, start, str));
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