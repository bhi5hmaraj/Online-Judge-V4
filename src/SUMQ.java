import java.util.*;
import java.io.*;
class SUMQ  {
    
    
    /************************ SOLUTION STARTS HERE ************************/
    

    static class Treap {
        /*
         * Based on https://sites.google.com/site/indy256/algo/treap_set
         * It is a max priority heap based treap
         */
        static class TreapNode {
            int cnt , size;
            long key , sum;
            int priority;
            TreapNode left , right;
            
            TreapNode(int key , int priority) {
                left = right = null;
                this.key = key;
                this.priority = priority;
                cnt = 1;
                size = 1;
                sum = key;
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
        private static long sum(TreapNode treap) {
            return treap == null ? 0 : treap.sum;
        }
        
        private static void update(TreapNode treap) {
            treap.size = treap.cnt + size(treap.left) + size(treap.right);
            treap.sum = (1L * treap.key * treap.cnt) + sum(treap.left) + sum(treap.right);
        }
        
        private Random rand;
        private TreapNode root;
        
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
            else if(x > treap.key && increaseIfFound(treap.right, x)) {
                treap.sum += x;
                treap.size++;
                return true;
            } else if (x < treap.key && increaseIfFound(treap.left, x)) {
                treap.sum += x;
                treap.size++;
                return true;
            } else if (x == treap.key) {
                treap.sum += x;
                treap.cnt++;
                treap.size++;
                return true;
            } else
                return false;
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
        
        private int countLess(TreapNode treap , long x) {
            return treap == null ? 0 :
                   x < treap.key ? countLess(treap.left, x) :
                   x > treap.key ? size(treap.left) + treap.cnt + countLess(treap.right, x) :
                                   size(treap.left) + treap.cnt;
        }
        
        public int countLess(long x) {
            return countLess(root , x);
        }
        
        private long prefixSum(TreapNode treap , long x) {
            return treap == null ? 0 :
                x < treap.key ? prefixSum(treap.left, x) :
                x > treap.key ? sum(treap.left) + (1L * treap.key * treap.cnt) + prefixSum(treap.right, x) :
                                sum(treap.left) + (1L * treap.key * treap.cnt);
        }
        
        public long prefixSum(long x) {
            return prefixSum(root, x);
        }
        
    }


    private static void solve() {
        
        int T = nextInt();
        long MOD = 1000000007;
        while(T-->0) {
            
            int P = nextInt();
            int Q = nextInt();
            int R = nextInt();
            
            Treap treapA = new Treap();
            Treap treapC = new Treap();
            
            while(P-->0) 
                treapA.insert(nextInt());
            long B[] = nextLongArray(Q);
            while(R-->0) 
                treapC.insert(nextInt());
           
            long sum = 0;
            Arrays.sort(B);
            
            for(long Y : B) {
                long m1 = (1L * Y * treapA.countLess(Y)) % MOD;
                long m2 = (m1 + treapA.prefixSum(Y)) % MOD;
                long m3 = (1L * Y * treapC.countLess(Y)) % MOD;
                long m4 = (m3 + treapC.prefixSum(Y)) % MOD;
                sum = (sum + ((m2 * m4) % MOD)) % MOD;
            }
            
            println(sum);
            
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