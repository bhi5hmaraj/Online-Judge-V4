import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.io.*;
public class EpidemicinMonstropolis
{


	/************************ SOLUTION STARTS HERE ***********************/



	private static void solve(FastScanner s1, PrintWriter out){

		int N = s1.nextInt();
		ArrayList<Integer> initial = new ArrayList<>(Arrays.stream(s1.nextIntArray(N)).boxed().collect(Collectors.toList())); // Get input
		int K = s1.nextInt();
		int finalQueue[] = s1.nextIntArray(K);

		StringBuilder sb = new StringBuilder();
		int start , end;
		for(int i=0;i<K;i++) {
			int sum = finalQueue[i];
			start = end = i;
			while(end < initial.size() && sum != 0) 
				sum -= initial.get(end++);

			if(sum != 0) {
				out.println("NO");
				return;
			}
			else {
				end--;
				int size = end - start + 1;
				if(size == 1) continue;
				int max = initial.subList(start, end + 1).stream().max(Integer::compare).get();  // Get the max in the subList from [start , end]
				int ptr = -1;
				int dir = -1; 
				for(int idx : IntStream.rangeClosed(start, end).filter($ -> initial.get($).intValue() == max).toArray()) {  // Get all the occurences of max in the subList
					if((idx > start && initial.get(idx) > initial.get(idx - 1))){
						dir = -1;
						ptr = idx;
						break;
					}
					else if((idx < end && initial.get(idx) > initial.get(idx + 1))){  // Check wether the left or right is not max
						dir = 1;
						ptr = idx;
						break;
					}
				}

				if(ptr == -1) {  // Case when all the items are max in the subList
					out.println("NO");
					return;
				}
				else {
					// 1 is move Right and -1 is move left , if ptr is end or if there is another max to the right then we can't move right 
					while(size != 1) {
						if(ptr == start) // We can't move left
							dir = 1;
						else if(ptr == end) // We can't move right
							dir = -1;

						end--; // After each deletion end decreases

						sb.append((ptr + 1) + (dir == -1 ? " L" : " R") + "\n"); // Append a move
						initial.set(ptr, initial.get(ptr) + initial.get(ptr + dir));
						initial.remove(ptr + dir);
						ptr += (dir == 1) ? 0 : -1; // Move the ptr to left if he eats the left
						size = end - start + 1; // Update the size of the subList
					}
				}
			}
		}

		if(initial.size() == K) { // Cross check wether both the final size is K
			out.println("YES");
			out.println(sb); 
		}
		else
			out.println("NO");
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