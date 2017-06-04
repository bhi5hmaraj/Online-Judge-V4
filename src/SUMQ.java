import java.util.*;

import helper.Treap.TreapNode;
import helper.Treap.TreapNodePair;

import java.io.*;
class SUMQ  {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static class MM {       // MM (Modular Math) class 
        static final long mod = (long) (1e9) + 7; // Default
        static long sub(long a, long b) {return (a - b  + mod) % mod;}
        static long mul(long a, long b) {return ((a % mod) * (b % mod)) % mod;}
        static long add(long a, long b) {return (a + b) % mod;}
        static long div(long a, long b) {return mul(a, modInverse(b));}
        static long modInverse(long n)  {return modPow(n, mod - 2);} // Fermat's little theorem
        static long modPow(long a , long b) {
            long pow = 1;
            while(b > 0) {
                if((b & 1L) == 1)
                    pow = ((pow * a) % mod);

                a = ((a * a) % (mod));
                b >>= 1;
            }
            return pow;
        }
    }

    static class Treap {
        /*
         * Based on https://sites.google.com/site/indy256/algo/treap_set
         * It is a max priority heap based treap
         */
        // static final long SEED = 226366815112524829L;
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
            @Override
            public String toString() {
                return String.format("[key = %d prio = %d cnt = %d sz = %d]", 
                                        key , priority , cnt , size);
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
                treap.size++;
                return true;
            } else if (x < treap.key && increaseIfFound(treap.left, x)) {
                treap.size++;
                return true;
            } else if (x == treap.key) {
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
        
        private int getFrequency(TreapNode treap , int x) {
            return treap == null ? 0 : 
                   x < treap.key ? getFrequency(treap.left, x)  :
                   x > treap.key ? getFrequency(treap.right, x) :
                   treap.cnt;
        }
        
        public int getFrequency(int x) {
            return getFrequency(root, x);
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
         * Counts the numbers less than or equal to x
         */
        
        private int countLess(TreapNode treap , int x) {
            return treap == null ? 0 :
                   x < treap.key ? countLess(treap.left, x) :
                   x > treap.key ? size(treap.left) + treap.cnt + countLess(treap.right, x) :
                                   size(treap.left) + treap.cnt;
        }
        
        public int countLess(int x) {
            return countLess(root , x);
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

        private StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb, TreapNode treap) {
            if (treap == null) {
                sb.append("Tree Empty\n");
                return sb;
            }
            if (treap.right != null) {
                toString(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb, treap.right);
            }
            sb.append(prefix).append(isTail ? "└── " : "┌── ").append(treap).append("\n");
            if (treap.left != null) {
                toString(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb, treap.left);
            }
            return sb;
        }

        @Override
        public String toString() {
            return this.toString(new StringBuilder(), true, new StringBuilder(), root).toString();
        }
    }

    private static void solve() {
        
        int T = nextInt();
        while(T-->0) {
            
            int P = nextInt();
            int Q = nextInt();
            int R = nextInt();
            
            long A[] = nextLongArray(P);
            long B[] = nextLongArray(Q);
            long C[] = nextLongArray(R);
            
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