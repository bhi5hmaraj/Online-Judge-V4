/***
 * 
 * This is my personal collections of redundant code snipets used in various programming problems
 * 
 * */

import java.util.*;
import java.io.*;

class helper {
	static int loPrime[];
	
	static int primes[];
	
	static BitSet segmentedSieve(long L , long R) {  // Dependency primes array contains primes till sqrt(R) 
		int len = (int) (R - L + 1);
		BitSet bitSet = new BitSet(len);
		bitSet.set(0, len);
		for(long p : primes)
			for(long i = L % p == 0 ? L : L + (p - (L % p));i <= R;i += p)
				if(i != p)
					bitSet.set((int) (i - L) , false);
		
		if(L == 1)
			bitSet.set(0, false);
		
		return bitSet;
	}
	
	static void loPrimeSieve(int N) // stores lowest prime factor of each number
	// , used for calculating phi
	{
		loPrime = new int[N + 1];
		int pr[] = new int[N];
		int sz = 0;
		for (int i = 2; i <= N; ++i) {
			if (loPrime[i] == 0) {
				loPrime[i] = i;
				pr[sz] = i;
				sz++;
			}
			for (int j = 0; j < sz && pr[j] <= loPrime[i] && i * pr[j] <= N; ++j)
				loPrime[i * pr[j]] = pr[j];
		}
	}

	static int lp[];
	static int fi[]; // euler totient function

	static void fastFiSieve(int N) {
		lp = new int[N + 1];
		fi = new int[N + 1];
		int pr[] = new int[N];
		int sz = 0;
		fi[1] = 1;
		for (int i = 2; i <= N; ++i) {
			if (lp[i] == 0) {
				lp[i] = i;
				fi[i] = i - 1;
				pr[sz] = i;
				sz++;
			} else {
				// Calculating phi
				if (lp[i] == lp[i / lp[i]])
					fi[i] = fi[i / lp[i]] * lp[i];
				else
					fi[i] = fi[i / lp[i]] * (lp[i] - 1);
			}
			for (int j = 0; j < sz && pr[j] <= lp[i] && i * pr[j] <= N; ++j)
				lp[i * pr[j]] = pr[j];
		}
	}

	static int bigPrime[];
	static int N;

	private static void preCalBigPrimeSieve() // instead of the loPrimesieve you
	// could use bigprimeSieve which
	// has the same performance and
	// its a lot more intutive
	{
		bigPrime[1] = 1;
		for (int i = 2; i <= N; i++) {
			if (bigPrime[i] == 0) {
				bigPrime[i] = i;
				for (int j = 2 * i; j <= N; j += i)
					bigPrime[j] = i;
			}
		}
	}

	static HashMap<Integer, Integer> primeFactorize(int N) // Dependency : A
	// sieve (loPrime[])
	// which contains the
	// lowest prime
	// divisor for each
	// number
	{
		HashMap<Integer, Integer> mp = new HashMap<>();
		int ct, prime;
		while (N != 1) {
			prime = loPrime[N];
			ct = 0;
			while (N % prime == 0) {
				N /= prime;
				ct++;
			}
			mp.put(prime, ct);
		}
		return mp;
	}

	private static long modPow(long a, long b) // squared exponentiation
	{
		if (b == 0L || a == 1L)
			return 1L;
		else if (b == 1L)
			return a;
		else {
			if ((b & 1L) == 0L) // Checking whether b is even (fast)
				return modPow((a * a) % mod, b >> 1);
			else
				return (a * modPow((a * a) % mod, b >> 1)) % mod;
		}
	}

	/***
	 * Documentation for lower_bound()
	 *
	 * if the array = [2,4,6,7,10,12] 1. key = 1 return = -1 (u can insert to
	 * the left of this one based point) 2. key = 2 return = 0 3. key = 12
	 * return = -6 4. key = 20 return = -7
	 * 
	 */
	private static int lower_bound(int arr[], int key) // if it exists then
	// returns the lower
	// bound or else returns
	// -(pos+1)
	{
		int lo = 0;
		int hi = arr.length - 1;
		int mid = 0;
		int pos = -1;
		boolean flag = false;
		while (lo <= hi) {
			mid = (lo + hi) / 2;
			if (arr[mid] >= key) {
				if (arr[mid] == key && !flag)
					flag = true;
				hi = mid - 1;
				pos = mid;
			} else
				lo = mid + 1;
		}
		if (flag)
			return pos;
		else {
			if (pos == -1)
				pos = arr.length;
			pos = -(pos + 1);
			return pos;
		}
	}

	/*
	 * Hack: copy - a double[] , copy of int array upper_bound =( - (
	 * Arrays.binarySearch(copy, (double)key + 0.5) + 1 ) ) lower_bound =( - (
	 * Arrays.binarySearch(copy, (double)key - 0.5) + 1 ) )
	 */

	private static String[] substrings(String in) {
		int len = in.length();
		String sub[] = new String[((len) * (len + 1)) / 2];
		int ptr = 0;
		for (int i = 0; i < len; i++)
			for (int j = i; j < len; j++)
				sub[ptr++] = in.substring(j - i, j + 1);

		return sub;
	}

	private static int log(long n, long b) {
		if (n < 1L || b <= 1L)
			throw new IllegalArgumentException("Wrong base or n");
		else {
			int ct = 1;
			while (n > 0) {
				n /= b;
				ct++;
			}
			return ct - 1;
		}
	}

	private static void swap(char ch[], int j, int k) {
		char temp = ch[j];
		ch[j] = ch[k];
		ch[k] = temp;
	}

	private static String nextBiggestPermutationModified(String str) // If it
	// returns
	// null
	// then no
	// permutation
	// exists
	{
		char ch[] = str.toCharArray();
		int i = ch.length - 1;
		while (i > 0 && ch[i] <= ch[i - 1]) {
			i--;
		}
		if (i != 0) {
			for (int j = i, k = ch.length - 1; j <= (i + ch.length - 1) / 2; j++, k--)
				swap(ch, j, k);

			char start = ch[i - 1];
			int pos = i;
			for (; pos < ch.length; pos++)
				if (ch[pos] > start)
					break;

			swap(ch, pos, i - 1);
			return new String(ch);
		} else
			return null;
	}

	private static int partition(int[] a, int lo, int hi) {
		int i = lo;
		int j = hi + 1;
		int v = a[lo];
		while (true) {

			// find item on lo to swap
			while (less(a[++i], v))
				if (i == hi)
					break;

			// find item on hi to swap
			while (less(v, a[--j]))
				if (j == lo)
					break; // redundant since a[lo] acts as sentinel

			// if pointers cross
			if (i >= j)
				break;

			exch(a, i, j);
		}

		// put partitioning item v at a[j]
		exch(a, lo, j);

		// now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
		return j;
	}

	private static boolean less(int v, int w) {
		return v - w < 0;
	}

	private static void exch(int[] a, int i, int j) {
		int swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}

	private static int selection(int arr[], int k) {
		int lo = 0, hi = arr.length - 1;
		int pos = partition(arr, lo, hi);
		while (lo < hi) {
			// System.out.println("pos"+pos);
			if (pos < k)
				lo = pos + 1;
			else if (pos > k)
				hi = pos - 1;
			else
				break;
			pos = partition(arr, lo, hi);
		}
		return arr[pos];
	}

	static void shuffleArray(int[] array) {
		Random random = new Random();
		for (int i = array.length - 1; i > 0; i--) {
			int index = random.nextInt(i + 1);
			int temp = array[index];
			array[index] = array[i];
			array[i] = temp;
		}
	}

	private static <Key> void frequency(Map<Key, Integer> mp, Key k) {
		Integer query = mp.get(k);
		mp.put(k, query == null ? 1 : query + 1);
	}
	

	static class MultiSet<T> extends HashMap<T, Integer> {
		public void add(T key) {
			Integer q = super.get(key);
			if (q == null)
				super.put(key, 1);
			else
				super.put(key, q + 1);
		}

		@Override
		public Integer remove(Object key) {
			Integer q = super.get(key);
			if (q != null) {
				if (q == 1)
					super.remove(key);
				else
					super.put((T) key, q - 1);
			} else
				throw new NullPointerException("The specified key does not exist");

			return q;
		}
	}

	public static HashMap<Character, Integer> freqOfChar(String str) {
		HashMap<Character, Integer> mp = new HashMap<>();
		for (int i = 0, len = str.length(); i < len; i++) {
			char k = str.charAt(i);
			Integer query = mp.get(k);
			if (query == null)
				mp.put(k, new Integer(1));
			else {
				mp.put(k, query + 1);
			}
		}
		return mp;
	}

	private static String filter(String str) // Removes duplicate chars in a
	// string
	{
		HashSet<Character> filter = new HashSet<>();
		StringBuilder sb = new StringBuilder();
		for (char c : str.toCharArray()) {
			if (!filter.contains(c)) {
				filter.add(c);
				sb.append(c);
			}
		}
		return sb.toString();
	}

	static boolean canPalin(String str) {
		TreeMap<Character, Integer> freq = new TreeMap<>();
		int len = str.length();
		for (int i = 0; i < len; i++)
			frequency(freq, str.charAt(i));
		if (len % 2 == 0) {
			for (Map.Entry<Character, Integer> e : freq.entrySet())
				if (e.getValue() % 2 == 1)
					return false;

			return true;
		} else {
			int ct = 0;
			for (Map.Entry<Character, Integer> e : freq.entrySet()) {
				if (e.getValue() % 2 == 1)
					ct++;
				if (ct > 1)
					return false;
			}
			return true;
		}
	}

	public static BitSet isPrimeBitSet(int N) {// Sieve of Erathanoses
		BitSet bs = new BitSet(N + 1);
		bs.set(0, bs.size());
		bs.set(0, false);
		bs.set(1, false);
		for (int i = 2; i * i <= N; i++)
			if (bs.get(i))  // i is prime
				for (int j = i * i; j <= N; j += i)
					bs.set(j, false);

		return bs;
	}
	
	public static boolean[] isPrimeArray(int N) // Sieve of Erathanoses
	{
		boolean num[] = new boolean[N + 1];
		Arrays.fill(num, true);
		num[1] = num[0]=  false;
		for (int i = 2; i * i <= N; i++)
			if (num[i])  // i is prime
				for (int j = i * i; j <= N; j += i)
					num[j] = false;
		
			
		return num;
	}
	
	public static int[] sieve(int N) // Sieve of Erathanoses dependency : isPrimeArray()
	{
		
		boolean isPrime[] = isPrimeArray(N);
		int sz = 0;
		for(boolean b : isPrime)
			sz += b ? 1 : 0;
		int arr[] = new int[sz];
		int ptr = 0;
		for (int i = 2; i <= N; i++)
			if (isPrime[i])
				arr[ptr++] = i;
				
		return arr;
	}
	public static boolean isPalin(String str) {
		int len = str.length();
		int end = len / 2;
		for (int i = 0; i < end; i++)
			if (str.charAt(i) != str.charAt(len - i - 1))
				return false;

		return true;
	}

	static long gcd(long a , long b) { return (b == 0) ? a : gcd(b, a % b); }

	public static ArrayList<Integer> palindromes(int start, int end) {
		ArrayList<Integer> palins = new ArrayList<>();
		for (int i = start; i <= end; i++) {
			if (isPalin(String.valueOf(i)))
				palins.add(i);
		}
		return palins;
	}

	static long nCk(int n, int k) {
		if (n == k)
			return 1;
		long ans = 1;
		k = (int) Math.min(k, n - k);
		for (int i = 1; i <= k; i++, n--) {
			if (n % i == 0) {
				ans *= (n / i);
			} else if (ans % i == 0) {
				ans = (ans / i) * n;
			} else {
				ans = (ans * n) / i;
			}
		}
		return ans;
	}


	class MyMap<K, V> extends HashMap<K, ArrayList<V>> {
		private static final long serialVersionUID = 1L; // don't know what it
		// is but eclipse gives
		// me a warning

		public MyMap() {
			super();
		}

		@Override
		public ArrayList<V> put(K key, ArrayList<V> value) {
			return super.put(key, value);
		}

		public void putVal(K key, V value) {
			ArrayList<V> arl = get(key);
			if (arl == null) {
				arl = new ArrayList<>();
				arl.add(value);
			} else
				arl.add(value);

			put(key, arl);
		}
	}

	class FasterScanner {
		private byte[] buf = new byte[1024];
		private int tmp_curChar;
		private int tmp_numChars;

		public int read() {
			if (tmp_numChars == -1)
				throw new InputMismatchException();
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

		public String next() {
			int c = read();
			while (isSpaceChar(c))
				c = read();
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = read();
			} while (!isSpaceChar(c));
			return res.toString();
		}

		public long nextLong() {
			int c = read();
			while (isSpaceChar(c))
				c = read();
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			long res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = read();
			} while (!isSpaceChar(c));
			return res * sgn;
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
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = read();
			} while (!isSpaceChar(c));
			return res * sgn;
		}

		public int[] nextIntArray(int n) {
			int[] arr = new int[n];
			for (int i = 0; i < n; i++) {
				arr[i] = nextInt();
			}
			return arr;
		}

		public long[] nextLongArray(int n) {
			long[] arr = new long[n];
			for (int i = 0; i < n; i++) {
				arr[i] = nextLong();
			}
			return arr;
		}

		private boolean isSpaceChar(int c) {
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}

		private boolean isEndOfLine(int c) {
			return c == '\n' || c == '\r' || c == -1;
		}
	}

	class Pair<A, B> {
		public final A first;
		public final B second;

		public Pair(final A first, final B second) {
			this.first = first;
			this.second = second;
		}

		public boolean equals(Pair<A, B> that) {
			return this.first.equals(that.first) && this.second.equals(that.second);
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

		public Node smallest(Node root) {
			return (root.left == null) ? root : smallest(root.left);
		}

		public Node largest(Node root) {
			return (root.right == null) ? root : largest(root.right);
		}

		public void del(long key) {
			Node toBeRemoved = search(root, key);
			if (toBeRemoved != null) {
				if (toBeRemoved.cnt == 1)
					delete(toBeRemoved);
				else {
					toBeRemoved.cnt--;
					while (toBeRemoved != null) {
						adjustHeight(toBeRemoved);
						toBeRemoved = toBeRemoved.parent;
					}
				}
			}
		}

		public long sumInRange(long L, long R ) {  	    
			return  (sumStrictlyLess(root, R) - sumStrictlyLess(root, L)); // [L,R) 
		}

		public long sumStrictlyLess(Node root, long key) {
			if (root != null) {
				if (root.key == key)
					return sum(root.left);
				else if (key < root.key)
					return sumStrictlyLess(root.left, key);
				else
					return (root.key * (long)(root.cnt)) + sum(root.left) + sumStrictlyLess(root.right, key);
			} else
				return 0;
		}
		public int countGreater(Node root , long  key)
		{
			if(root != null){
				if(root.key == key){
					return size(root.right);
				}
				else if(root.key < key){
					return countGreater(root.right, key);
				}
				else
					return root.cnt + size(root.right) + countGreater(root.left, key);
			}
			else
				return 0;
		}
		public int countGreater(long key){
			return countGreater(root, key);	    
		}
		public void delete(Node N) {

			if (N.left == null && N.right == null) {
				if (N == root)
					root = null;
				else {
					Node par = N.parent;
					if (par.left == N)
						par.left = null;
					else
						par.right = null;

					Node orig_par = par;
					while (par != null) {
						adjustHeight(par);
						par = par.parent;
					}
					root = rebalance(orig_par);
				}
			} else if (N.right == null && N.left != null) {
				Node par = N.parent;
				if (par != null) {
					if (par.left == N)
						par.left = N.left;
					else
						par.right = N.left;

					N.left.parent = par;
					Node orig_par = par;
					while (par != null) {
						adjustHeight(par);
						par = par.parent;
					}

					root = rebalance(orig_par);

				} else {
					root = root.left;
					root.parent = null;
					adjustHeight(root);
				}
			} else if (N.left == null && N.right != null) {
				Node par = N.parent;
				if (par != null) {
					if (par.left == N)
						par.left = N.right;
					else
						par.right = N.right;

					N.right.parent = par;

					Node orig_par = par;
					while (par != null) {
						adjustHeight(par);
						par = par.parent;
					}
					root = rebalance(orig_par);
				} else {
					root = root.right;
					root.parent = null;
					adjustHeight(root);
				}
			} else {
				Node small = smallest(N.right);
				N.key = small.key;
				N.cnt = small.cnt;  // !!! VERY VERY IMPORTANT
				delete(small);
			}
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

		public void print(Node root) {
			if (root != null) {
				print(root.left);
				System.out.println(root);
				print(root.right);
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
			sb.append(prefix).append(isTail ? "|-- " : "|-- ").append(root.key).append("\n");
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

	static class SplayTree /** The following implementation does not allow duplicates **/{
		private Vertex root = null;

		static class Vertex {
			int key;
			// Sum of all the keys in the subtree - remember to update
			// it after each operation that changes the tree.
			long sum;
			Vertex left;
			Vertex right;
			Vertex parent;

			Vertex(int key, long sum, Vertex left, Vertex right, Vertex parent) {
				this.key = key;
				this.sum = sum;
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

		private void update(Vertex v) {
			if (v == null)
				return;
			v.sum = (v.key + (v.left != null ? v.left.sum : 0) + (v.right != null ? v.right.sum : 0));
			if (v.left != null) {
				v.left.parent = v;
			}
			if (v.right != null) {
				v.right.parent = v;
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
				 * 	a
				 * 	 \
				 * 	  \
				 * 	   b
				 */
				smallRotation(v.parent);
				smallRotation(v);
			} else {
				/* Zig-zag
				 *    a    a
				 *    /    \
				 *    \	   /
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
			Vertex v = root;	// Returns a vertex pair with 
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

		private VertexPair split(Vertex root, int key) {
			VertexPair result = new VertexPair();
			VertexPair findAndRoot = find(root, key);
			root = findAndRoot.right;
			result.right = findAndRoot.left;
			if (result.right == null) { // Key is largest in the tree
				result.left = root;
				return result;
			}
			result.right = splay(result.right);
			result.left = result.right.left;
			result.right.left = null;
			if (result.left != null)
				result.left.parent = null;

			update(result.left);
			update(result.right);

			return result;
		}

		private Vertex merge(Vertex left, Vertex right) {
			if (left == null)
				return right;
			if (right == null)
				return left;
			left = find(left, Integer.MAX_VALUE).right; // root after the find and splay , Find the max in left tree							 
			left.right = right;
			left.parent = null;
			update(left);
			return left;
		}

		public void insert(int x) {    // Split the tree at x and then the left tree has all elements less
			if (!find(x)) {            // than x and right tree has all elements greater now create a new node
				Vertex left = null;    // and merge the left-->new node-->right
				Vertex right = null;
				Vertex new_vertex = null;
				VertexPair leftRight = split(root, x);
				left = leftRight.left;
				right = leftRight.right;
				if (right == null || right.key != x) {
					new_vertex = new Vertex(x, x, null, null, null);
				}
				root = merge(merge(left, new_vertex), right);
				root.parent = null;
				update(root);
			}
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
				} else {   		//First splay the ceil of x then splay x now the ceil of x is in the right , 		    				
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

		public long sum(int from, int to) {
			VertexPair leftMiddle = split(root, from);
			Vertex left = leftMiddle.left;
			Vertex middle = leftMiddle.right;
			VertexPair middleRight = split(middle, to + 1);
			middle = middleRight.left;
			Vertex right = middleRight.right;
			long ans = middle != null ? middle.sum : 0;
			root = merge(left, merge(middle, right));
			return ans;
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

	static int mod = 1000000007;

	static class Matrix  { // Don't use ModMath class with Matrix (very slow in matrix exponentiation) 
		long e00, e01, e10, e11;

		Matrix(long a, long b, long c, long d) {
			e00 = a;
			e01 = b;
			e10 = c;
			e11 = d;
		}

		Matrix multiply(Matrix t) {
			long a = (((e00 * t.e00) % mod) + ((e01 * t.e10) % mod)) % mod;
			long b = (((e00 * t.e01) % mod) + ((e01 * t.e11) % mod)) % mod;
			long c = (((e10 * t.e00) % mod) + ((e11 * t.e10) % mod)) % mod;
			long d = (((e10 * t.e01) % mod) + ((e11 * t.e11) % mod)) % mod;
			return new Matrix(a, b, c, d);
		}
		public String toString() {
			return e00 + " " + e01 + "\n" + e10 + " " + e11 + "\n";
		}

		static long mul(long a, long b) {
			return ((a % mod) * (b % mod)) % mod;
		}

		static long add(long a, long b) {
			return ((a % mod) + (b % mod)) % mod;
		}
	}

	public static Matrix pow(Matrix m, int b) {
		if (b == 1)
			return m;
		else {
			if ((b & 1) == 0)
				return pow(m.multiply(m), b >> 1);
			else
				return m.multiply(pow(m.multiply(m), b >> 1));
		}
	}

    static class MyBitSet {
        long bits[];
        int cardinality;
        int size;
        MyBitSet(int MAX) {
            size = MAX;
            bits = new long[((MAX - 1) / 64) + 1];
            cardinality = 0;
        }

        void set(int n, boolean f) {
            int index = n / 64;
            if (f) {
                if((bits[index] & (1L << (n % 64))) == 0)
                    cardinality++;
                bits[index] |= (1L << (n % 64));
            }
            else
                bits[index] ^= (bits[index] & (1L << (n % 64))) != 0 ? (1L << (n % 64)) : 0;
        }

        void set(int n) {
            set(n, true);
        }
        
        int cardinality() {
            return cardinality;
        }
                
        boolean get(int n) {
            return ((bits[n / 64]) & (1L << (n % 64))) != 0;
        }
        
    }

	/* Increase stack size in java

	 */
	public static void main(String[] args) throws IOException {
		new Thread(null, new Runnable() {
			public void run() {
				new helper().run();
			}
		}, "Increase Stack", 1 << 25).start();

	}

	void run(){	
	}

	static class DSU {
		private int parent[];
		private int size[];
		private int cnt;

		DSU(int length) {
			this.cnt = length;
			parent = new int[length + 10];
			size = new int[length + 10];
			Arrays.fill(size, 1);
			for (int i = 0; i < parent.length; i++)
				parent[i] = i;
		}

        int root(int p) {
            while(p != parent[p]) p = parent[p];
            return p;
        }

		int sizeOf(int p) {
			return size[root(p)];
		}

		boolean connected(int u, int v) {
			return root(u) == root(v);
		}

		int components() {
			return cnt;
		}

		void union(int u, int v) {
			if (!connected(u, v)) {
				cnt--;
				int rootU = root(u);
				int rootV = root(v);
				if (size[rootU] < size[rootV]) {
					parent[rootU] = rootV;
					size[rootV] += size[rootU];
				} else {
					parent[rootV] = rootU;
					size[rootU] += size[rootV];
				}
			}
		}
	}

	private static long distTo[];  // Should be filled with infinity
	private static ArrayList<Edge>[] adj;

	static class Edge implements Comparable<Edge> {
		int v;
		long cost;

		Edge(int v, long cost) {
			this.v = v;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.cost, o.cost);
		}
	}

	   
    private static void dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));
        distTo[start] = 0;
        while (!pq.isEmpty()) {
            Edge min = pq.remove();
            int u = min.v;
            if (distTo[u] < min.cost)
                continue;

            for (Edge e : adj[u])
                if (distTo[e.v] > distTo[u] + e.cost) {
                    distTo[e.v] = distTo[u] + e.cost;
                    pq.add(new Edge(e.v, distTo[e.v]));
                }
        }
    }

	static class Edge_MST implements Comparable<Edge_MST> // For kruskal MST
	{
		int u, v;
		long cost;

		Edge_MST(int u, int v, long cost) {
			this.u = u;
			this.v = v;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge_MST o) {
			return Long.compare(this.cost, o.cost);
		}
	}

	private static long KruskalMST(Edge_MST arr[], int V) {
		Arrays.sort(arr);
		DSU dsu = new DSU(V);
		long minCost = 0;
		for (int i = 0; i < arr.length && dsu.components() != 1; i++) {
			if (!dsu.connected(arr[i].u, arr[i].v)) {
				dsu.union(arr[i].u, arr[i].v);
				minCost += arr[i].cost;
			}
		}
		return minCost;
	}

	private static void reverse(int arr[]) {
		for (int i = 0, end = arr.length >> 1; i < end; i++) {
			int temp = arr[i];
			arr[i] = arr[arr.length - i - 1];
			arr[arr.length - i - 1] = temp;
		}
	}

    static class MMInt {       // MM (Modular Math) class 
        static final int mod = (int) (1e9) + 7; // Default
        static int sub(int a, int b) {return (a - b  + mod) % mod;}
        static int mul(int a, int b) {return (int) ((1L * a * b ) % mod);}
        static int add(int a, int b) {return (a + b) % mod;}
        static int div(int a, int b) {return mul(a, modInverse(b));}
        static int modInverse(int n) {return modPow(n, mod - 2);} // Fermat's little theorem
        static int modPow(long a , long b) {
            long pow = 1;
            while(b > 0) {
                if((b & 1L) == 1)
                    pow = ((pow * a) % mod);

                a = ((a * a) % (mod));
                b >>= 1;
            }
            return (int) pow;
        }
    }
	
	static class MM {   	// MM (Modular Math) class 
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

	private static int MAX = 1000_000; // (Change it to max N limit (be careful with the index)					  
	private static long fact[] = new long[MAX + 5];
	private static long invFact[] = new long[MAX + 5];
	static {
		fact[1] = 1;
		fact[0] = 1;
		for (int i = 2; i <= MAX; i++)
			fact[i] = MM.mul(i, fact[i - 1]);
		for(int i=0;i<=MAX;i++)
			invFact[i] = MM.modInverse(fact[i]);
	}

	private static long nCr(int n, int r) { // Precompute inv Factorials (Dont compute every time) 
		return MM.mul(fact[n], MM.mul(invFact[r], invFact[n - r]));
	}
	static class SparseTable2D  // <O (MNlog(M)log(N)) , O(1) >  
	{		
		/*
		 * Dont use Math.log its (very * 2 ^ 64) slow for a lot of queries (10^6), 
		 * instead precompute the log values . or use log(n) = (31 - Integer.numberOfLeadingZeros(n))
		 * Costed me several days
		 * 
		 * In a sparse table you break the query rectangle into four smaller rects each with 
		 * dimensions log(height) X log(width)
		 * 
		 * So the query time is O(1) , but the build time is O(M * N * log(M) * log(N))
		 * 
		 * 	********|****|******
		 * 	********|****|******
		 * 	********|****|******
		 * 	--------|----|------
		 * 	******************
		 * 	******************
		 */

		int sparse[][][][];
		static int log(int N) { return 31 - Integer.numberOfLeadingZeros(N); }
		int R,C;

		int getMax(int x1,int y1,int x2,int y2, boolean build){
			int x_sz = x2 - x1 + 1;
			int y_sz = y2 - y1 + 1;
			int k1 = (x_sz == 1) ? 0 : log(build ? (x_sz - 1) : x_sz);
			int k2 = (y_sz == 1) ? 0 : log(build ? (y_sz - 1) : y_sz);
			int NW = sparse[k1][k2][x1][y1]; // North-West
			int NE = sparse[k1][k2][x1][y_sz - (1 << k2) + y1]; // North-East
			int SW = sparse[k1][k2][x_sz - (1 << k1) + x1][y1]; // South-West
			int SE = sparse[k1][k2][x_sz - (1 << k1) + x1][y_sz - (1 << k2) + y1]; // South-East
			return (int) Math.max(Math.max(NW, NE), Math.max(SW, SE));
		}

		SparseTable2D(int arr[][],int R,int C)
		{
			this.R = R;
			this.C = C;
			int k1 = log(R) + 1;
			int k2 = log(C) + 1;
			sparse = new int[k1][k2][R][C];
			for (int i = 0; i < R; i++)
				for (int j = 0; j < C; j++)
					sparse[0][0][i][j] = arr[i][j];

			for(int h=0;h<k1;h++)
			{
				for(int v=0;v<k2;v++)
				{
					if(!(h == 0 && v == 0))
					{
						for(int i=0;i+(1<<h) <= R;i++)
						{
							for(int j=0;j+(1<<v) <= C;j++)
							{
								sparse[h][v][i][j] = getMax(i, j, i + (1<<h) - 1, j + (1<<v) - 1, true);
							}
						}
					}
				}
			}

		}
	}


	static class SparseTable1D  // < O(Nlog(N)) , O(1) >  0 based indexing
	{
		int sparse[][];
		int len;
		static int log(int N) { return 31 - Integer.numberOfLeadingZeros(N); }

		SparseTable1D(int arr[])
		{
			len = arr.length;
			int k = log(len) + 1;
			sparse = new int[k][len];
			for (int i = 0; i < len; i++)
				sparse[0][i] = arr[i];

			for(int i=1;i<k;i++)
				for(int j=0;j+(1<<i) <= len;j++)
					sparse[i][j] = Math.max(sparse[i-1][j],sparse[i-1][j+(1<<(i-1))]);

		}

		int getMax(int L,int R)
		{
			int sz = R - L + 1;
			int k  = log(sz);
			int v1 = sparse[k][L];
			int v2 = sparse[k][L + (sz - (1 << k))];
			return Math.max(v1,v2);
		}

	}
	static class Vector {
        double         x, y;
        private double norm;
        

        public String toString() {
            return String.format("(%.3f , %.3f)", x , y);
        }
        
        Vector(double x, double y) {
            this.x = x;
            this.y = y;
            this.norm = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        }
        
        Vector() {
            this(0, 0);
        }

        double magnitude() {
            return norm;
        }
        
        double angleTo(Vector that) {
            double cosTheta = ((this.x * that.x) + (this.y * that.y)) / (this.magnitude() * that.magnitude());
            return Math.acos(cosTheta);
        }
        
        Vector add(Vector that) {
            return new Vector(this.x + that.x, this.y + that.y);
        }
        
        static Vector[] getPerpendicularUnit(Vector v) {
            Vector[] vecs = new Vector[2];
            double constant;
            if(v.x == 0) { 
                constant = 1.0 / Math.sqrt(1.0 + ((v.x * v.x) / (v.y * v.y)));
                vecs[0] = new Vector(constant, -constant * (v.x / v.y));
            }
            else {
                constant = 1.0 / Math.sqrt(1.0 + ((v.y * v.y) / (v.x * v.x)));
                vecs[0] = new Vector(-constant * (v.y / v.x), constant);
            }
            vecs[1] = new Vector(-vecs[0].x, -vecs[0].y);
            
            return vecs;
        }
        
        void multiply(double scalar) {
            x *= scalar;
            y *= scalar;
            norm *= scalar; 
        }
        
    }
	static class MyFreqMap  // Order statistics on elements
	{
		HashMap<Integer,Integer> freq;
		TreeMap<Integer,TreeSet<Integer>> inverse;
		MyFreqMap(){
			freq = new HashMap<>();
			inverse = new TreeMap<>();
		}

		void addIntoMap(int key,int val){
			TreeSet<Integer> set = inverse.get(key);
			if(set == null)
				set = new TreeSet<>();
			set.add(val);
			inverse.put(key, set);
		}
		void removeFromMap(int key1,int key2){
			TreeSet<Integer> set = inverse.get(key1);
			set.remove(key2);
			if(set.size() == 0)
				inverse.remove(key1);
			else
				inverse.put(key1, set);
		}
		void add(int elem){
			int oldFreq = freq.getOrDefault(elem, 0);
			if(oldFreq == 0){
				freq.put(elem, 1);
				addIntoMap(1, elem);
			}
			else{
				freq.put(elem, oldFreq + 1);
				removeFromMap(oldFreq, elem);
				addIntoMap(oldFreq + 1, elem);
			}

		}
		void remove(int elem){
			int oldFreq = freq.get(elem);
			if(oldFreq == 1){
				freq.remove(elem);
				removeFromMap(1, elem);
			}
			else{
				freq.put(elem, oldFreq - 1);
				removeFromMap(oldFreq, elem);
				addIntoMap(oldFreq - 1, elem);
			}
		}
		int getMaxFreqElem(){
			return inverse.lastEntry().getValue().first();
		}
		@Override
		public String toString() {
			return " freq = " + freq + " inv " + inverse;
		}
	}

	static class SuffixArray {  // Implemented for Upper case alphabets automatically appends ' $ ' 

		final char $ = '@';  // use ' ` ' for small case alphabets
		int equiClass[];
		int order[];		
		int N;

		int add(int a, int b){ return (a + b) % N; }
		int sub(int a ,int b){ return (N + a - b) % N; }

		int[] sortDoubled(int L){

			int newOrder[] = new int[N];
			int cnt[] = new int[N];
			for(int i=0;i<N;i++){
				int idx = sub(order[i], L);
				cnt[equiClass[idx]]++;
			}
			for(int i=1;i<N;i++)
				cnt[i] += cnt[i - 1];
			for(int i=N-1;i>=0;i--){
				int idx = sub(order[i], L);
				newOrder[--cnt[equiClass[idx]]] = idx;
			}

			return newOrder;

		}
		int[] updateEquiClass(int L , int newOrder[]){
			int newClass[] = new int[N];
			newClass[newOrder[0]] = 0;
			for(int i=1;i<N;i++){
				int f1 = equiClass[newOrder[i - 1]];
				int s1 = equiClass[add(newOrder[i - 1], L)];
				int f2 = equiClass[newOrder[i]];
				int s2 = equiClass[add(newOrder[i], L)];
				newClass[newOrder[i]] = newClass[newOrder[i - 1]] + (f1 == f2 && s1 == s2 ? 0 : 1); 
			}
			return newClass;
		}

		int[] getSuffixArray(){

			for(int L = 1;L < N;L <<= 1){
				int[] newOrder = sortDoubled(L);
				int[] newClass = updateEquiClass(L, newOrder);
				order = newOrder;
				equiClass = newClass;
			}
			return order;
		}

		SuffixArray(String str){

			str = str.concat(String.valueOf($));
			N = str.length();
			equiClass = new int[N];
			order = new int[N];


			int cnt[] = new int[27];
			for(int i=0;i<N;i++)
				cnt[str.charAt(i) - $]++;
			for(int i=1;i<27;i++)
				cnt[i] += cnt[i - 1];
			for(int i=N-1;i>=0;i--)
				order[--cnt[str.charAt(i) - $]] = i;
			equiClass[order[0]] = 0;
			for(int i=1;i<N;i++)
				equiClass[order[i]] = equiClass[order[i - 1]] + (str.charAt(order[i]) == str.charAt(order[i - 1]) 
				? 0 : 1 ); 
		}

	}

	/*
	 * Solves the TSP problem in O(N^2 * 2^N)
	 * memo[mask][prev] the shortest path using the set integers in mask where the last vertex visited in prev
	 */
	static int cost[][];
	static int memo[][];
	static int V;
	static int TSP(int mask , int prev) {

		if(memo[mask][prev] != -1)
			return memo[mask][prev];
		else {
			int dist = Integer.MAX_VALUE;
			for(int i = 0;i < V;i++)
				if(i != prev && (mask & (1 << i)) != 0)  // Also check for edge between i and prev
					dist = Math.min(dist,cost[i][prev] + TSP(mask ^ (1 << prev), i));

			return memo[mask][prev] = dist;
		}

	}
	
    static class FenwickTree {

        /****************
         * DONT USE BIT IF YOU UPDATE INDEX 0 (causes infinite loop)
         ******************/

        int tree[];
        int len;

        FenwickTree(int len) {
            this.len = len;
            tree = new int[len + 10];
        }

        void update(int idx, int val) {
            if (idx == 0)
                throw new IndexOutOfBoundsException("BIT IS NOT ZERO INDEXED");
            for (; idx <= len; idx += (idx & -idx))
                tree[idx] += val;
        }

        int query(int idx) {
            int sum = 0;
            for (; idx > 0; idx -= (idx & -idx))
                sum += tree[idx];

            return sum;
        }

        int query(int L, int R) {
            return query(R) - query(L - 1);
        }
    }

	/* LCA <NlogN , logN> dependency : level , log , V , DP = new int[log(V) + 1][V + 1];, parent (for the first level of DP) */
	static int DP[][]; // One based vertices
	static int level[];
	static int parent[];
	static int log(int N){
		return 31 - Integer.numberOfLeadingZeros(N);
	}
	static void binaryLift() {
		
		for(int i=1;i<=V;i++)
			DP[0][i] = parent[i];
		
		for (int i = 1; i < DP.length; i++) 
			for (int j = 1; j <= V; j++) 
				DP[i][j] = DP[i - 1][DP[i - 1][j]];

	}

	static int LCA(int u , int v){

		if(level[v] < level[u]){
			int temp = u;
			u = v;
			v = temp;
		}
		int diff = level[v] - level[u];
		while(diff > 0){        // Bring v to the same level as u
			int log = log(diff);
			v = DP[log][v];
			diff -= (1 << log);
		}
		while(u != v){
			int i = log(level[u]);
			for(;i > 0 && DP[i][u] == DP[i][v];)
				i--;

			u = DP[i][u];
			v = DP[i][v];
		}

		return u;
	}

	static class SegmentTree  { // Implemented to store min in a range , point update and range query
		int tree[];
		int len;
		int size;
		SegmentTree(int arr[]) { // arr should be a 1 based array
			len = arr.length - 1;
			size = 1 << (32 - Integer.numberOfLeadingZeros(len - 1) + 1);  // ceil(log(len)) + 1
			tree = new int[size];
			build(arr, 1, 1, len);
		}
		void update(int node,int idx,int val,int nl,int nr) {
			if(nl == nr && nl == idx)
				tree[node] = val;
			else {
				int mid = (nl + nr) >> 1;
				if(idx <= mid)
					update(2*node, idx , val ,nl , mid);
				else
					update((2*node) + 1, idx ,val , mid + 1, nr);

				tree[node] = Math.min(tree[2*node],tree[(2 * node) + 1]);
			}
		}
		void update(int idx , int val){
			update(1, idx, val, 1, len);
		}
		int query(int L , int R){
			return query(1, L, R, 1, len);
		}
		int query(int node , int L , int R, int nl, int nr) {
			int mid = (nl + nr) >> 1;
			if(nl == L && nr == R)
				return tree[node];
			else if(R <= mid)
				return query(2 * node, L, R, nl, mid);
			else if(L > mid)
				return query((2*node)+1, L, R, mid + 1 , nr);
			else
				return Math.min(query(2*node, L, mid , nl , mid) ,  query((2*node)+1, mid+1, R , mid+1,nr));
		}
		void build(int arr[],int node,int L,int R) {
			if(L == R)
				tree[node] = arr[L];
			else
			{
				int mid = (L + R) >> 1;
				build(arr, 2 * node, L, mid);
				build(arr, (2 * node) + 1, mid + 1, R);
				tree[node] = Math.min(tree[2*node] , tree[(2 * node) + 1]);
			}
		}
	}
	
	static class SCC {

		/*
		 * Kosaraju-Sharir Algorithm
		 * 
		 * Identify sinks (reverse post order in inverse graph)
		 * Start normal dfs from the above order , the resulting components form SCC
		 * 
		 * If you want to use 1 based indexing set onBased flag to true
		 * 
		 */

		private ArrayList<Integer>[] invGraph;
		private ArrayDeque<Integer> stack;
		private Iterable<Integer>[] adj;
		private int group[];
		private boolean marked[];
		private int numOfComponents;
		private int st , V;


		@SuppressWarnings("unchecked")
		SCC(Iterable<Integer>[] adj , boolean oneBased) {
			st = oneBased ? 1 : 0;
			V = adj.length - st; 
			group = new int[V + st];
			this.adj = adj;
			invGraph = new ArrayList[V + st];
			for(int i=st;i<V + st;i++)
				invGraph[i] = new ArrayList<>();
			for(int i=st;i<V + st;i++)
				for(int j : adj[i])
					invGraph[j].add(i);

			marked = new boolean[V + st];
			stack = new ArrayDeque<>();
			for(int i=st;i<V + st;i++)
				if(!marked[i])
					reversePostOrder(i);

			marked = new boolean[V + st];
			int grp = 0;
			for(int i : stack)
				if(!marked[i])
					dfs(i, grp++);

			numOfComponents = grp;
			stack = null;
		}

		private void reversePostOrder(int u) {
			marked[u] = true;
			for(int v : invGraph[u])
				if(!marked[v])
					reversePostOrder(v);
			stack.push(u);
		}

		private void dfs(int u , int grp) {
			marked[u] = true;
			group[u] = grp;
			for(int v : adj[u])
				if(!marked[v])
					dfs(v, grp);
		}
		public int[] getSCC() {
			return group;
		}
		public int numberOfComponents() {
			return numOfComponents;
		}

		@SuppressWarnings("unchecked")
		public HashSet<Integer>[] getDAG() {
			HashSet<Integer>[] dag = new HashSet[numOfComponents];
			for(int i=0;i<numOfComponents;i++)
				dag[i] = new HashSet<>();
			for(int i=st;i<V + st;i++)
				for(int v : adj[i])
					if(group[i] != group[v])
						dag[group[i]].add(group[v]);

			return dag;
		}
	}


	static class BellmanFordSSSP { // Dependency : Edge class
		
		static class Edge { // Take this Edge class to the outer class
			int v;
			long cost;
			Edge(int vv, long wt) {
				v = vv;
				cost = wt;
			}
		}
		
		static final long INF = (long)3e18;
		long distTo[];
		ArrayList<Edge>[] adj;
		int V, st;
		
		public BellmanFordSSSP(ArrayList<Edge>[] adj , int V , int start, boolean oneBased) {
			
			this.V = V;
			this.adj = adj;
			st = oneBased ? 1 : 0;
			distTo = new long[V + st];
			
			Arrays.fill(distTo, INF);
			distTo[start] = 0;
			
			for(int i=1;i<=V-1;i++) 
				for(int u=st;u<V+st;u++)
					for(Edge e : adj[u])
						distTo[e.v] = Math.abs(distTo[u]) == INF ? distTo[e.v] :Math.max(-INF,Math.min(distTo[e.v],distTo[u] + e.cost));
			
		}
		
		// Checks wether the graph contains a negative cycle or not
		
		boolean containsNegativeCycle() {
			for(int u=st;u<V+st;u++)
				for(Edge e : adj[u])
					if(distTo[u] != INF && distTo[u] + e.cost < distTo[e.v])
						return true;
			
			return false;
		}
		
		// Sets true for all vertices which are rechable from any negative cycle or null otherwise
		
		boolean[] reachableFromNegativeCycle() {
			if(!containsNegativeCycle())
				return null;
			else {
				ArrayDeque<Integer> queue = new ArrayDeque<>();
				boolean cycleMarked[] = new boolean[V + st];
				for(int u=st;u<V+st;u++)
					for(Edge e : adj[u])
						if(distTo[u] != INF && distTo[u] + e.cost < distTo[e.v] && !cycleMarked[u]) {
							cycleMarked[u] = true;
							queue.add(u);
						}
				
				while(!queue.isEmpty()) {
					int u = queue.remove();
					for(Edge e : adj[u])
						if(!cycleMarked[e.v]) {
							cycleMarked[e.v] = true;
							queue.add(e.v);
						}
				}
				return cycleMarked;
			}
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
    
    static class BinaryTrie {

        static class Node {
            int size;
            Node zero;
            Node one;
            Node() {
                size = 1;   // Take this into consideration
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
    }
    
    static int[] KMPPrefixFunction(char str[]) {
        int n = str.length;
        int prefix[] = new int[n];  // Stores the length of largest border for a prefix 
        for(int i = 1; i < n; i++) {
            int border;
            for(border = prefix[i - 1]; border > 0 && str[border] != str[i]; border = prefix[border - 1])
                ;
            prefix[i] = str[i] == str[border] ? border + 1: 0;
        }
        
        return prefix;
    }
    
    static int[][] packU(int n, int[] from, int[] to , int isOneBased) {    // Courtesy : UWI ( adjacency list using Jagged Arrays )
        int[][] g = new int[n + isOneBased][];
        int[] p = new int[n + isOneBased];
        for (int f : from)
            p[f]++;
        for (int t : to)
            p[t]++;
        for (int i = 0 + isOneBased; i < n + isOneBased; i++)
            g[i] = new int[p[i]];
        for (int i = 0; i < from.length; i++) {
            g[from[i]][--p[from[i]]] = to[i];
            g[to[i]][--p[to[i]]] = from[i];
        }
        return g;
    }
    
    static final double EPS = 1e-8;
    static int compare(double a , double b) {
        if(a <= b - EPS)
            return -1;
        else if(a >= b + EPS)
            return 1;
        else
            return 0;
    }
    
}