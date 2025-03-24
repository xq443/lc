package Snowflake.Tree.SerializeBinaryNaryTree;

import java.util.ArrayList;
import java.util.List;

public class DiametryNaryTree {
    public static class Node {
        int val;
        List<Node> children;

        public Node(int val) {
            this.val = val;
            this.children = new ArrayList<>();
        }

        public void add(Node child) {
            children.add(child);
        }
    }
    int ret;
    public int diameter(Node root) {
        this.ret = 0;
        if (root == null) return 0;
        dfs(root);
        return ret;
    }

    private int dfs(Node root) {
        if (root == null) return 0;
        int maxDepth = 0, secondDepth = 0;
        for(Node child : root.children) {
            int curr = dfs(child);
            if(curr > maxDepth) {
                secondDepth = maxDepth;
                maxDepth = curr;
            } else if (curr > secondDepth) {
                secondDepth = curr;
            }
        }
        // Update the diameter (sum of the longest two child paths)
        ret = Math.max(ret, maxDepth + secondDepth);
        return maxDepth + 1;
    }

    public static void main(String[] args) {
        DiametryNaryTree d = new DiametryNaryTree();
        // Create nodes for the example tree
        Node root = new Node(1);
        Node child1 = new Node(2);
        Node child2 = new Node(3);
        Node child3 = new Node(4);
        Node child4 = new Node(5);

        // Building the n-ary tree
        root.add(child1);
        root.add(child2);
        root.add(child3);
        child3.add(child4);

        System.out.println(d.diameter(root));
    }
}
