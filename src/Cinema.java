import java.util.*;
import java.io.*;
public class Cinema
{

    
    /************************ SOLUTION STARTS HERE ***********************/

    static class Movie implements Comparable<Movie>{
	int audio, sub , aCnt, sCnt;
	int index;
	Movie(int a,int s, int index){
	    audio = a;
	    sub = s;
	    this.index = index;
	}
	@Override
	public int compareTo(Movie o) {
	    if(this.aCnt != o.aCnt)
		return aCnt - o.aCnt;
	    else
		return sCnt - o.sCnt;
	}
    }

    private static <Key> void frequency(Map<Key, Integer> mp, Key k) {
	Integer query = mp.get(k);
	mp.put(k, query == null ? 1 : query + 1);
    }
    private static void solve(FastScanner s1, PrintWriter out){

	int N = s1.nextInt();
	HashMap<Integer,Integer> scientist = new HashMap<>();
	while(N-->0)
	    frequency(scientist, s1.nextInt());
	
	int m = s1.nextInt();
	Movie arr[] = new Movie[m];

	for(int i=0;i<m;i++){
	    int a = s1.nextInt();	    
	    arr[i] = new Movie(a, 0, i+1);
	}
	for(int i=0;i<m;i++){
	    int s = s1.nextInt();	    
	    arr[i].sub = s;
	}
	
	Movie temp = new Movie(-1, -1, 1);
	
	for(int i=0;i<m;i++){
	    arr[i].aCnt = scientist.getOrDefault(arr[i].audio,0);
	    arr[i].sCnt = scientist.getOrDefault(arr[i].sub,0);
	    temp = arr[i].compareTo(temp) > 0 ? arr[i] :temp;
	}	
	out.print(temp.index);
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