import java.util.*;
import java.io.*;
public class CountryLeader
{

    
    /************************ SOLUTION STARTS HERE ***********************/

    private static int filter(String str) // Removes duplicate chars in a
    // string
    {
	HashSet<Character> filter = new HashSet<>();
	for (char c : str.toCharArray()) {
	    if (!filter.contains(c) && Character.isAlphabetic(c)) {
		filter.add(c);
	    }
	}
	return filter.size();
    }

    static class Pair implements Comparable<Pair>{
	
	String str;
	int unique;
	Pair(String s){
	    str = s;
	    unique = filter(s);
	}
	@Override
	public int compareTo(Pair o) {
	    if(this.unique != o.unique)
		return Integer.compare(o.unique, this.unique);
	    else
		return this.str.compareTo(o.str);
	}
    }
    
    private static void solve(FastScanner s1, PrintWriter out){

	int t = s1.nextInt();
	for(int z = 1;z<=t;z++){
	    
	    int N = s1.nextInt();
	    Pair arr[] = new Pair[N];
	    for(int i=0;i<N;i++)
		arr[i] = new Pair(s1.nextLine());
	    
	    Arrays.sort(arr);
	    out.println("Case #"+z+": " + arr[0].str);
	    
	}

    }


    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE *********************/

    public static void main(String []args) throws IOException {
	FastScanner in  = new FastScanner(System.in);
	/*
	PrintWriter out = 
		new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false); 
		*/
	PrintWriter out = new PrintWriter("out.txt");
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