import java.util.*;
import java.io.*;

public class DividebyThree {

    /************************ SOLUTION STARTS HERE ***********************/

    private static void solve(FastScanner s1, PrintWriter out) {

        char num[] = s1.nextLine().toCharArray();
        int sum = 0;
        for (char ch : num)
            sum += ch - '0';

        sum %= 3;
        if (sum == 0)
            out.println(new String(num));
        else {
            int freq[] = new int[3];
            for (char ch : num)
                freq[(ch - '0') % 3]++;

            int max = 0;
            String answer = null;

            if (freq[sum] >= 1) {
                char replace = 0;
                for (int i = num.length - 1; i >= 0; i--)
                    if ((num[i] - '0') % 3 == sum) {
                        replace = num[i];
                        num[i] = 0;
                        break;
                    }
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < num.length; i++)
                    if (num[i] > 0)
                        sb.append(num[i]);

                if (sb.length() == 0) {
                    out.println(-1);
                    return;
                }

                int start = 0;
                while (start < sb.length() && sb.charAt(start) == '0')
                    start++;

                String ans = sb.substring(start);
                ans = ans.isEmpty() ? "0" : ans;

                if (ans.length() > max) {
                    max = ans.length();
                    answer = ans;
                }
                for (int i = 0; i < num.length; i++)
                    if (num[i] == 0) {
                        num[i] = replace;
                        break;
                    }
            }
            if (freq[3 - sum] >= 2) {
                int temp = 2;
                for (int i = num.length - 1; i >= 0; i--)
                    if ((num[i] - '0') % 3 == 3 - sum && temp > 0) {
                        temp--;
                        num[i] = 0;
                    }

                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < num.length; i++)
                    if (num[i] > 0)
                        sb.append(num[i]);

                if (sb.length() == 0) {
                    out.println(-1);
                    return;
                }

                int start = 0;
                while (start < sb.length() && sb.charAt(start) == '0')
                    start++;

                String ans = sb.substring(start);
                ans = ans.isEmpty() ? "0" : ans;
                if (ans.length() > max) {
                    max = ans.length();
                    answer = ans;
                }

            }

            out.println(answer == null ? -1 : answer);
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
		int[]  nextIntArray(int n)         {int[] a= new int[n];   int i=0;while(i<n){a[i++]=nextInt();}  return a;}
		long[] nextLongArray(int n)        {long[]a= new long[n];  int i=0;while(i<n){a[i++]=nextLong();} return a;}	
		int[]  nextIntArrayOneBased(int n) {int[] a= new int[n+1]; int i=1;while(i<=n){a[i++]=nextInt();} return a;}	    	
		long[] nextLongArrayOneBased(int n){long[]a= new long[n+1];int i=1;while(i<=n){a[i++]=nextLong();}return a;}	    	
		void   close(){try{reader.close();}catch(IOException e){e.printStackTrace();}}				
	}
	
	/************************ TEMPLATE ENDS HERE ************************/
}