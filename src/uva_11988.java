import java.util.*;
import java.io.*;
public class uva_11988
{


	/************************ SOLUTION STARTS HERE ***********************/

	private static void solve(FastScanner s1, PrintWriter out){

		String in ;
		while((in = s1.nextLine()) /*!= null*/ .length() != 0)
		{
			in = in.trim();
			StringBuilder sb = new StringBuilder();
			LinkedList<StringBuilder> list = new LinkedList<>();
			boolean isLast = true;
			for(int i=0,len=in.length();i<len;i++)
			{
				char ch = in.charAt(i);

				if(ch == '[' || ch == ']')
				{
					if(sb.length() != 0)
					{
						if(isLast)
							list.addLast(sb);
						else
							list.addFirst(sb);
					}
					if(ch == '[')
						isLast = false;
					else
						isLast = true;

					sb = new StringBuilder();
					continue;
				}
				sb.append(ch);
			}

			if(sb.length() != 0)
			{
				if(isLast)
					list.addLast(sb);
				else
					list.addFirst(sb);
			}
			for(StringBuilder s:list)
				out.print(s.toString());

			out.println();
		}

	}
	

	
	private static void solve2(/*FastScanner s1, */PrintWriter out){

		String line;
		Scanner s1 = new Scanner(System.in);
		
		while(s1.hasNext()) {
			line = s1.nextLine();
			StringTokenizer st = new StringTokenizer(line, "[|]");
			ArrayDeque<String> deque = new ArrayDeque<>();

			int ptr = 0;
			while(ptr < line.length()) {
				boolean addLast = true;
				while(ptr < line.length() && (line.charAt(ptr) == '[' || line.charAt(ptr) == ']'))
					addLast = line.charAt(ptr++) == ']';
				if(st.hasMoreTokens()) {
					if(addLast)
						deque.addLast(st.nextToken()); 
					else
						deque.addFirst(st.nextToken());
				}
				while(ptr < line.length() && !(line.charAt(ptr) == '[' || line.charAt(ptr) == ']'))
					ptr++;
			}

			for(String s : deque) out.print(s);
			out.println();
		}
		s1.close();
	}

	/************************ SOLUTION ENDS HERE ************************/





	/************************ TEMPLATE STARTS HERE *********************/

	public static void main(String []args) throws IOException {
		FastScanner in  = new FastScanner(System.in);
		PrintWriter out = 
				new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false); 
		// solve2(in, out);
		solve2(out);
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
		int[]  nextIntArray(int n)         {int[] a= new int[n];   int i=0;while(i<n){a[i++]=nextInt();}  return a;}
		long[] nextLongArray(int n)        {long[]a= new long[n];  int i=0;while(i<n){a[i++]=nextLong();} return a;}	
		int[]  nextIntArrayOneBased(int n) {int[] a= new int[n+1]; int i=1;while(i<=n){a[i++]=nextInt();} return a;}	    	
		long[] nextLongArrayOneBased(int n){long[]a= new long[n+1];int i=1;while(i<=n){a[i++]=nextLong();}return a;}	    	
		void   close(){try{reader.close();}catch(IOException e){e.printStackTrace();}}				
	}

	/************************ TEMPLATE ENDS HERE ************************/
}