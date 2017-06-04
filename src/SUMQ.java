import java.util.*;
import java.io.*;
class SUMQ  {
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static class MM {       // MM (Modular Math) class 
        static final long mod = (long) 1000000007; // Default
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
//         static final long SEED = 226366815112524829L;
        static class TreapNode {
            int cnt , size;
            long key , sum;
            long priority;
            TreapNode left , right;
            
            TreapNode(int key , long priority) {
                left = right = null;
                this.key = key;
                this.priority = priority;
                cnt = 1;
                size = 1;
                sum = key;
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


    static class AVLTree {
        static class Node {
            long key;
            long sum;
            int height;
            int cnt;
            int size;
            Node left, right, parent;

            Node(long key) {
                this.key = key;
                height = 1;
                sum = key;
                cnt = 1;
                size = 1;
                left = right = parent = null;
            }

            @Override
            public String toString() {
                return "[key = " + key + " s = " +sum + "cnt = "+cnt+" sz = "+size+ "]" + " P = " + (parent != null ? parent.key : "null")
                        + " L = " + (left != null ? left.key : "null") + " R = " + (right != null ? right.key : "null");
            }
        }

        public Node root;

        public AVLTree() {
            root = null;
        }

        public int height(Node n) {
            return n == null ? 0 : n.height;
        }

        public void add(long key) {
            root = add(key, root, null);
            Node N = search(root, key);
            root = rebalance(N);
        }

        public boolean find(long key) {
            return search(root, key) != null;
        }

        public long sum(Node n) {
            return n == null ? 0 : n.sum;
        }
        public int size(Node n){
            return n == null ? 0 : n.size;
        }
        public void adjustHeight(Node N) {
            N.height = 1 + Math.max(height(N.left), height(N.right));
            if (N.left != null)
                N.left.parent = N;
            if (N.right != null)
                N.right.parent = N;
            adjustSum(N); // Small hack , so that I need not call it explicitly!!
            adjustSize(N);
        }

        public void adjustSum(Node N) {
            N.sum = (N.key * (long)N.cnt) + sum(N.left) + sum(N.right);
        }
        public void adjustSize(Node N){
            N.size = N.cnt + size(N.left) + size(N.right);
        }
        public Node rotateRight(Node N) {
            Node oldPar = N.parent;
            Node newN = N.left;
            N.left = newN.right;
            if (newN != null && newN.right != null && newN.right.parent != null)
                newN.right.parent = N;
            adjustHeight(N);
            newN.parent = oldPar;
            newN.right = N;
            adjustHeight(newN);
            N.parent = newN;
            if (oldPar != null) {
                if (oldPar.left == N)
                    oldPar.left = newN;
                else
                    oldPar.right = newN;

                adjustHeight(oldPar);
            }
            return newN;
        }

        public Node rotateLeft(Node N) {
            Node oldPar = N.parent;
            Node newN = N.right;
            N.right = newN.left;
            if (newN != null && newN.left != null && newN.left.parent != null)
                newN.left.parent = N;
            adjustHeight(N);
            newN.parent = oldPar;
            newN.left = N;
            N.parent = newN;
            adjustHeight(newN);
            if (oldPar != null) {
                if (oldPar.left == N)
                    oldPar.left = newN;
                else
                    oldPar.right = newN;

                adjustHeight(oldPar);
            }
            return newN;
        }

        public Node search(Node root, long key) {
            if (root != null)
                return ((root.key == key) ? root : (key < root.key ? search(root.left, key) : search(root.right, key)));
            else
                return null;
        }

        public long prefixSum(Node root, long key) {
            if (root != null) {
                if (root.key == key)
                    return sum(root.left) + (1L * root.key * root.cnt);
                else if (key < root.key)
                    return prefixSum(root.left, key);
                else
                    return (root.key * (long)(root.cnt)) + sum(root.left) + prefixSum(root.right, key);
            } else
                return 0;
        }
        long prefixSum(long x) {
            return prefixSum(root, x);
        }
        public int countLess(Node root , long  key) {
            if(root != null){
                if(root.key == key)
                    return root.cnt + size(root.left);
                else if(root.key > key)
                    return countLess(root.left, key);
                else
                    return root.cnt + size(root.left) + countLess(root.right, key);
            }
            else
                return 0;
        }
        public int countLess(long key){
            return countLess(root, key);     
        }

        public Node rebalance(Node N) {
            Node par = N.parent;
            if (height(N.left) - height(N.right) >= 2) {
                Node M = N.left;
                if (height(M.right) > height(M.left))
                    M = rotateLeft(M);
                N = rotateRight(N);
            }
            if (height(N.right) - height(N.left) >= 2) {
                Node M = N.right;
                if (height(M.left) > height(M.right))
                    M = rotateRight(M);

                N = rotateLeft(N);
            }

            if (par != null)
                return rebalance(par);
            else
                return N;

        }

        public Node add(long key, Node root, Node parent) {
            if (root == null) {
                Node newNode = new Node(key);
                newNode.parent = parent;
                return newNode;
            } else {
                if (key < root.key)
                    root.left = add(key, root.left, root);
                else if (key > root.key)
                    root.right = add(key, root.right, root);
                else
                    root.cnt++;

                adjustHeight(root);
                return root;
            }
        }

        private StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb, Node root) {

            if (root == null) {
                sb.append("Tree Empty\n");
                return sb;
            }
            if (root.right != null) {
                toString(new StringBuilder().append(prefix).append(isTail ? "|   " : "    "), false, sb, root.right);
            }
            sb.append(prefix).append(isTail ? "|-- " : "|-- ").append(root.key + " sum " + root.sum + " sz " + root.size + " cnt " + root.cnt).append("\n");
            if (root.left != null) {
                toString(new StringBuilder().append(prefix).append(isTail ? "    " : "|   "), true, sb, root.left);
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
        long MOD = 1000000007;
        while(T-->0) {
            
            int P = nextInt();
            int Q = nextInt();
            int R = nextInt();
            
            AVLTree treapA = new AVLTree();
            AVLTree treapC = new AVLTree();
            
            while(P-->0) 
                treapA.add(nextInt());
            long B[] = nextLongArray(Q);
            while(R-->0) 
                treapC.add(nextInt());
            /*
            println(treapA);
            println(treapC);
            */
            long sum = 0;
            Arrays.sort(B);
            
            for(long Y : B) {
                /*
                System.out.println(treapA.countLess(Y));
                System.out.println(Y);
                System.out.println((treapA.prefixSum(Y) % MM.mod));
                System.out.println(treapC.countLess(Y));
                System.out.println(Y);
                System.out.println((treapC.prefixSum(Y) % MM.mod));
                */
                long m1 = (1L * Y * treapA.countLess(Y)) % MOD;
                long m2 = (m1 + treapA.prefixSum(Y)) % MOD;
                long m3 = (1L * Y * treapC.countLess(Y)) % MOD;
                long m4 = (m3 + treapC.prefixSum(Y)) % MOD;
                sum = (sum + ((m2 * m4) % MOD)) % MOD;
                /*
                sum = MM.add(sum, MM.mul(MM.add(MM.mul(treapA.countLess(Y), Y), (treapA.prefixSum(Y) % MM.mod)), 
                                         MM.add(MM.mul(treapC.countLess(Y), Y), (treapC.prefixSum(Y) % MM.mod))));
                */
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