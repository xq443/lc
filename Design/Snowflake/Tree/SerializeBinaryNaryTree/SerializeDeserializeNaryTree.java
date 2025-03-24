package Snowflake.Tree.SerializeBinaryNaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SerializeDeserializeNaryTree {
    public static class Node {
        int val;
        List<Node> children = new ArrayList<>();

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, List<Node> children) {
            this.val = val;
            this.children = children;
        }

        public void add(Node child) {
            children.add(child);
        }
    }

    public String serialize(Node root) {
        if(root == null) return "";
        StringBuilder ret = new StringBuilder();
        Queue<Node> queue = new LinkedList<>();
        ret.append(root.val);
        queue.add(root);
        while(!queue.isEmpty()) {
            Node curr = queue.poll();
            ret.append(" ");
            ret.append(curr.children.size());
            for(Node next : curr.children) {
                ret.append(" ");
                ret.append(next.val);
                queue.add(next);
            }
        }
        return ret.toString();
    }

    public Node deserialize(String data) {
        if(data == null || data.isEmpty()) return null;
        String[] tokens = data.split(" ");
        Node root = new Node(Integer.parseInt(tokens[0]), new ArrayList<>());

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while(i < tokens.length) {
            int size = Integer.parseInt(tokens[i++]);
            Node curr = queue.poll();
            for(int j = 0; j < size; j++) {
                Node next = new Node(Integer.parseInt(tokens[i++]), new ArrayList<>());
                assert curr != null;
                curr.children.add(next);
                queue.add(next);
            }
        }
        return root;
    }

    public void print(Node root) {
        if(root == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            Node curr = queue.poll();
            System.out.println(curr.val);
            for(Node next : curr.children) {
                queue.add(next);
            }
        }
    }

    public static void main(String[] args) {
        SerializeDeserializeNaryTree s = new SerializeDeserializeNaryTree();
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

        System.out.println(s.serialize(root));
        s.print(s.deserialize(s.serialize(root)));
    }
}
