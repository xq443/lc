package Snowflake.Tree.SerializeBinaryNaryTree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SerializeDeserializeBinaryTreeDFS {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    // Serialize the tree
    public String serialize(TreeNode root) {
        StringBuilder ret = new StringBuilder();
        serializedfs(root, ret);
        return ret.toString();
    }

    // Helper method for serialization
    public void serializedfs(TreeNode root, StringBuilder ret) {
        if (root == null) {
            ret.append("# ");
            return;
        }
        ret.append(root.val).append(" ");
        serializedfs(root.left, ret);
        serializedfs(root.right, ret);
    }

    // Deserialize the tree
    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) return null;
        Queue<String> q = new LinkedList<>(Arrays.asList(data.split(" ")));
        return deserializedfs(q);
    }

    // Helper method for deserialization
    public TreeNode deserializedfs(Queue<String> q) {
        if (q.isEmpty()) return null;
        String token = q.poll();
        if (token.equals("#")) return null;  // # represents a null node

        TreeNode root = new TreeNode(Integer.parseInt(token));

        // Deserialize left and right children
        root.left = deserializedfs(q);
        root.right = deserializedfs(q);

        return root;
    }

    public void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    public static void main(String[] args) {
        SerializeDeserializeBinaryTreeDFS solution = new SerializeDeserializeBinaryTreeDFS();

        // Example to test serialization and deserialization
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        String serializedData = solution.serialize(root);
        System.out.println("Serialized: " + serializedData);

        TreeNode deserializedRoot = solution.deserialize(serializedData);
        solution.inorder(deserializedRoot);  // Output the root of the deserialized tree
    }
}
