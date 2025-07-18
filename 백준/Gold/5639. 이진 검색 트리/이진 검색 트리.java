import java.io.*;
import java.util.*;

// 점점 작아지는 수들은 모두 왼쪽 서브트리를 선택한 것이다. 감소가 끝난 이후부터 root보다 작을 때까지는 오른쪽 서브트리를 의미한다.
// 루트 기준으로 오른쪽도 작아지는 경우는 왼쪽을 선택한 경우, 감소가 끝나고 나서는 오른쪽을 선택한 경우
public class Main {
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        List<Integer> list = new ArrayList<>();
        String input;
        while((input = br.readLine()) != null && !input.isEmpty()){
            list.add(Integer.parseInt(input));
        }
        if(list.isEmpty()) return;
        Node rootNode = new Node(list.get(0));
        for(int i=1; i<list.size(); i++) rootNode.insert(list.get(i));
        postOrder(rootNode);
        System.out.print(sb);
    }

    public static void postOrder(Node node){
        if(node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        sb.append(node.node).append("\n");
    }

    public static class Node{
        int node;
        Node left, right;
        Node(int node){
            this.node = node;
        }
        void insert(int newNode){
            if(newNode < node){
                if(left == null){
                    left = new Node(newNode);
                    return;
                }
                left.insert(newNode);
                return;
            }
            if(right == null){
                right = new Node(newNode);
                return;
            }
            right.insert(newNode);
        }
    }
}