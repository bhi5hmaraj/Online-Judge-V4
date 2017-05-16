import java.util.*;
import java.io.*;
public class ProblemsforRound
{


    /************************ SOLUTION STARTS HERE ***********************/



    private static void solve(FastScanner s1, PrintWriter out){

	int n = s1.nextInt();
	TreeSet<Integer> div2 = new TreeSet<>();
	TreeSet<Integer> div1 = new TreeSet<>();
	int m = s1.nextInt();
	if(m == 0){
	    out.println(n - 1);
	    return;
	}
	while(m-->0){
	    int u = s1.nextInt();
	    int v = s1.nextInt();
	    int min = Math.min(u,v);
	    int max = Math.max(u,v);
	    u = min;v = max;
	    if((div2.contains(u) && div2.contains(v)) || (div1.contains(u) && div1.contains(v)))
	    {
		out.print(0);
		return;
	    }
	    else if(div2.contains(u))
		div1.add(v);
	    else if(div1.contains(u))
		div2.add(v);
	    else if(div2.contains(v))
		div1.add(u);
	    else if(div1.contains(v))
		div2.add(u);
	    else {
		div2.add(u);
		div1.add(v);
	    }
	}
	
	if(div2.last() > div1.first()){
		out.print(0);
		return;
	}
	else
	{
	    TreeSet<Integer> set = new TreeSet<>();
	    for(int i=1;i<=n;i++){
		if(!div1.contains(i) && !div2.contains(i))
		    set.add(i);
	    }
	    out.print(set.subSet(div2.last(), div1.first()).size() + 1);
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