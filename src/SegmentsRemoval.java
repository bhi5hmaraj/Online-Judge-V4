import java.util.*;
import java.io.*;
public class SegmentsRemoval {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static class Node implements Comparable<Node> {
        Node left , right;
        int index , key , freq;
        public Node(int id , int k , int f) {
            index = id;
            key = k;
            freq = f;
        }
        
        @Override
        public int compareTo(Node o) {
            if(freq != o.freq)
                return freq - o.freq;
            else
                return o.index - index;
        }
        
        static Node combine(Node a , Node b) {
            Node newNode = new Node(Math.min(a.index , b.index), a.key, a.freq + b.freq);
            newNode.left = a.left;
            newNode.right = b.right;
            if(a.left != null)
                a.left.right = newNode;
            if(b.right != null)
                b.right.left = newNode;
            return newNode;
        }
        
        @Override
        public String toString() {
            return String.format("[id = %d key = %d freq = %d]", index , key , freq);
        }
    }
    
    private static void solve() {
        
        int n = nextInt();
        int arr[] = nextIntArray(n);
        
        Node last = null;
        TreeSet<Node> set = new TreeSet<>();
        
        for(int i = 0; i < n; ) {
            int k = arr[i];
            int f = 0;
            while(i < n && arr[i] == k) {
                i++;
                f++;
            }
            Node newNode = new Node(i, k, f);
            if(last != null) {
                last.right = newNode;
                newNode.left = last;
            }
            last = newNode;
            set.add(newNode);
        }
        
        int time = 0;
        while(!set.isEmpty()) {
            Node toRem = set.last();
            set.remove(toRem);
            if(toRem.left != null && toRem.right != null && toRem.left.key == toRem.right.key) {
                set.remove(toRem.left);
                set.remove(toRem.right);
                set.add(Node.combine(toRem.left, toRem.right));
            }
            else {
                if(toRem.left != null)
                    toRem.left.right = toRem.right;
                if(toRem.right != null) 
                    toRem.right.left = toRem.left;
            }
            
            time++;
        }
        
        println(time);
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