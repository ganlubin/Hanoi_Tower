import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Algorithms {
    public static Algorithms.InputReader in = new Algorithms.InputReader();
    public static PrintWriter out;
    public static final int N = 123456;
    public static final int[] dx;
    public static final int[] dy;
    public static final int INF = 1061109567;
    public static int[] primes;
    public static int[] e;
    public static int[] ne;
    public static int[] h;
    public static int[] w;
    public static int idx;
    public static int[] p;
    public static Edge[] edges;
    public static int[] fact;
    public static int[] infact;

    static {
        out = new PrintWriter(System.out);
        dx = new int[]{1, 0, -1, 0};
        dy = new int[]{0, 1, 0, -1};
        primes = new int[123456];
        e = new int[123456];
        ne = new int[123456];
        h = new int[123456];
        w = new int[123456];
        p = new int[123456];
        edges = new Edge[246912];
        fact = new int[100005];
        infact = new int[100005];
    }


    public static void main(String[] args) throws IOException {

        int n = in.nextInt();
        int m = in.nextInt();
        for (int i = 1; i <= n; i++) {
            p[i] = i;
        }
        for (int i = 0; i < m; i++) {
            String input[] = in.next().split(" ");
            int x = Integer.parseInt(input[1]);
            int y = Integer.parseInt(input[2]);
            if (input[0].equals("M")) {
                int a = find(x);
                int b = find(y);
                if (a != b) {
                    p[a] = b;
                }
            } else {
                out.println(find(x) == find(y) ? "Yes" : "No");
            }
        }

        out.flush();
    }

    public static long jiecheng(long x) {
        if (x == 0) return 1;
        long res = 1;
        for (long i = 2; i <= x; i++) {
            res *= i;
        }
        return res;
    }

    public static boolean isSquare(long num) {
        double a = 0;
        try {
            a = Math.sqrt(num);
        } catch (Exception e) {
            return false;
        }
        long b = (long) a;
        return a - b == 0;
    }

    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static int get_lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    public static long get_inverse(int a, int p) {
        if (a % p == 0) {
            return -1L;
        } else {
            long res = pow(a, p - 2, p);
            return res % (long) p;
        }
    }

    public static void Cab(int p) {
        fact[0] = infact[0] = 1;

        for (int i = 1; i < 100005; ++i) {
            fact[i] = (int) ((long) fact[i - 1] * (long) i % (long) p);
            infact[i] = (int) ((long) infact[i - 1] * pow(i, p - 2, p) % (long) p);
        }

    }

    public static long pow(long a, long k, long mod) {
        long res;
        if (k == 0) return 1L;
        for (res = 1L; k != 0; a = a * a % mod) {
            if ((k & 1) == 1) {
                res = a * res % mod;
            }

            k >>= 1;
        }

        return res;
    }

    static double oula(int x) {
        int k = 0;
        double res = (double) x;
        int[] oula = new int[1010];

        int i;
        for (i = 2; i <= x / i; ++i) {
            if (x % i == 0) {
                for (oula[k++] = i; x % i == 0; x /= i) {
                }
            }
        }

        if (x > 1) {
            oula[k++] = x;
        }

        for (i = 0; i < k; ++i) {
            res *= 1.0D - 1.0D / (double) oula[i];
        }

        return res;
    }

    public static ArrayList<Integer> getfs(int x) {
        ArrayList<Integer> res = new ArrayList();

        for (int i = 1; i <= x / i; ++i) {
            if (x % i == 0) {
                res.add(i);
                if (i != x / i) {
                    res.add(x / i);
                }
            }
        }

        Collections.sort(res);
        return res;
    }

    public static boolean is_Prime(int x) {
        if (x <= 2) {
            return x == 2;
        } else {
            for (int i = 2; i <= x / i; ++i) {
                if (x % i == 0) {
                    return false;
                }
            }

            return true;
        }
    }

    private static void getPrimes(int n) {
        int cnt = 0;
        boolean[] st = new boolean[123456];

        for (int i = 2; i <= n; ++i) {
            if (!st[i]) {
                primes[cnt++] = i;
            }

            for (int j = 0; primes[j] <= n / i; ++j) {
                st[primes[j] * i] = true;
                if (i % primes[j] == 0) {
                    break;
                }
            }
        }

    }

    public static void dandiaoQueue(int[] a, int k) {
        Deque<Integer> dandiaoQueue = new ArrayDeque();
        int n = a.length;

        for (int i = 0; i < n; ++i) {
            if (!dandiaoQueue.isEmpty() && i - k + 1 > (Integer) dandiaoQueue.peekFirst()) {
                dandiaoQueue.pollFirst();
            }

            while (!dandiaoQueue.isEmpty() && a[i] <= a[(Integer) dandiaoQueue.peekLast()]) {
                dandiaoQueue.pollLast();
            }

            dandiaoQueue.addLast(i);
            if (!dandiaoQueue.isEmpty() && i >= k - 1) {
                out.print(a[(Integer) dandiaoQueue.peekFirst()] + " ");
            }
        }

    }

    public static int dijkstra(int n) {
        Arrays.fill(h, -1);
        int[] dist = new int[123456];
        boolean[] st = new boolean[123456];
        Arrays.fill(dist, 1061109567);
        PriorityQueue<Pair> heap = new PriorityQueue(new Comparator<Pair>() {
            public int compare(Pair t1, Pair t2) {
                return t1.x - t2.x;
            }
        });
        heap.offer(new Pair(0, 1));

        while (true) {
            int ver;
            int distance;
            do {
                if (heap.isEmpty()) {
                    if (dist[n] == 1061109567) {
                        return -1;
                    }

                    return dist[n];
                }

                Pair t = (Pair) heap.poll();
                ver = t.y;
                distance = t.x;
            } while (st[ver]);

            st[ver] = true;

            for (int i = h[ver]; i != -1; i = ne[i]) {
                int j = e[i];
                if (dist[j] > distance + w[i]) {
                    dist[j] = distance + w[i];
                    heap.offer(new Pair(dist[j], j));
                }
            }
        }
    }

    public static void add(int a, int b, int c) {
        e[idx] = b;
        w[idx] = c;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    public static void KMP(String input1, String input2) {
        int n = input1.length();
        int m = input2.length();
        char[] longer = new char[m + 2];
        char[] shorter = new char[m + 2];
        int[] next = new int[n + 2];

        int i;
        for (i = 1; i <= m; ++i) {
            longer[i] = input2.charAt(i - 1);
        }

        for (i = 1; i <= n; ++i) {
            shorter[i] = input1.charAt(i - 1);
        }

        i = 2;

        int j;
        for (j = 0; i <= n; ++i) {
            while (j != 0 && shorter[i] != shorter[j + 1]) {
                j = next[j];
            }

            if (shorter[i] == shorter[j + 1]) {
                ++j;
            }

            next[i] = j;
        }

        i = 1;

        for (j = 0; i <= m; ++i) {
            while (j != 0 && longer[i] != shorter[j + 1]) {
                j = next[j];
            }

            if (longer[i] == shorter[j + 1]) {
                ++j;
            }

            if (j == n) {
                out.print(i - n + " ");
                j = next[j];
            }
        }

    }

    public static int bfs(boolean[][] g, int n, int m) {
        int[][] dist = new int[n + 1][m + 1];
        Queue<Pair> queue = new ArrayDeque();
        dist[1][1] = 0;
        queue.add(new Pair(1, 1));

        int i;
        for (int j = 1; j <= n; ++j) {
            for (j = 1; j <= m; ++j) {
                dist[j][j] = -1;
            }
        }

        while (!queue.isEmpty()) {
            Pair temp = (Pair) queue.poll();

            for (i = 0; i < 4; ++i) {
                int _x = temp.x + dx[i];
                int _y = temp.y + dy[i];
                if (_x >= 1 && _y >= 1 && _x <= n && _y <= m && !g[_x][_y] && dist[_x][_y] == -1) {
                    dist[_x][_y] = dist[temp.x][temp.y] + 1;
                    queue.add(new Pair(_x, _y));
                }
            }
        }

        return dist[n][m] + 1;
    }

    public static void kruskal(int n, int m) {
        int res;
        for (res = 0; res < n; p[res] = res++) {
        }

        Arrays.sort(edges, 0, m);
        res = 0;
        int cnt = 0;

        for (int i = 0; i < m; ++i) {
            int a = edges[i].a;
            int b = edges[i].b;
            int c = edges[i].c;
            if (!judge(a, b)) {
                res += c;
                ++cnt;
                p[find(a)] = find(b);
            }
        }

        if (cnt < n - 1) {
            out.println("impossible");
        } else {
            out.println(res);
        }

    }

    public static int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }

        return p[x];
    }

    public static boolean judge(int a, int b) {
        return find(a) == find(b);
    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader() {
            this.reader = new BufferedReader(new InputStreamReader(System.in), 32768);
            this.tokenizer = null;
        }

        public String next() {
            while (this.tokenizer == null || !this.tokenizer.hasMoreTokens()) {
                try {
                    this.tokenizer = new StringTokenizer(this.reader.readLine());
                } catch (IOException var2) {
                    throw new RuntimeException(var2);
                }
            }

            return this.tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(this.next());
        }

        public long nextLong() {
            return Long.parseLong(this.next());
        }

        public float nextFloat() {
            return Float.parseFloat(this.next());
        }
    }
}


class Edge implements Comparable<Edge> {
    int a;
    int b;
    int c;

    public Edge(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int compareTo(Edge o) {
        return this.c - o.c;
    }
}

class Pair {
    int x;
    int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
