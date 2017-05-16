import java.util.*;
import java.io.*;
public class BeautifulSetofPoints
{

    
    /************************ SOLUTION STARTS HERE ***********************/

    static boolean isPerfectSquare(int N){
	int root = (int)Math.sqrt(N);
	return root * root == N;
    }
    static int R , C;
    static boolean isValid(int i, int j){
	return i >= 0 && i <= R && j >= 0 && j <= C;
    }
    
    private static void solve(FastScanner s1, PrintWriter out){
	
	ArrayList<Integer> dx = new ArrayList<>();
	ArrayList<Integer> dy = new ArrayList<>();
	int len = dx.size();
	for(int i=1;i<=100;i++)
	    for(int j=1;j<=100;j++)
		if(isPerfectSquare((i * i) + (j * j))){
		    dx.add(i);
		    dy.add(j);
		}
	
	
	R = s1.nextInt();
	C = s1.nextInt();
	boolean marked[][] = new boolean[R+1][C+1];
	marked[0][0] = true;
	for(int i=0;i<=R;i++){
	    for(int j=0;j<=C;j++){
		if(!marked[i][j]){
		    
		    for(int r=0;r<=R;r++)
			if(r != i)
			    marked[r][j] = true;
		    
		    for(int c = 0;c<=C;c++)
			if(c != j)
			    marked[i][c] = true;
		    
		    
		    for(int ptr = 0;ptr<=len;ptr++){
			if(isValid(i + dx.get(ptr), j + dy.get(ptr)))
			    marked[i + dx.get(ptr)][j + dy.get(ptr)] = true;
			
			if(isValid(i + dx.get(ptr), j - dy.get(ptr)))
			    marked[i + dx.get(ptr)][j - dy.get(ptr)] = true;
		    }
			
		}
	    }
	}
	
	int cnt = 0;
	for(int i=0;i<=R;i++)
	    for(int j=0;j<=C;j++)
		cnt += !marked[i][j] ? 1 : 0;
	
	out.println(cnt);
	for(int i=0;i<=R;i++)
	    for(int j=0;j<=C;j++)
		if(!marked[i][j])
		    out.println(i + " " + j);
    }

    private static void solve2(FastScanner s1, PrintWriter out){
	
	R = s1.nextInt();
	C = s1.nextInt();
	out.println(Math.min(R + 1,C + 1));
	int i = 0;
	int j = C;
	while(isValid(i, j))
	    out.println((i++) + " " + (j--));
	
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