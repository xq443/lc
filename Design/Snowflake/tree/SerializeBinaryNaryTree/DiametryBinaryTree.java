package Snowflake.tree.SerializeBinaryNaryTree;

public class DiametryBinaryTree {
    int ret;
    public int diameterOfBinaryTree(TreeNode root) {
        this.ret = 0;
        if (root == null) return 0;
        dfs(root);
        return ret;
    }

    public int dfs(TreeNode root) {
        if (root == null) return 0;
        int left = dfs(root.left);
        int right = dfs(root.right);
        ret = Math.max(ret, left + right);
        return 1 + Math.max(left, right);
    }

    public static void main(String[] args) {
        DiametryBinaryTree f = new DiametryBinaryTree();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(6);
        System.out.println(f.diameterOfBinaryTree(root));
    }
}
