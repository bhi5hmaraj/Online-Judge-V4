import java.util.*;
import java.io.*;
public class PolycarpPhonebook {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    

    static class Trie {
        static class Node {
            int size;
            Node adj[];
            Node() {
                size = 1;   // Take this into consideration
                adj = new Node[10];
            }
        }
        Node root;
        Trie(){
            root = new Node();
        }

        void insert(int num , int len){
            root = insert(root, num , len);
        }
        
        int size(Node n) {
            return n == null ? 0 : n.size;
        }
        
        Node insert(Node curr , int num , int len){
            if(curr == null)
                curr = new Node();
            curr.size++;
            if(len > 0) {
                int d = num % 10;
                curr.adj[d] = insert(curr.adj[d], num / 10, len - 1);
            }
            return curr;
        }
        
        void remove(int num , int len){
            remove(root, num, len);
        }
        
        void remove(Node curr , int num , int len){
            if(len > 0)
                remove(curr.adj[num % 10], num / 10, len - 1);
            curr.size--;
        }
        
        int search(Node curr , int num) {
            return curr == null ? 0 : 1 + search(curr.adj[num % 10], num / 10);
        }
    }
    
    
    private static void solve() {
        
        
        
        
        
        
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