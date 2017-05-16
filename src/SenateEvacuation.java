import java.util.*;
import java.io.*;
public class SenateEvacuation
{


    /************************ SOLUTION STARTS HERE ***********************/



    private static void solve(FastScanner s1, PrintWriter out){

	int t = 0 , max = s1.nextInt();
	while( ++t <= max){

	    TreeMap<Integer,ArrayList<Character>> map = new TreeMap<>();
	    int N = s1.nextInt();
	    int sum = 0;
	    for(int i=0;i<N;i++){
		int f = s1.nextInt();
		sum += f;
		ArrayList<Character> arl = map.get(f);
		if(arl == null)
		    arl = new ArrayList<>();
		arl.add((char)('A' + i));
		map.put(f, arl);
	    }
	    out.print("Case #"+t+": ");
	    while(!map.isEmpty()){

		Map.Entry<Integer,ArrayList<Character>> lastEntry = map.pollLastEntry();
		ArrayList<Character> last = lastEntry.getValue();
		int freq = lastEntry.getKey();

		if(freq > (sum / 2)){
		     throw new RuntimeException("WA at test " + t);
		}
		if(last.size() == 1){
		    int newFreq ;
		    if(freq % 2 == 0){
			out.print(last.get(0) + "" + last.get(0) + " ");
			newFreq = freq - 2;
			sum -= 2;
		    }
		    else {
			out.print(last.get(0) + " ");
			newFreq = freq - 1;
			sum -= 1;
		    }

		    if(newFreq > 0){
			ArrayList<Character> merge = map.get(newFreq);
			if(merge == null)
			    map.put(newFreq, last);
			else{
			    merge.add(last.get(0));
			    map.put(newFreq, merge);
			}
		    }			
		}
		else{
		    if(freq == 1 && last.size() == 3){
			out.print(last.get(0)+ " ");
			sum -= 1;
			int newFreq = freq - 1;
			char a = last.remove(0);
			if(last.size() > 0)
			    map.put(freq, last);
			if(newFreq != 0){
			    ArrayList<Character> merge = map.get(newFreq);
			    if(merge == null)
				merge = new ArrayList<>();
			    merge.add(a);
			    map.put(newFreq, merge);
			}
		    }
		    else{
			out.print(last.get(0) +"" + last.get(1)+ " ");
			sum -= 2;
			int newFreq = freq - 1;
			char a = last.remove(0);
			char b = last.remove(0);
			if(last.size() > 0)
			    map.put(freq, last);
			if(newFreq != 0){
			    ArrayList<Character> merge = map.get(newFreq);
			    if(merge == null)
				merge = new ArrayList<>();
			    merge.add(a);
			    merge.add(b);
			    map.put(newFreq, merge);
			}
		    }
		}
	    }
	    out.println();
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