import java.util.*;
import java.io.*;
public class GoodlandElectricity
{


    /************************ SOLUTION STARTS HERE ***********************/



    private static void solve(FastScanner s1, PrintWriter out){

	int N = s1.nextInt();
	int K = s1.nextInt() - 1;

	TreeSet<Integer> ones = new TreeSet<>();
	TreeSet<Integer> zeros = new TreeSet<>();

	for(int i=0;i<N;i++){
	    if(s1.nextInt() == 0)
		zeros.add(i);
	    else
		ones.add(i);
	}

	int cnt = 0;
	if(zeros.isEmpty())
	    out.println(0);
	else{

	    int zero_pos = zeros.first();
	    boolean flag = true;
	    while(true){

		if(ones.floor(zero_pos + K) != null){
		    int floor = ones.floor(zero_pos + K);
		    if(Math.abs(floor - zero_pos) > K){
			flag = false;
			break;
		    }
		    cnt++;
		    if(zeros.higher(floor + K) != null)
			zero_pos = zeros.higher(floor + K);
		    else
			break;
		    
		}
		else{
		    flag = false;
		    break;
		}
	    }
	    
	    out.println(flag ? cnt : "-1");
	}

    }

    private static void solve2(FastScanner s1, PrintWriter out){
	
	int N = s1.nextInt();
	int K = s1.nextInt() - 1;
	int prev[] = new int[N];
	int last = -1;
	for(int i=0;i<N;i++){
	    if(s1.nextInt() == 1)
		last = i;
	    prev[i] = last;
	}
	int cnt = 0;
	for(int i=0;i<N;){
	    
	    int tower = prev[Math.min(N - 1,i + K)];
	    if(tower == -1 || tower + K < i){
		out.println( -1 );
		return;
	    }
	    
	    i = tower + K + 1;
	    cnt++;
	}
	
	out.println( cnt );
    }

    /************************ SOLUTION ENDS HERE ************************/





    /************************ TEMPLATE STARTS HERE *********************/

    public static void main(String []args) throws IOException {
	FastScanner in  = new FastScanner(System.in);
	PrintWriter out = 
		new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false); 
	solve2(in, out);
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