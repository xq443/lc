package Snowflake;

import java.util.LinkedList;
import java.util.Queue;

public class DeleteNodeBST {
    public static class Node {
        int val;
        Node left, right;

        public Node(int val) {
            this.val = val;
        }
    }

    public Node delete(Node root, int key) {
        int before = getHeight(root);
        root = deleteNode(root, key);
        int after = getHeight(root);

        System.out.println("Height before deletion: " + before);
        System.out.println("Height after deletion: " + after);

        return root;
    }

    public Node deleteNode(Node root, int key) {
        if(root == null) return null;
        if(root.val < key) {
            root.right = deleteNode(root.right, key);
        } else if(root.val > key) {
            root.left = deleteNode(root.left, key);
        } else {
            if(root.left == null) root = root.right;
            else if(root.right == null) root = root.left;
            else {
                Node min = root.right;
                while(min.left != null) min = min.left;
                root.val = min.val;
                root.right = deleteNode(root.right, min.val);
            }
        }
        return root;
    }

    public int getHeight(Node root) {
        if(root == null) return 0;
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }

    public void printLevelOrder(Node root) {
        if(root == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.val);
            if(cur.left != null) queue.add(cur.left);
            if(cur.right != null) queue.add(cur.right);
        }
    }

    public static void main(String[] args) {
        DeleteNodeBST d = new DeleteNodeBST();
        Node root = new Node(5);
        root.left = new Node(3);
        root.right = new Node(6);
        root.left.left = new Node(2);
        root.left.right = new Node(4);
        root.right.right = new Node(7);

        d.printLevelOrder(d.delete(root, 3));
    }
}
/**
 * deleteHelper	O(H) → O(log N) (best) / O(N) (worst)	O(H) → O(log N) (best) / O(N) (worst)
 * getHeight	O(N)	O(H) → O(log N) (best) / O(N) (worst)
 * Total (worst case)	O(N) + O(N) = O(N)	O(N)
 */