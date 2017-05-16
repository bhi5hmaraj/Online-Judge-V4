import java.util.*;
import java.io.*;
public class ShilandPalindromeResearch
{

    
    /************************ SOLUTION STARTS HERE ***********************/

    static class FenwickTree{
	
	int len;
	int BIT[];
	
	public FenwickTree(int length) {
	    len = length;
	    BIT = new int[len + 10];
	}
	
	void update(int idx , int inc){
	    for(;idx <= len; idx += (idx & ((~idx) + 1)))
		BIT[idx] += inc;
	}
	
	int getSum(int end){
	    int sum = 0;
	    for(;end > 0; end -= (end & ((~end) + 1)))
		sum += BIT[end];
	    
	    return sum;
	}
	
	int getSum(int L , int R){
	    return getSum(R) - getSum(L - 1);
	}
    }
    
    
    static boolean canPalin(int freq[] , int len){
	
	if(len % 2 == 0){
	    boolean flag = true;
	    for(int n:freq)
		flag &= n % 2 == 0;
	    
	    return flag;
	}
	else{
	    int cnt = 0;
	    for(int n:freq)
		cnt += n % 2 ;
	    return cnt == 1;
	}
	
    }
    
    private static void solve(FastScanner s1, PrintWriter out){

	int N = s1.nextInt();
	int Q = s1.nextInt();
	char str[] = s1.nextLine().toCharArray();
	
	FenwickTree BITs[] = new FenwickTree[26];
	for(int i=0;i<26;i++)
	    BITs[i] = new FenwickTree(N);
	for(int i=0;i<N;i++)
	    BITs[str[i] - 'a'].update(i + 1, 1);
	
	while(Q-->0)
	{
	    if(s1.nextInt() == 1){
		int pos = s1.nextInt();
		char ch = s1.nextChar();
		BITs[str[pos - 1] - 'a'].update(pos, -1);
		str[pos - 1] = ch;
		BITs[str[pos - 1] - 'a'].update(pos, 1);
	    }
	    else{
		int freq[] = new int[26];
		int L = s1.nextInt();
		int R = s1.nextInt();
		for(int i=0;i<26;i++)
		    freq[i] = BITs[i].getSum(L, R);
		out.println(canPalin(freq, R - L + 1) ? "yes" : "no");
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