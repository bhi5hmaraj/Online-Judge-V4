import java.util.*;
import java.io.*;
public class NewYearandRoads {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int maxGap(ArrayList<Integer> arl) {
        int max = 0;
        for(int i = 0; i < arl.size() - 1; i++)
            max = Math.max(max , arl.get(i + 1) - arl.get(i));
        
        return max;
    }
    
    private static void solve() {
        
        ArrayList<ArrayList<Integer>> reds = new ArrayList<>();
        ArrayList<ArrayList<Integer>> blues = new ArrayList<>();
        
        ArrayList<Integer> greens = new ArrayList<>();
        
        int minG = Integer.MAX_VALUE;
        int maxG = Integer.MIN_VALUE;
        
        int n = nextInt();
        long cost = 0;
        ArrayList<Integer> tempB = new ArrayList<>();
        ArrayList<Integer> tempR = new ArrayList<>();
        
        while(n-->0) {
            int p = nextInt();
            switch (nextChar()) {
            case 'G':
                minG = Math.min(minG , p);
                maxG = Math.max(maxG , p);
                blues.add(tempB);
                reds.add(tempR);
                greens.add(p);
                tempB = new ArrayList<>();
                tempR = new ArrayList<>();
                break;
            case 'B':
                tempB.add(p);
                break;
            case 'R':
                tempR.add(p);
                break;
            }
        }
        
        blues.add(tempB);
        reds.add(tempR);
        
        
        if(greens.isEmpty()) { 
            ArrayList<Integer> b = blues.get(0);
            ArrayList<Integer> r = reds.get(0);
            cost += b.isEmpty() ? 0 : b.get(b.size() - 1) - b.get(0);
            cost += r.isEmpty() ? 0 : r.get(r.size() - 1) - r.get(0);
        } else {
            
            // segment --1--- G -- ... --- G ---n---
            // first segment 
            cost += blues.get(0).isEmpty() ? 0 : greens.get(0) - blues.get(0).get(0);
            cost += reds.get(0).isEmpty() ? 0 : greens.get(0) - reds.get(0).get(0);
            
            // last segment
            ArrayList<Integer> lastB = blues.get(blues.size() - 1);
            ArrayList<Integer> lastR = reds.get(reds.size() - 1);
            cost += lastR.isEmpty() ? 0 : lastR.get(lastR.size() - 1) - greens.get(greens.size() - 1);
            cost += lastB.isEmpty() ? 0 : lastB.get(lastB.size() - 1) - greens.get(greens.size() - 1);
            
            // middle segments
            for(int i = 1; i < greens.size(); i++) {
                int prev = greens.get(i - 1);
                int next = greens.get(i);
                reds.get(i).add(0, prev);
                reds.get(i).add(next);
                blues.get(i).add(0, prev);
                blues.get(i).add(next);
                long segLen = next - prev;
                long option1 = 2 * segLen;  // 2 parallel paths 1 using reds and other using blues
                long option2 = segLen + (segLen - maxGap(reds.get(i))) + (segLen - maxGap(blues.get(i)));
                cost += Math.min(option1 , option2);
            }
        }
        
        println(cost);
    }
    
    
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/
    
    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
        st     = null;
        solve();
        reader.close();
        writer.close();
    }
    
    static BufferedReader reader;
    static PrintWriter    writer;
    static StringTokenizer st;
    
    static String next()
    {while(st == null || !st.hasMoreTokens()){try{String line = reader.readLine();if(line == null){return null;}            
    st = new StringTokenizer(line);}catch (Exception e){throw new RuntimeException();}}return st.nextToken();}
    static String nextLine()  {String s=null;try{s=reader.readLine();}catch(IOException e){e.printStackTrace();}return s;}             
    static int    nextInt()   {return Integer.parseInt(next());}
    static long   nextLong()  {return Long.parseLong(next());}     
    static double nextDouble(){return Double.parseDouble(next());}
    static char   nextChar()  {return next().charAt(0);}
    static int[]  nextIntArray(int n)         {int[] a= new int[n];   int i=0;while(i<n){a[i++]=nextInt();}  return a;}
    static long[] nextLongArray(int n)        {long[]a= new long[n];  int i=0;while(i<n){a[i++]=nextLong();} return a;}    
    static int[]  nextIntArrayOneBased(int n) {int[] a= new int[n+1]; int i=1;while(i<=n){a[i++]=nextInt();} return a;}            
    static long[] nextLongArrayOneBased(int n){long[]a= new long[n+1];int i=1;while(i<=n){a[i++]=nextLong();}return a;}            
    static void   print(Object o)  { writer.print(o);  }
    static void   println(Object o){ writer.println(o);}
    
    /************************ TEMPLATE ENDS HERE ************************/
    
}