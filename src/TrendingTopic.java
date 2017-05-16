import java.util.*;
import java.io.*;
class TrendingTopic
{


    /************************ SOLUTION STARTS HERE ***********************/



    private static void solve(FastScanner s1, PrintWriter out){

	String read = null;
	HashMap<String,Integer> freq = new HashMap<>();
	TreeMap<Integer,TreeSet<String>> map = new TreeMap<>();
	ArrayList<ArrayList<String>> words = new ArrayList<>();

	while((read = s1.nextLine()) != null)
	{

	    if(read.equals("<text>"))
	    {

		ArrayList<String> arl = new ArrayList<>();
		while(!(read = s1.next()).equals("</text>"))
		{
		    if(read.length() >= 4)
		    {
			arl.add(read);
			if(!freq.containsKey(read))
			{
			    freq.put(read, 1);
			    TreeSet<String> set = map.get(1);
			    if(set == null) set = new TreeSet<>();
			    set.add(read);
			    map.put(1, set);
			}
			else
			{
			    int oldFreq = freq.get(read);
			    int newFreq = oldFreq + 1;
			    freq.put(read, newFreq);
			    TreeSet<String> set = map.get(oldFreq);
			    set.remove(read);
			    if(set.size() == 0)map.remove(oldFreq);
			    set = map.get(newFreq);
			    if(set == null)set = new TreeSet<>();
			    set.add(read);
			    map.put(newFreq, set);
			}
		    }
		}

		words.add(arl);
		if(words.size() > 7)
		{
		    for(String s:words.get(words.size() - 8))
		    {
			int oldFreq = freq.get(s);
			if(oldFreq == 1)
			    freq.remove(s);
			else
			    freq.put(s, oldFreq-1);
			int newFreq = oldFreq - 1;
			TreeSet<String> set = map.get(oldFreq);
			set.remove(s);
			if(set.size() == 0)map.remove(oldFreq);

			if(newFreq != 0)
			{
			    set = map.get(newFreq);
			    if(set == null) set = new TreeSet<>();
			    set.add(s);
			    map.put(newFreq, set);
			}
		    }
		}

	    }
	    else if(read.length() > 1)
	    {
		int K = Integer.parseInt(read.split(" ")[1]);
		int i = 1;
		out.println("<top "+K+">");
		for(Map.Entry<Integer, TreeSet<String>> e = map.lastEntry();i <= Math.min(K,map.size());i++,e = map.lowerEntry(e.getKey()))
		{
		    for(String s : e.getValue())
			out.println(s + " " + e.getKey());
		}
		out.println("</top>");
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