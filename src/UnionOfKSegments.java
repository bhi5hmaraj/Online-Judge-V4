import java.util.*;
import java.io.*;
public class UnionOfKSegments
{


    /************************ SOLUTION STARTS HERE ***********************/


    static class Pair implements Comparable<Pair>
    {
	int point,seg_ct,single;
	Pair(int point)
	{
	    this.point = point;
	    this.seg_ct = 0;
	    this.single = 0;
	}
	@Override
	public int compareTo(Pair that) {
	    return this.point - that.point;
	}
    }
    static class tuple
    {
	int start,end;
	tuple(int s,int e)
	{
	    start = s;
	    end = e;
	}
    }

    static void shuffleArray(Pair[] array) {
	Random random = new Random();
	for (int i = array.length - 1; i > 0; i--) {
	    int index = random.nextInt(i + 1);
	    Pair temp = array[index];
	    array[index] = array[i];
	    array[i] = temp;
	}
    }
    private static void solve(FastScanner s1, PrintWriter out){

	int n = s1.nextInt();
	int k = s1.nextInt();
	int[][] segment = new int[n][2];
	HashMap<Integer,Integer> set = new HashMap<>();
	//BitSet set = new BitSet();
	for(int i=0;i<n;i++)
	{
	    segment[i][0] = s1.nextInt();
	    segment[i][1] = s1.nextInt();
	    set.put(segment[i][0], i);
	    set.put(segment[i][1], i);

	}
	//out.println(filter);
	Pair arr[] = new Pair[set.size()];
	int ct = 0;
	//HashMap<Integer,Integer> map = new HashMap<>();
	for (Map.Entry<Integer,Integer> e : set.entrySet()) 
	{
	    arr[ct++] = new Pair(e.getKey());
	} 
	//shuffleArray(arr);
	Arrays.sort(arr);
	ct = 0;
	/*
	for(Pair p:arr)
	    map.put(p.point, ct++);
	 */
	for(Pair p : arr)
	{
	    set.replace(p.point, ct++);
	}


	Pair L = new Pair(-1);
	Pair R = new Pair(-1);
	for(int i=0;i<n;i++)
	{	  
	    /*
	    L.point = segment[i][0];
	    R.point = segment[i][1];
	    int L_index = Arrays.binarySearch(arr, L);
	    int R_index = Arrays.binarySearch(arr, R);		 
	     */
	    int L_index = set.get(segment[i][0]);
	    int R_index = set.get(segment[i][1]);
	    arr[L_index].seg_ct++;
	    arr[R_index].seg_ct--;
	    if(segment[i][0] == segment[i][1])
		arr[L_index].single++;
	}
	int cnt = 0;
	boolean started = false;
	//ArrayList<tuple> arl = new ArrayList<>();
	tuple arl[] = new tuple[n];
	int ptr = 0;
	for(int i=0;i<arr.length;i++)
	{
	    if(cnt < k && !started && cnt + arr[i].single >= k)
	    {
		//arl.add(new tuple(arr[i].point, arr[i].point));
		arl[ptr++] = new tuple(arr[i].point, arr[i].point);
		cnt += arr[i].seg_ct;
		continue;
	    }	
	    cnt += arr[i].seg_ct;
	    if(cnt >= k && !started)
	    {
		started = true;
		//arl.add(new tuple(arr[i].point, Integer.MAX_VALUE));
		arl[ptr++] = new tuple(arr[i].point, Integer.MAX_VALUE);
	    }
	    else if(cnt < k && started)
	    {
		started = false;
		//arl.get(arl.size() - 1).end = arr[i].point;
		arl[(ptr - 1)].end = arr[i].point;
	    }
	    else if(cnt < k && !started && cnt + arr[i].single >= k)
	    {
		//arl.add(new tuple(arr[i].point, arr[i].point));
		arl[ptr++] = new tuple(arr[i].point, arr[i].point);
	    }	
	}
	//out.println(arl.size());
	out.println(ptr);
	/*
	for(tuple t:arl)
	    out.println(t.start + " " + t.end);
	 */
	for(int i=0;i<ptr;i++)
	    out.println(arl[i].start + " " + arl[i].end);
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