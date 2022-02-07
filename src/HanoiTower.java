import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
public class HanoiTower {

//    public static InputReader in = new InputReader();
//    public static PrintWriter out = new PrintWriter(System.out);
    static int a[] = new int[123456], n;

    public static void main(String[] args) {

        new Tower().move(3, 'a', 'b', 'c'); // Num means the number of disks

    }

}

class Tower {
    public void move(int num, char a, char b, char c) {
        if (num == 1) {
            System.out.println(a + " -> " + c);
        } else {
            move(num - 1, a, c, b);
            System.out.println(a + " -> " + c);
            move(num - 1, b, a, c);
        }
    }
}
/*
class InputReader {
    public BufferedReader reader;
    public StringTokenizer tokenizer;

    public InputReader() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.tokenizer = null;
    }

    public String next() {
        while (this.tokenizer == null || !this.tokenizer.hasMoreTokens()) {
            try {
                this.tokenizer = new StringTokenizer(this.reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return this.tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(this.next());
    }

    public float nextFloat() {
        return Float.parseFloat(this.next());
    }

    public long nextLong() {
        return Long.parseLong(this.next());
    }

}
*/