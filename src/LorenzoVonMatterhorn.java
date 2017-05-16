import java.util.*;
import java.io.*;
public class LorenzoVonMatterhorn
{


    /************************ SOLUTION STARTS HERE ***********************/

    static class Edge
    {
	long u , v;
	Edge(long u , long v){
	    this.u = Math.min(u,v);
	    this.v = Math.max(u,v);
	}
	@Override
	public int hashCode() {
	    return Objects.hash(u , v);
	}
	@Override
	public boolean equals(Object obj) {
	    Edge that = (Edge) obj;
	    return that.u == this.u && that.v == this.v;
	}
    }

    static int log(long n){
	return 63 - Long.numberOfLeadingZeros(n);
    }

    static ArrayList<Edge> getPath(long u , long v){
	ArrayList<Edge> path = new ArrayList<>();

	long uu = Math.max(u,v);
	long vv = Math.min(u,v);

	while(log(uu) != log(vv)){
	    path.add(new Edge(uu, uu >> 1));
	    uu >>= 1;
	}
	while(uu != vv){
	    path.add(new Edge(uu, uu >> 1));
	    path.add(new Edge(vv, vv >> 1));
	    uu >>= 1;
	    vv >>= 1;
	}

	return path;
    }

    private static void solve(FastScanner s1, PrintWriter out){

	int q = s1.nextInt();
	HashMap<Edge, Long> map = new HashMap<>();
	while(q-->0){

	    if(s1.nextInt() == 1){
		long u = s1.nextLong();
		long v = s1.nextLong();
		long cost = s1.nextLong();

		for(Edge e : getPath(u, v))
		    map.put(e, map.getOrDefault(e, 0L) + cost);
	    }
	    else{
		
		long dist = 0;
		for(Edge e:getPath(s1.nextLong(), s1.nextLong()))
		    dist += map.getOrDefault(e, 0L);
		
		out.println(dist);
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