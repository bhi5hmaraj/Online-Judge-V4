import java.util.*;
import java.io.*;
public class MonkAndIQ
{

    
    /************************ SOLUTION STARTS HERE ***********************/

    static class Course implements Comparable<Course>
    {
	long cnt,IQ[],x,z;
	int index;
	Course(long iq,int index)
	{	    	    
	    this(index);
	    addStudent(iq);
	}
	Course(int index)
	{
	    IQ  = new long[2];
	    x   = 0;
	    cnt = 0;
	    z   = 0;
	    this.index = index;
	}
	void addStudent(long iq)
	{
	    if(IQ[0] == 0)
	    {
		x += iq;
		IQ[0] = iq;
	    }
	    else if(IQ[1] == 0)
	    {
		x += iq;
		IQ[1] = iq;
	    }
	    else
	    {
		x -= IQ[0];
		IQ[0] = IQ[1];
		IQ[1] = iq;
		x += iq;
	    }
	    cnt++;
	    z = x * cnt;
	}
	@Override
	public int compareTo(Course o) {
	    int comp = Long.compare(this.z, o.z);
	    return comp != 0 ? comp : this.index - o.index;
	}
    }
    
    private static void solve(FastScanner s1, PrintWriter out){

	int C = s1.nextInt();
	int P = s1.nextInt();
	int N = s1.nextInt();
	
	PriorityQueue<Course> pq = new PriorityQueue<>(C);
	int init_student[]       = s1.nextIntArrayOneBased(N);
	int friends[]            = s1.nextIntArrayOneBased(P);
	
	for(int i=1;i<=C;i++)
	{
	    if(i <= N)
		pq.add(new Course(init_student[i], i));
	    else
		pq.add(new Course(i));
	}
	for(int i=1;i<=P;i++)
	{
	    Course curr = pq.remove();
	    out.print(curr.index + " ");
	    curr.addStudent(friends[i]);
	    pq.add(curr);
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