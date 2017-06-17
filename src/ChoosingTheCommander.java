import java.util.*;
import java.io.*;
public class ChoosingTheCommander {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static class BinaryTrie {

        static class Node {
            int size;
            Node zero;
            Node one;
            Node() {
                size = 1;
            }
        }
        Node root;
        static final int offset = 27;  //Maximum index of set bit
        BinaryTrie(){
            root = new Node();
        }

        void insert(int num){
            root = insert(root, num, offset);
        }
        
        int size(Node n) {
            return n == null ? 0 : n.size;
        }
        
        Node insert(Node curr , int num , int idx){
            if(idx < 0) {
                if(curr != null)
                    curr.size++;
                else
                    curr = new Node();
                return curr;
            }
            else{
                if(curr == null)
                    curr = new Node();
                if((num & (1 << idx)) == 0)
                    curr.zero = insert(curr.zero, num, idx - 1);
                else
                    curr.one = insert(curr.one, num, idx - 1);
                
                curr.size = size(curr.zero) + size(curr.one);
                return curr;
            }
        }
        
        void remove(int num){
            remove(root, null, num, offset);
        }
        
        void remove(Node curr , Node parent , int num , int idx){
            if(idx >= 0){
                if((num & (1 << idx)) == 0)
                    remove(curr.zero, curr, num, idx - 1);
                else
                    remove(curr.one, curr, num, idx - 1);
            }
            curr.size--;
        }
        
        int count(Node curr , int a , int b , int idx) {
            if(idx < 0 || curr == null)
                return 0;
            else if((a & (1 << idx)) == 0 && (b & (1 << idx)) == 0)
                return count(curr.zero, a, b, idx - 1);
            else if((a & (1 << idx)) == 0 && (b & (1 << idx)) != 0)
                return size(curr.zero) + count(curr.one, a, b, idx - 1);
            else if((a & (1 << idx)) != 0 && (b & (1 << idx)) == 0)
                return count(curr.one, a, b, idx - 1);
            else
                return size(curr.one) + count(curr.zero, a, b, idx - 1);
        }
        
        int count(int a , int b) {
            return count(root, a, b, offset);
        }

    }
    

    
    private static void solve() {
        
        int Q = nextInt();
        BinaryTrie trie = new BinaryTrie();
        while(Q-->0) {
            switch(nextInt()) {
            case 1:
                trie.insert(nextInt());
                break;
            case 2:
                trie.remove(nextInt());
                break;
            case 3:
                println(trie.count(nextInt(), nextInt()));
                break;
            }
        }
        
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