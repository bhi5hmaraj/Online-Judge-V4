import java.util.*;
import java.io.*;
public class PhoneNumbers
{

    
    /************************ SOLUTION STARTS HERE ***********************/

    static class Friend {
	
	String name,contacts[];
	int cnt[];
	Friend(FastScanner s1){
	    int len = s1.nextInt();
	    name = s1.next();
	    contacts = new String[len];
	    cnt = new int[3];
	    for(int i=0;i<len;i++)
		contacts[i] = s1.nextLine().replaceAll("-", "");
	    
	    for(int i=0;i<len;i++){
		
		boolean f1 = true;
		boolean f2 = true;
		for(int j=1;j<6;j++){
		    f1 &= contacts[i].charAt(j) == contacts[i].charAt(0);
		    f2 &= contacts[i].charAt(j) < contacts[i].charAt(j-1);
		}
		
		if(f1)
		    cnt[0]++;
		else if(f2)
		    cnt[1]++;
		else
		    cnt[2]++;
	    }
	}
    }
    
    private static void solve(FastScanner s1, PrintWriter out){

	int n = s1.nextInt();
	Friend arr[] = new Friend[n];
	for(int i=0;i<n;i++)
	    arr[i] = new Friend(s1);
	
	int max[] = new int[3];
	Arrays.fill(max, Integer.MIN_VALUE);
	for(Friend f:arr){
	    for(int i=0;i<3;i++)
		max[i] = Math.max(max[i],f.cnt[i]);
	}

	ArrayList<String>[] ans = (ArrayList<String>[]) new ArrayList[3];
	for(int i=0;i<3;i++)ans[i] = new ArrayList<>();
	
	
	for(Friend f:arr){
	    for(int i=0;i<3;i++)
		if(f.cnt[i] == max[i])
		    ans[i].add(f.name);
	}
	
	out.print("If you want to call a taxi, you should call: ");
	for(int i=0;i<ans[0].size();i++)
	    out.print( ans[0].get(i) + (i == ans[0].size()-1 ? "." : ", "));
	out.print("\nIf you want to order a pizza, you should call: ");
	for(int i=0;i<ans[1].size();i++)
	    out.print(ans[1].get(i) + (i == ans[1].size()-1 ? "." : ", "));
	out.print("\nIf you want to go to a cafe with a wonderful girl, you should call: ");
	for(int i=0;i<ans[2].size();i++)
	    out.print(ans[2].get(i) + (i == ans[2].size()-1 ?"." : ", "));
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