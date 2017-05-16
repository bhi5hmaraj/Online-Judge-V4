import java.util.*;
import java.io.*;
public class MatchtheShoes
{

    
    /************************ SOLUTION STARTS HERE ***********************/

    static class Pair implements Comparable<Pair>{
	
	int id,freq;
	Pair(int id,int freq){
	    this.id = id;
	    this.freq = freq;
	}
	@Override
	public int compareTo(Pair o) {
	    
	    if(freq != o.freq)
		return Integer.compare(o.freq, freq);
	    else
		return Integer.compare(id, o.id);	    
	}
    }

    private static <Key> void frequency(Map<Key, Integer> mp, Key k) {
	Integer query = mp.get(k);
	mp.put(k, query == null ? 1 : query + 1);
    }

    
    private static void solve(FastScanner s1, PrintWriter out){

	int K = s1.nextInt();
	int M = s1.nextInt();
	int N = s1.nextInt();
	HashMap<Integer, Integer> freq = new HashMap<>(M);
	PriorityQueue<Pair> pq = new PriorityQueue<>(M);
	while(N-->0)
	    frequency(freq, s1.nextInt());
	
	for (Map.Entry<Integer,Integer> e : freq.entrySet()) 
	    pq.add(new Pair(e.getKey(), e.getValue()));
	
	while(K-->0)
	    out.println(pq.remove().id);
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