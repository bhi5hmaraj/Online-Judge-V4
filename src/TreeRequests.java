import java.util.*;
import java.io.*;
public class TreeRequests  {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int[][] adj;
    static ArrayList<int[]>[] queries;
    static ArrayList<Integer>[] map;
    
    static char alph[];
    static boolean ans[];
    static int depth[];
    
    static void preprocess(int u) {
        depth[u] = 1;
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(u);
        while(!queue.isEmpty()) {
            int curr = queue.remove();
            for(int v : adj[curr]) {
                depth[v] = depth[curr] + 1;
                queue.add(v);
            }
        }
    }
    
    static class NodeStatus {
        int index;
        boolean explored;
        public NodeStatus(int u) {
            index = u;
            explored = false;
        }
    }
    
    static void processNode(int u) {

        int maxPos = adj[u].length > 0 ? adj[u][0] : -1;
        
        for(int i = 0; i < adj[u].length; i++) {
            int v = adj[u][i];
            maxPos = map[v].size() > map[maxPos].size() ? v : maxPos;
        }
        
        for(int i = 0; i < adj[u].length; i++) {
            int v = adj[u][i];
            int offset = map[maxPos].size() - map[v].size();
            if(v != maxPos) 
                for(int j = 0; j < map[v].size(); j++)
                    map[maxPos].set(offset + j, map[maxPos].get(offset + j) ^ map[v].get(j));
        }
        map[u] = maxPos < 0 ? new ArrayList<>() : map[maxPos];
        map[u].add(1 << (alph[u] - 'a'));

        for(int q[] : queries[u]) 
            ans[q[1]] = !(q[0] >= depth[u] && q[0] < depth[u] + map[u].size()) || 
                        Integer.bitCount(map[u].get(map[u].size() - (q[0] - depth[u]) - 1)) <= 1;
     
    }
    
    static void iterativeDfs(int start) {
    
        ArrayDeque<NodeStatus> stack = new ArrayDeque<>();  // Dont use stack (TLEs)
        stack.push(new NodeStatus(start));
        
        while(!stack.isEmpty()) {
            NodeStatus curr = stack.pop();
            int u = curr.index;
            if(curr.explored)
                processNode(u);
            else {
                curr.explored = true;
                stack.push(curr);
                for(int i = 0; i < adj[u].length; i++) {
                    int v = adj[u][i];
                    stack.push(new NodeStatus(v));
                }
            }
        }

    }
    
    // Courtesy : UWI ( adjacency list using Jagged Arrays )
    static int[][] packU(int n, int[] from, int[] to) {   
        int[][] g = new int[n][];
        int[] p = new int[n];
        for (int f : from)
            p[f]++;
        for (int i = 0; i < n; i++)
            g[i] = new int[p[i]];
        for (int i = 0; i < from.length; i++) 
            g[from[i]][--p[from[i]]] = to[i];
        
        return g;
    }
    
    
    public static void main(String[] args) throws IOException {
        
        PrintWriter writer = 
                new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
        FasterScanner scan = new FasterScanner();
        
        int V = scan.nextInt();
        int Q = scan.nextInt();
        queries = new ArrayList[V];
        map = new ArrayList[V];
        
        for(int i = 0; i < V; i++) 
            queries[i] = new ArrayList<>();
        
        
        int from[] = new int[V - 1];
        int to[] = new int[V - 1];
        
        for(int i = 0; i < V - 1; i++) { 
            from[i] = scan.nextInt() - 1;
            to[i] = i + 1;
        }
        
        adj = packU(V, from, to);

        alph = scan.nextLine().toCharArray();
        ans = new boolean[Q];
        depth = new int[V];
        
        preprocess(0);
        
        while(Q-->0)
            queries[scan.nextInt() - 1].add(new int[]{scan.nextInt() , Q});   // height , qNo

        iterativeDfs(0);
        
        for(int i = ans.length - 1; i >= 0; i--)
            writer.println(ans[i] ? "Yes" : "No");
       
        writer.close();
    
    }
    
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/


    static class FasterScanner {
        private byte[] buf = new byte[4096];
        private int tmp_curChar;
        private int tmp_numChars;

        public int read() { // Removed safety checks ! 
            if (tmp_curChar >= tmp_numChars) {
                tmp_curChar = 0;
                try {
                    tmp_numChars = System.in.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (tmp_numChars <= 0)
                    return -1;
            }
            return buf[tmp_curChar++];
        }

        public String nextLine() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isEndOfLine(c));
            return res.toString();
        }

        public int nextInt() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        private boolean isEndOfLine(int c) {
            return c == '\n' || c == '\r' || c == -1;
        }
    }

    
    /************************ TEMPLATE ENDS HERE ************************/
    
}