import java.util.*;
import java.io.*;
public class Anagram 
{

    
    /************************ SOLUTION STARTS HERE ***********************/

    public static HashMap<Character, Integer> freqOfChar(String str) {
	HashMap<Character, Integer> mp = new HashMap<>();
	for (int i = 0, len = str.length(); i < len; i++) {
	    char k = str.charAt(i);
	    Integer query = mp.get(k);
	    if (query == null)
		mp.put(k, new Integer(1));
	    else {
		mp.put(k, query + 1);
	    }
	}
	return mp;
    }
    
    private static void solve(FastScanner s1, PrintWriter out){

	int t = s1.nextInt();
	while(t-->0){
	    
	    String str = s1.nextLine();
	    if(str.length() % 2 == 1)
		out.println(-1);
	    else{
		
		HashMap<Character,Integer> f1 = freqOfChar(str.substring(0, str.length()/2));
		HashMap<Character,Integer> f2 = freqOfChar(str.substring(str.length()/2));
		int cnt = 0;
		for (Map.Entry<Character,Integer> e : f2.entrySet()) {
		    int diff = e.getValue() - f1.getOrDefault(e.getKey(), 0);
		    cnt += diff > 0 ? diff : 0;
		}
		
		out.println(cnt);
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