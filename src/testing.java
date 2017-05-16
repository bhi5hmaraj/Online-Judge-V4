import java.io.*;

class Main {
    public static void main(String args[]) throws IOException {
        StringBuilder sb = new StringBuilder("abs");
        sb.insert(0, 'z');
        sb.insert(sb.length(), 'x');
        System.out.println(sb);
    }
}