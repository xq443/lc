package Snowflake.Tree.SerializeBinaryNaryTree;

import java.util.*;

public class SerializeDeserializeNaryTreeDFS {
    static class Node {
        int val;
        List<Node> children = new ArrayList<>();

        public Node(List<Node> children, int val) {
            this.children = children;
            this.val = val;
        }

        public Node(int val) {
            this.val = val;
        }

        public Node() {}

        public void add(Node child) {
            children.add(child);
        }
    }
    public String serialize(Node root) {
        StringBuilder ret = new StringBuilder();
        serializedfs(ret, root);
        return ret.toString();
    }
    public void serializedfs(StringBuilder ret, Node root) {
        if (root == null) return;
        ret.append(root.val).append(" ");
        for (Node child : root.children) {
            serializedfs(ret, child);
        }
        ret.append("# ");
    }

    public Node deserialize(String data) {
        if(data == null || data.isEmpty()) return null;
        Queue<String> tokens = new LinkedList<>(Arrays.asList(data.split(" ")));
        return deserializedfs(tokens);
    }

    public Node deserializedfs(Queue<String> tokens) {
        if (tokens.isEmpty()) return null;
        String token = tokens.poll();
        if (token.equals("#")) return null;

        Node curr = new Node(new ArrayList<>(), Integer.parseInt(token));
        while (!tokens.isEmpty()) {
            Node child = deserializedfs(tokens);
            if(child == null) break;
            curr.children.add(child);
        }
        return curr;
    }

    public static void main(String[] args) {
        SerializeDeserializeNaryTreeDFS s = new SerializeDeserializeNaryTreeDFS();
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
    }
    // 1 2 # 3 # 4 5 # # #
}
