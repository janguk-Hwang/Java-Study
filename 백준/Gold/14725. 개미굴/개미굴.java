import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    static String[] path;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        Node root = new Node();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            k = Integer.parseInt(st.nextToken());
            path = new String[k];
            for(int j=0; j<k; j++) path[j] = st.nextToken();
            insert(root, path);
        }
        print(root, 0);
    }

    static class Node {
        Map<String, Node> children = new TreeMap<>();
    }

    static void insert(Node root, String[] path) {
        Node current = root;
        for(String food : path){
            current.children.putIfAbsent(food, new Node());
            current = current.children.get(food);
        }
    }

    static void print(Node node, int depth) {
        for (String key : node.children.keySet()) {
            System.out.println("--".repeat(depth) + key);
            print(node.children.get(key), depth + 1);
        }
    }
}