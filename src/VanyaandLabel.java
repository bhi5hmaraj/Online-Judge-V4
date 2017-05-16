import java.util.*;
import java.io.*;
public class VanyaandLabel
{

    
    /************************ SOLUTION STARTS HERE ***********************/


    
    private static void solve(FastScanner s1, PrintWriter out){

	char map[] = new char[64];
	char ch = '0';
	HashMap<Character,Integer> invMap = new HashMap<>();
	for(int i=0;i<=9;i++){
	    invMap.put(ch, i);
	    map[i] = ch++;
	}
	ch = 'A';
	for(int i=10;i<=35;i++){
	    invMap.put(ch, i);
	    map[i] = ch++;
	}
	ch = 'a';
	for(int i=36;i<=61;i++){
	    invMap.put(ch, i);
	    map[i] = ch++;
	}
	map[62] = '-';
	map[63] = '_';
	invMap.put('-', 62);
	invMap.put('_', 63);
	
	int andCnt[] = new int[64];
	for(int i=0;i<64;i++){
	    for(int j=0;j<64;j++){
		andCnt[i&j]++;
	    }
	}
	long cnt = 1;
	long mod = (long)(1e9) + 7L;
	char str[] = s1.nextLine().toCharArray();
	for(char c : str){
	    cnt = ((cnt % mod) * (andCnt[invMap.get(c)] % mod)) % mod;
	}
	out.print(cnt);
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