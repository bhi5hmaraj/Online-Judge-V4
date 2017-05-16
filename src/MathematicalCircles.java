import java.util.*;
import java.awt.Point;
import java.io.*;
class MathematicalCircles
{

    
    /************************ SOLUTION STARTS HERE ***********************/

    static class Circle
    {
	Point p;
	int radius;
	Integer index;
	Circle(int x,int y,int r,int index)
	{
	    p = new Point(x, y);
	    radius = r;
	    this.index = index;
	}
	@Override
	public int hashCode() {
	   // return p.hashCode();
	    return index.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
	    Circle c = (Circle)obj;
	    return this.p.equals(c.p) && this.radius == c.radius && this.index.intValue() == c.index.intValue();
	}
	boolean intersects(Circle that)
	{
	    double dist = this.p.distanceSq(that.p);
	    return dist >= Math.pow(this.radius-that.radius, 2) && dist <= Math.pow(this.radius+that.radius, 2); 
	}
	@Override
	public String toString() {
	    return "p = "+p+" rad "+radius;
	}
    }
    
    private static void remove(HashMap<Circle,HashSet<Circle>> mp, Circle c)
    {
	HashSet<Circle> set = mp.remove(c);
	for(Circle curr:set)
	{
	    HashSet<Circle> other = mp.get(curr);
	    other.remove(c);
	    if(other.size() == 0)
		mp.remove(curr);
	    else
		mp.put(curr, other);
	}
    }
    
    private static void solve(FastScanner s1, PrintWriter out){

	HashMap<Circle,HashSet<Circle>> mp = new HashMap<>();
	int N = s1.nextInt();
	Circle arr[] = new Circle[N];
	for(int i=0;i<N;i++)
	    arr[i] = new Circle(s1.nextInt(), s1.nextInt(), s1.nextInt(),i);
	
	for(int i=0;i<N;i++)
	{
	    HashSet<Circle> set = mp.get(arr[i]);
	    for(int j=0;j<N;j++)
	    {
		if(i != j && arr[i].intersects(arr[j]))
		{
		    if(set == null)
			set = new HashSet<>();
		    set.add(arr[j]);
		    mp.put(arr[i], set);
		}
	    }
	}
	
	int cnt = 0;
	while(!mp.isEmpty())
	{
	    Circle toRemove = null;
	    int max = Integer.MIN_VALUE;
	    for (Map.Entry<Circle,HashSet<Circle>> e : mp.entrySet()) 
	    {
		if(e.getValue().size() > max)
		{
		    max = e.getValue().size();
		    toRemove = e.getKey();
		}
	    }
	    remove(mp, toRemove);
	    cnt++;
	}
	out.println(cnt);
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