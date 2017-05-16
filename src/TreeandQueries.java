import java.util.*;
import java.io.*;
public class TreeandQueries {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/    
    
    static ArrayList<Integer>[] adj;
    static ArrayList<int[]>[] queries;
    static HashMap<Integer , Integer>[] freq;
    static Treap[] freqOfFreq;
    static int size[];
    static int ans[];
    static int color[];
    
    static void dfs(int u , int par) {
        size[u] = 1;
        int bigChild = -1;
        int bigChildSize = 0;
        
        for(int v : adj[u])
            if(v != par) {
                dfs(v, u);
                size[u] += size[v];
                if(size[v] > bigChildSize) {
                    bigChildSize = size[v];
                    bigChild = v;
                }
            }
        
        
        if(bigChild > 0) {
            freq[u] = freq[bigChild];
            freqOfFreq[u] = freqOfFreq[bigChild];
            for(int v : adj[u])
                if(v != par && v != bigChild) {
                    for(Map.Entry<Integer, Integer> e : freq[v].entrySet()) {
                        int oldFreq = freq[u].getOrDefault(e.getKey() , 0);
                        freqOfFreq[u].remove(oldFreq);
                        freqOfFreq[u].insert(oldFreq + e.getValue());
                        freq[u].put(e.getKey(), oldFreq + e.getValue());
                    }
                }
            
            int oldFreq = freq[u].getOrDefault(color[u], 0);
            freqOfFreq[u].remove(oldFreq);
            freqOfFreq[u].insert(oldFreq + 1);
            freq[u].put(color[u], oldFreq + 1);
        }
        else {
            freq[u]       = new HashMap<>();
            freqOfFreq[u] = new Treap();
            freq[u].put(color[u], 1);
            freqOfFreq[u].insert(1);
        }
        
        for(int[] pair : queries[u])  
            ans[pair[1]] = freqOfFreq[u].countGreater(pair[0]);
        
    }
    
    @SuppressWarnings("unchecked")
    private static void solve() {
        
        int V = nextInt();
        int Q = nextInt();
        
        adj        = new ArrayList[V + 1];
        freqOfFreq = new Treap[V + 1];
        queries    = new ArrayList[V + 1];     // [k , query index]
        size       = new int[V + 1];
        ans        = new int[Q];
        freq       = new HashMap[V + 1];
        color      = new int[V + 1];
        
        color = nextIntArrayOneBased(V);
        
        for(int i=1;i<=V;i++) {
            adj[i] = new ArrayList<>();
            queries[i] = new ArrayList<>();
        }
        
        int E = V - 1;
        
        while(E-->0) {
            int u = nextInt();
            int v = nextInt();
            adj[u].add(v);
            adj[v].add(u);
        }
        
        for(int i=0;i<Q;i++)
            queries[nextInt()].add(new int[]{nextInt() , i});
        
        dfs(1, 0);
        
        for(int a : ans)
            println(a);
    }
    
    
    static class Treap {
        /*
         * Based on https://sites.google.com/site/indy256/algo/treap_set
         * It is a max priority heap based treap
         */
        static class TreapNode {
            int key , cnt , size ;
            int priority;
            TreapNode left , right;
            TreapNode(int key , int priority) {
                left = right = null;
                this.key = key;
                this.priority = priority;
                cnt = 1;
                size = 1;
            }
        }
        static class TreapNodePair {
            TreapNode left , right;
            TreapNodePair(TreapNode left , TreapNode right) {
                this.left = left;
                this.right = right;
            }
        }
        private static int size(TreapNode treap) {
            return treap == null ? 0 : treap.size;
        }
        
        private static void update(TreapNode treap) {
            treap.size = treap.cnt + size(treap.left) + size(treap.right);
        }
        
        private Random rand;
        private TreapNode root;
        // static final long SEED = 226366815112524829L; // For debugging
        
        Treap() {
            rand = new Random();
            root = null;
        }
        /*
         * All the elements in the left tree are <= x 
         */
        private TreapNodePair split(TreapNode treap , int x) {
            if(treap == null)
                return new TreapNodePair(null, null);
            else if(treap.key <= x) {   // No need to take care of left subtree now
                TreapNodePair rightSplit = split(treap.right, x);
                treap.right = rightSplit.left;
                update(treap);
                rightSplit.left = treap;
                return rightSplit;
            }
            else {
                TreapNodePair leftSplit = split(treap.left, x);
                treap.left = leftSplit.right;
                update(treap);
                leftSplit.right = treap;
                return leftSplit;
            }
        }
        
        private TreapNode merge(TreapNode leftTreap , TreapNode rightTreap) {
            if(leftTreap == null && rightTreap == null)
                return null;
            else if(leftTreap == null)
                return rightTreap;
            else if(rightTreap == null)
                return leftTreap;
            else {
                if(leftTreap.priority > rightTreap.priority) {
                    leftTreap.right = merge(leftTreap.right, rightTreap);
                    update(leftTreap);
                    return leftTreap;
                }
                else {
                    rightTreap.left = merge(leftTreap , rightTreap.left);
                    update(rightTreap);
                    return rightTreap;
                }
            }
        }
        /*
         * Increases the frequency of the key if found and returns true else false
         */
        private boolean increaseIfFound(TreapNode treap , int x) {
            if(treap == null)
                return false;
            else if(x > treap.key) {
                if(increaseIfFound(treap.right, x)) {
                    treap.size++;
                    return true;
                }
                else
                    return false;
            }
            else if(x < treap.key){
                if(increaseIfFound(treap.left, x)) {
                    treap.size++;
                    return true;
                }
                else
                    return false;
            }
            else {
                treap.cnt++;
                treap.size++;
                return true;
            }
        }
        
        private TreapNode insert(TreapNode treap , int x) {
            if(treap == null)
                return new TreapNode(x, rand.nextInt());
            else if(!increaseIfFound(treap, x)) { 
                TreapNodePair split = split(treap, x);
                TreapNode newNode = merge(merge(split.left, new TreapNode(x, rand.nextInt())), split.right);
                return newNode;
            }
            else
                return treap;
        }
        public void insert(int x) {
            root = insert(root, x);
        }
        
        private TreapNode remove(TreapNode treap , int x) {
            if(treap == null)
                return null;
            else if(x < treap.key) {
                treap.left = remove(treap.left, x);
                update(treap);
                return treap;
            }
            else if(x > treap.key) {
                treap.right = remove(treap.right, x);
                update(treap);
                return treap;
            }
            else {
                if(treap.cnt > 1) {
                    treap.cnt--;
                    treap.size--;
                }
                else
                    treap = merge(treap.left, treap.right);
                
                return treap;
            }
        }
        
        public void remove(int x) {
            root = remove(root, x);
        }

        /*
         * Counts the numbers greater than or equal to x
         */
        private int countGreater(TreapNode treap , int x) {
            return treap == null ? 0 :
                x < treap.key ? countGreater(treap.left,  x) + treap.cnt + size(treap.right):
                x > treap.key ? countGreater(treap.right, x) :
                                size(treap.right) + treap.cnt;
        }
        public int countGreater(int x) {
            return countGreater(root , x);
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