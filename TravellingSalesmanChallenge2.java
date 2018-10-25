import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

class TravellingSalesmanChallenge2 {

    static int     N;
    static boolean marked[];

    static class Edge implements Comparable<Edge> {
        int    from, to, cost;
        String fromAP, toAP;

        public Edge(int from, int to, int cost, String fromAP, String toAP) {
            this.fromAP = fromAP;
            this.toAP = toAP;
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge that) {
            return Integer.compare(cost, that.cost);
        }

        @Override
        public String toString() {
            return String.format("%s -> %s for %d", fromAP, toAP, cost);
        }
    }

    static ArrayList<Edge>[][] adj; // N x N matrix of adjacency lists ,
                                    // where adj[i][j] gives the flights for ith day and from jth area

    public static void main(String[] args) throws IOException {

        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);

        N = nextInt();
        adj = new ArrayList[N + 1][N];
        marked = new boolean[N + 1];

        for (int i = 1; i <= N; i++)
            for (int j = 0; j < N; j++)
                adj[i][j] = new ArrayList<>();

        String startAirport = next();

        String area[] = new String[N];
        HashMap<String, Integer> areaMap = new HashMap<>();

        for (int i = 0; i < N; i++) {
            area[i] = nextLine();
            for (String airport : nextLine().split(" "))
                areaMap.put(airport, i);
        }

        String line = null;
        while ((line = nextLine()) != null) {
            String tok[] = line.split(" ");
            int from = areaMap.get(tok[0]);
            int to = areaMap.get(tok[1]);
            int day = Integer.parseInt(tok[2]);
            int cost = Integer.parseInt(tok[3]);

            Edge e = new Edge(from, to, cost, tok[0], tok[1]);
            if (day == 0) { // TODO could be optimized !
                for (int i = 1; i <= N; i++)
                    adj[i][from].add(e);
            } else
                adj[day][from].add(e);
        }

        solveGreedy(startAirport, areaMap.get(startAirport));

        reader.close();
        writer.close();
    }

    static final boolean shuffle = false;

    static void solveGreedy(String startAP, int startArea) {

        for (int i = 1; i <= N; i++)
            for (int j = 0; j < N; j++) {
                Collections.sort(adj[i][j]);
                // System.out.println("Flights at day " + i + " area " + j + " = " + adj[i][j]);
            }

        /*
         * Greedy OT | score 5 | 339 7 | 347 9 | 350
         * 
         * Greedy (Randomized) OT | Score 9 | 72
         */

        bestTillNow = new Edge[N];

        marked[startArea] = true;
        startTimeStamp = System.nanoTime();

        for (Edge e : adj[1][startArea])
            if (e.fromAP.equals(startAP)) {
                bruteGreedy(e.to, 2, startArea, e.cost, e);
            }

        println(minCost);
        int d = 1;

        for (Edge e : bestTillNow)
            println(e.fromAP + " " + e.toAP + " " + (d++) + " " + e.cost);
    }

    static int        pathsToConsider = 8;                // Try these many hamiltonian paths
    static final long timeThresh      = 10;

    static final int  take            = 20;
    static int        minCost         = Integer.MAX_VALUE;
    static Edge       bestTillNow[];

    static Edge       currPath[];
    static int        counter         = 0;
    static long       startTimeStamp;

    static void bruteGreedy(int curr, int day, int end, int totalCost, Edge par) {

        if (pathsToConsider == 0)
            return;

        currPath[day - 2] = par;
        marked[curr] = true;

        int toTake = Math.min(adj[day][curr].size(), take);
        List<Edge> modified = adj[day][curr].subList(0, toTake);

        if (day == N) {
            for (Edge e : modified)
                if (e.to == end && totalCost + e.cost < minCost) {
                    minCost = totalCost + e.cost;
                    pathsToConsider--;
                    System.arraycopy(currPath, 0, bestTillNow, 0, N);
                    bestTillNow[N - 1] = e;
                }
        } else {
            for (Edge e : modified)
                if (e.fromAP.equals(par.toAP) && !marked[e.to])
                    bruteGreedy(e.to, day + 1, end, totalCost + e.cost, e);
        }

        marked[curr] = false;
        currPath[day - 2] = null;

    }

    ///////////////////////////////////////// I/O Stuff 
    
    
    static BufferedReader  reader;
    static PrintWriter     writer;
    static StringTokenizer st;

    static String next() {
        while (st == null || !st.hasMoreTokens()) {
            try {
                String line = reader.readLine();
                if (line == null) {
                    return null;
                }
                st = new StringTokenizer(line);
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
        return st.nextToken();
    }

    static String nextLine() {
        String s = null;
        try {
            s = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    static int nextInt() {
        return Integer.parseInt(next());
    }

    static long nextLong() {
        return Long.parseLong(next());
    }

    static double nextDouble() {
        return Double.parseDouble(next());
    }

    static char nextChar() {
        return next().charAt(0);
    }

    static int[] nextIntArray(int n) {
        int[] a = new int[n];
        int i = 0;
        while (i < n) {
            a[i++] = nextInt();
        }
        return a;
    }

    static long[] nextLongArray(int n) {
        long[] a = new long[n];
        int i = 0;
        while (i < n) {
            a[i++] = nextLong();
        }
        return a;
    }

    static int[] nextIntArrayOneBased(int n) {
        int[] a = new int[n + 1];
        int i = 1;
        while (i <= n) {
            a[i++] = nextInt();
        }
        return a;
    }

    static long[] nextLongArrayOneBased(int n) {
        long[] a = new long[n + 1];
        int i = 1;
        while (i <= n) {
            a[i++] = nextLong();
        }
        return a;
    }

    static void print(Object o) {
        writer.print(o);
    }

    static void println(Object o) {
        writer.println(o);
    }
}
