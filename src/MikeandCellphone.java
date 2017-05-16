import java.util.*;
import java.io.*;
public class MikeandCellphone
{

    
    /************************ SOLUTION STARTS HERE ***********************/

    static class Pair
    {
	int r,c;
	Pair(int r,int c){
	    this.r = r;
	    this.c = c;
	}
	@Override
	public String toString() {
	    return "r "+r+" c "+c;
	}
    }
    
    
    
    private static void solve(FastScanner s1, PrintWriter out){

	HashMap<Integer,Pair> mp = new HashMap<>();
	for(int i=0;i<3;i++)
	    for(int j=0;j<3;j++)
		mp.put((i*3)+(j+1), new Pair(i, j));
	
	mp.put(0, new Pair(3, 1));
	
	int n = s1.nextInt();
	String in = s1.nextLine();
	ArrayList<Pair> action = new ArrayList<>();
	for(int i=0;i<n-1;i++){
	    Pair a = mp.get(in.charAt(i) - '0');
	    Pair b = mp.get(in.charAt(i+1) - '0');
	    action.add(new Pair(b.r - a.r, b.c - a.c));
	}
	
	int cnt = 0;
	for(int i=0;i<3;i++){
	    for(int j=0;j<3;j++){
		
		boolean flag = true;
		Pair curr = new Pair(i, j);
		for(Pair move:action){
		    
		    curr.r += move.r;
		    curr.c += move.c;		    
		    flag &= (curr.r >= 0 && curr.r < 3 && curr.c >= 0 && curr.c < 3) || curr.r == 3 && curr.c == 1;

		}
		cnt += flag ? 1 : 0;
	    }
	}
	
	boolean flag = true;
	Pair curr = new Pair(3, 1);
	for(Pair move:action){

	    curr.r += move.r;
	    curr.c += move.c;
	    flag &= (curr.r >= 0 && curr.r < 3 && curr.c >= 0 && curr.c < 3) || curr.r == 3 && curr.c == 1;

	
	}
	cnt += flag ? 1 : 0;
	out.println(cnt > 1 ? "NO" : "YES");
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