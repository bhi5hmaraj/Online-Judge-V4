import java.util.*;
import java.io.*;
public class HackerRankProb
{


    /************************ SOLUTION STARTS HERE ***********************/

    static class Student implements Comparable<Student>{
	private int token;
	private String fname;
	private double cgpa;
	public Student(int id, String fname, double cgpa) {
	    super();
	    this.token = id;
	    this.fname = fname;
	    this.cgpa = cgpa;
	}
	public int getToken() {
	    return token;
	}
	public String getFname() {
	    return fname;
	}
	public double getCgpa() {
	    return cgpa;
	}
	@Override
	public int compareTo(Student that) {
	    
	    if(this.cgpa != that.cgpa)
		return (this.cgpa < that.cgpa)? 1 : -1 ; 
	    else
	    {
		int cmp2 = this.fname.compareTo(that.fname);
		if(cmp2 != 0)
		    return cmp2;
		else
		    return this.token - that.token;
	    }
	}
	@Override
	public String toString() {
	    return fname + ", "+cgpa + ", "+ token;
	}
    }

    private static void solve(FastScanner s1, PrintWriter out){

	TreeSet<Student> set = new TreeSet<>();
	int query = s1.nextInt();
	while(query-->0)
	{
	    String command = s1.next();
	    if(command.equals("ENTER"))
	    {
		String name = s1.next();
		double cgpa = s1.nextDouble();
		int token = s1.nextInt();
		set.add(new Student(token, name, cgpa));
	    }
	    else
		set.pollFirst();	    
	}
	
	if(set.isEmpty())
	    out.println("EMPTY");
	else
	{
	    for(Student stu:set)
		out.println(stu.fname);
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