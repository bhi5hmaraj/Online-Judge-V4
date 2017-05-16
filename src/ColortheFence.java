import java.util.*;
import java.io.*;
public class ColortheFence
{



    /************************ SOLUTION STARTS HERE ***********************/

    static class Pair implements Comparable<Pair>{

	int len , cnt[];
	Pair(){
	    len = 0;
	    cnt = new int[10];
	}
	Pair(Pair o){
	    if(o == null){
		len = 0;
		cnt = new int[10];
	    }		
	    else{
		len = o.len;
		cnt = Arrays.copyOf(o.cnt, o.cnt.length);
	    }
	}
	void insert(int e){
	    len++;
	    cnt[e]++;
	}
	@Override
	public int compareTo(Pair o) {

	    if(len != o.len)
		return Integer.compare(len, o.len);
	    else{
		for(int i=9;i>=1;i--){
		    if(cnt[i] > o.cnt[i])
			return 1;
		    else if(cnt[i] < o.cnt[i])
			return -1;		    		    
		}

		return 0;
	    }
	}

	static Pair max(ArrayList<Pair> arl){

	    if(arl.isEmpty())
		return null;
	    Pair max = arl.get(0);
	    for(Pair p : arl)
		max = p.compareTo(max) > 0 ? p : max;

		return max;
	}

	@Override
	public String toString() {
	    return "length = "+len+" cnt "+Arrays.toString(cnt);		    
	}
    }

    private static void solve(FastScanner s1, PrintWriter out){

	int v = s1.nextInt();
	int a[] = s1.nextIntArrayOneBased(9);
	long start = System.nanoTime();
	if(v == 0)
	    out.print(-1);
	else{
	    Pair dp[] = new Pair[v+1];
	    dp[0] = new Pair();
	    for(int i=1;i<=v;i++){

		ArrayList<Pair> arl = new ArrayList<>();
		for(int j=1;j<=9;j++){
		    if(i - a[j] >= 0){
			Pair p = new Pair(dp[i - a[j]]);
			p.insert(j);
			arl.add(p);
		    }
		}
		dp[i] = Pair.max(arl);
	    }
	    
	    Pair ans = dp[v];
	    if(ans == null)
		out.print(-1);
	    else{
		for(int i=9;i>=1;i--){
		    for(int j=1;j<=ans.cnt[i];j++)
			out.print(i);
		}
	    }
		
	}
	long stop = System.nanoTime();
	System.err.println("\nTime : "+((stop-start)/1e9) + " s");
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