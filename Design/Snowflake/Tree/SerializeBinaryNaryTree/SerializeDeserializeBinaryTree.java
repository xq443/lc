package Snowflake.Tree.SerializeBinaryNaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class SerializeDeserializeBinaryTree {
    public String serialize(TreeNode root) {
        if(root == null) return "";
        StringBuilder ret = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if(curr == null) ret.append("null,");
            else {
                ret.append(String.valueOf(curr.val)).append(",");
                queue.offer(curr.left);
                queue.offer(curr.right);
            }
        }
        ret.deleteCharAt(ret.length()-1); // remove the trailing comma
        return ret.toString();
    }

    public TreeNode deserialize(String data) {
        if(data == null || data.isEmpty()) return null;
        String[] tokens = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(tokens[0]));

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while(i < tokens.length) {
            TreeNode curr = queue.poll();
            if(!tokens[i].equals("null")) {
                assert curr != null;
                curr.left = new TreeNode(Integer.parseInt(tokens[i]));
                queue.add(curr.left);
            }
            i++;

            if(i < tokens.length && !tokens[i].equals("null")) {
                assert curr != null;
                curr.right = new TreeNode(Integer.parseInt(tokens[i]));
                queue.add(curr.right);
            }
            i++;
        }
        return root;
    }

    public void inOrder(TreeNode root) {
        if(root == null) return;
        inOrder(root.left);
        System.out.print(root.val + " ");
        inOrder(root.right);
    }

    public static void main(String[] args) {
        SerializeDeserializeBinaryTree s = new SerializeDeserializeBinaryTree();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(6);
        System.out.println(s.serialize(root));
        s.inOrder(s.deserialize(s.serialize(root)));
    }
}
