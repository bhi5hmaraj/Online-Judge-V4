import java.util.*;
import java.io.*;
public class CardsSorting {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    

    static class SplayTree /** The following implementation does not allow duplicates **/{
        private Vertex root;
        int arr[];
        public SplayTree(int arr[]) {
            this.arr = arr;
            root = build(0, arr.length - 1);
        }
        
        Vertex build(int L , int R) {
            if(L <= R) {
                int mid = (L + R) >> 1;
                Vertex v = new Vertex(arr[mid], null, null, null);
                v.left = build(L, mid - 1);
                v.right = build(mid + 1, R);
                update(v);
                return v;
            }
            
            return null;
        }
        
        long simulate() {
            long steps = 0;
            for(int i = 1; i <= arr.length; i++) {
                steps += root.minPos + 1;
                VertexPair pair = split(root, root.minPos);
            }
        }
        
        static class Vertex {
            int key;
            // Sum of all the keys in the subtree - remember to update
            // it after each operation that changes the tree.
            int size;
            int minPos;
            int min;
            Vertex left;
            Vertex right;
            Vertex parent;

            Vertex(int key, Vertex left, Vertex right, Vertex parent) {
                this.key = this.min = key;
                this.size = 1;
                this.minPos = 0;
                this.left = left;
                this.right = right;
                this.parent = parent;
            }

            @Override
            public String toString() {
                return "[key = " + key + "]" + " p = " + (parent != null ? parent.key : "null") + " l = "
                        + (left != null ? left.key : "null") + " r = " + (right != null ? right.key : "null");
            }
        }

        static class VertexPair {
            Vertex left;
            Vertex right;

            VertexPair() {
            }

            VertexPair(Vertex left, Vertex right) {
                this.left = left;
                this.right = right;
            }
        }
        int size(Vertex v) {
            return v == null ? 0 : v.size;
        }
        private void update(Vertex v) {
            if (v == null)
                return;
            v.size = 1;
            v.min = Integer.MAX_VALUE;
            if (v.left != null) {
                v.left.parent = v;
                v.size += v.left.size;
                if(v.left.min < v.min) {
                    v.min = v.left.min;
                    v.minPos = v.left.minPos;
                }
            }
            
            if(v.key < v.min) {
                v.min = v.key;
                v.minPos = size(v.left);
            }
            
            if (v.right != null) {
                v.right.parent = v;
                v.size += v.right.size;
                if(v.right.min < v.min) {
                    v.min = v.right.min;
                    v.minPos = size(v.left) + 1 + v.right.minPos;
                }
            }
        }

        private void smallRotation(Vertex v) {
            Vertex parent = v.parent;
            if (parent == null) {
                return;
            }
            Vertex grandparent = v.parent.parent;
            if (parent.left == v) {
                Vertex m = v.right;
                v.right = parent;
                parent.left = m;
            } else {
                Vertex m = v.left;
                v.left = parent;
                parent.right = m;
            }

            update(parent);
            update(v);
            v.parent = grandparent;
            if (grandparent != null) {
                if (grandparent.left == parent) {
                    grandparent.left = v;
                } else {
                    grandparent.right = v;
                }
            }
            update(grandparent);

        }

        private void bigRotation(Vertex v) {
            if (v.parent.left == v && v.parent.parent.left == v.parent) {
                // Zig-zig left
                /*       a
                 *      /
                 *     /
                 *    b  
                 */
                smallRotation(v.parent);
                smallRotation(v);
            } else if (v.parent.right == v && v.parent.parent.right == v.parent) {
                // Zig-zig right
                /*
                 *  a
                 *   \
                 *    \
                 *     b
                 */
                smallRotation(v.parent);
                smallRotation(v);
            } else {
                /* Zig-zag
                 *    a    a
                 *    /    \
                 *    \    /
                 *     b   b
                 */
                smallRotation(v);
                smallRotation(v);
            }
        }

        private Vertex splay(Vertex v) {
            if (v == null)
                return null;
            while (v.parent != null) {
                if (v.parent.parent != null)
                    bigRotation(v);
                else
                    smallRotation(v);
            }
            return v;
        }

        // Searches for the given key in the tree with the given root
        // and calls splay for the deepest visited node after that.
        // Returns pair of the result and the new root.
        // If found, result is a pointer to the node with the given key.
        // Otherwise, result is a pointer to the node with the smallest
        // bigger key (next value in the order).
        // If the key is bigger than all keys in the tree,
        // then result is null.
        private VertexPair find(Vertex root, int key) {  //searches for a elem and splays the last touched node.
            Vertex v = root;    // Returns a vertex pair with 
            Vertex last = root; // Left node  = ceil of the search
            Vertex next = null; // Right node = last touched node in the search
            while (v != null) {
                if (v.key >= key && (next == null || v.key < next.key)) {
                    next = v;
                }
                last = v;
                if (v.key == key) {
                    break;
                }
                if (v.key < key) {
                    v = v.right;
                } else {
                    v = v.left;
                }
            }
            root = splay(last);
            return new VertexPair(next, root);
        }

        private static void print(Vertex root) {
            if (root != null) {
                print(root.left);
                System.out.println(root);
                print(root.right);
            }
        }
        
        VertexPair split(Vertex root, int pos) {
            VertexPair result = new VertexPair();
            Vertex piv = get(pos, root);
            result.left = splay(piv);
            if (result.left == null) {
                result.right = root;
                return result;
            }
            result.right = result.left.right;
            result.left.right = null;
            if (result.right != null)
                result.right.parent = null;
            update(result.left);
            update(result.right);
            return result;
        }

        Vertex get(int i, Vertex root) { // zero based index
            if (root != null) {
                if (i == size(root.left))
                    return root;
                else if (i < size(root.left))
                    return get(i, root.left);
                else
                    return get(i - size(root.left) - 1, root.right);
            } else
                return null;
        }

        Vertex merge(Vertex left, Vertex right) {
            if (left == null)
                return right;
            if (right == null)
                return left;
            right = splay(get(0, right));
            left = splay(get(left.size - 1, left));
            left.right = right;
            update(left);
            return left;
        }
        
        public void erase(int x) {
            VertexPair vp = find(root, x);
            root = vp.right;
            if (root != null)
                root.parent = null;
            update(root);
            if (vp.right != null && vp.right.key == x) {
                if (vp.right.right == null) {
                    root = vp.right.left;
                    if (root != null)
                        root.parent = null;
                    update(root);
                } else {        //First splay the ceil of x then splay x now the ceil of x is in the right ,                            
                    find(root, x + 1);  //so we can just redirect the root = root.right 
                    vp = find(root, x);
                    root = vp.right.right;
                    root.left = vp.right.left;
                    root.parent = null;
                    update(root);
                }
            }
        }

        public boolean find(int x) {
            VertexPair vp = find(root, x);
            root = vp.right;
            return root != null && root.key == x;
        }

        
        private StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb, Vertex root) {

            if (root == null) {
                sb.append("Tree Empty");
                return sb;
            }

            if (root.right != null) {
                toString(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb, root.right);
            }
            sb.append(prefix).append(isTail ? "└── " : "┌── ").append(root.key).append("\n");
            if (root.left != null) {
                toString(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb, root.left);
            }
            return sb;
        }

        @Override
        public String toString() {
            return this.toString(new StringBuilder(), true, new StringBuilder(), root).toString();
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