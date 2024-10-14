import java.util.Scanner;

class BinaryTree {
    static class Node {
        char data;
        Node left, right;

        Node(char data) {
            this.data = data;
        }
    }

    private Node root;

    public void addNode(char data, char leftData, char rightData) {
        if (root == null) {
            root = new Node(data);
            if (leftData != '.') root.left = new Node(leftData);
            if (rightData != '.') root.right = new Node(rightData);
        } else {
            searchAndAdd(root, data, leftData, rightData);
        }
    }

    private void searchAndAdd(Node node, char data, char leftData, char rightData) {
        if (node == null) return;

        if (node.data == data) {
            if (leftData != '.') node.left = new Node(leftData);
            if (rightData != '.') node.right = new Node(rightData);
        } else {
            searchAndAdd(node.left, data, leftData, rightData);
            searchAndAdd(node.right, data, leftData, rightData);
        }
    }

    //전위 순회
    public void preorder(Node node) {
        if (node == null) return;
        System.out.print(node.data);
        preorder(node.left);
        preorder(node.right);
    }

    //중위 순회
    public void inorder(Node node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.data);
        inorder(node.right);
    }

    //후위 순회
    public void postorder(Node node) {
        if (node == null) return;
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.data);
    }

    public Node getRoot() {
        return root;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        BinaryTree tree = new BinaryTree();

        for (int i = 0; i < n; i++) {
            char root = sc.next().charAt(0);
            char left = sc.next().charAt(0);
            char right = sc.next().charAt(0);
            tree.addNode(root, left, right);
        }

        tree.preorder(tree.getRoot());
        System.out.println();

        tree.inorder(tree.getRoot());
        System.out.println();

        tree.postorder(tree.getRoot());
        System.out.println();

        sc.close();
    }
}