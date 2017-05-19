import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
/*
 * This submission http://codeforces.com/contest/786/submission/25756378 
 * is taken for debugging puposes
 */
public class LegacyResearch {
    static int E = 0;
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        FastScanner in = new FastScanner(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskB solver = new TaskB();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskB {
        final long infinity = (long) 1e18 + 100;
        List<Edge>[] edges;

        public void solve(int testNumber, FastScanner in, PrintWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();
            int s = in.nextInt() - 1;
            int N = n + 2 * 4 * n;
            edges = new List[N];
            for (int i = 0; i < edges.length; i++) {
                edges[i] = new ArrayList<>();
            }
            int root1Offset = n;
            int root2Offset = n + 4 * n;
            String deb = "";
            long st = System.nanoTime();
            initTree(root1Offset, 0, 0, n - 1, false);
            initTree(root2Offset, 0, 0, n - 1, true);
            for (int i = 0; i < m; i++) {
                int t = in.nextInt();
                int v = in.nextInt() - 1;
                int l = in.nextInt() - 1;
                int r = l;
                if (t != 1) {
                    r = in.nextInt() - 1;
                }
                int w = in.nextInt();
                if (t == 1) {
                    edges[v].add(new Edge(l, w));
                }
                if (t == 2) {
                    addSegment(root1Offset, 0, 0, n - 1, l, r, v, w, false);
                }
                if (t == 3) {
                    addSegment(root2Offset, 0, 0, n - 1, l, r, v, w, true);
                }
            }
            deb += "time for init = " + ((System.nanoTime() - st) / 1e9) + " s ";
            long[] d = new long[N];
            Arrays.fill(d, infinity);
            d[s] = 0;
            PriorityQueue<Item> pq = new PriorityQueue<>();
            st = System.nanoTime();
            pq.offer(new Item(s, d[s]));
            while (!pq.isEmpty()) {
                Item cur = pq.poll();
                int v = cur.v;
                if (d[v] != cur.d) {
                    continue;
                }
                for (Edge e : edges[v]) {
                    int u = e.dst;
                    long nd = d[v] + e.w;
                    if (d[u] > nd) {
                        d[u] = nd;
                        pq.offer(new Item(u, d[u]));
                    }
                }
            }
            deb += "time for dijkstra = " + ((System.nanoTime() - st) / 1e9) + "s";
            deb += " edges = " + E;
            if(s + 1 == 49273) {
                System.out.println(deb) ;
                return;
            }
            for (int i = 0; i < n; i++) {
                if (i > 0) {
                    out.print(" ");
                }
                if (d[i] >= infinity) {
                    out.print(-1);
                } else {
                    out.print(d[i]);
                }
            }
        }

        private void initTree(int offset, int root, int l, int r, boolean edgesUp) {
            if (l == r) {
                int v = l;
                int u = offset + root;
                if (edgesUp) {
                    edges[v].add(new Edge(u, 0));
                } else {
                    edges[u].add(new Edge(v, 0));
                }
                return;
            }
            int m = (l + r) / 2;
            int a = offset + root;
            int b = offset + 2 * root + 1;
            int c = offset + 2 * root + 2;
            if (edgesUp) {
                edges[b].add(new Edge(a, 0));
                edges[c].add(new Edge(a, 0));
            } else {
                edges[a].add(new Edge(b, 0));
                edges[a].add(new Edge(c, 0));
            }
            initTree(offset, 2 * root + 1, l, m, edgesUp);
            initTree(offset, 2 * root + 2, m + 1, r, edgesUp);
        }

        private void addSegment(int offset, int root, int l, int r, int L, int R, int v, int w, boolean rev) {
            if (l > r || l > R || L > r || L > R) {
                return;
            }
            if (l == L && r == R) {
                int u = offset + root;
                if (rev) {
                    edges[u].add(new Edge(v, w));
                } else {
                    edges[v].add(new Edge(u, w));
                }
                return;
            }
            int m = (l + r) / 2;
            addSegment(offset, 2 * root + 1, l, m, L, Math.min(R, m), v, w, rev);
            addSegment(offset, 2 * root + 2, m + 1, r, Math.max(m + 1, L), R, v, w, rev);
        }

        class Edge {
            int dst;
            long w;

            Edge(int dst, long w) {
                E++;
                this.dst = dst;
                this.w = w;
            }

        }

        class Item implements Comparable<Item> {
            long d;
            int v;

            Item(int v, long d) {
                this.v = v;
                this.d = d;
            }

            public int compareTo(Item o) {
                if (d == o.d) {
                    return 0;
                }
                return d < o.d ? -1 : 1;
            }

        }

    }

    static class FastScanner {
        private BufferedReader in;
        private StringTokenizer st;

        public FastScanner(InputStream stream) {
            in = new BufferedReader(new InputStreamReader(stream));
        }

        public String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    String rl = in.readLine();
                    if (rl == null) {
                        return null;
                    }
                    st = new StringTokenizer(rl);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }
}