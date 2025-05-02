public class NumberofGoodLeafNodesPairs {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    int ret;
    int dis;
    public int countPairs(TreeNode root, int distance) {
        this.dis = distance;
        dfs(root);
        return ret;
    }

    public int[] dfs(TreeNode root) {
        int[] distance = new int[dis + 1];
        if(root == null) return distance;
        if(root.left == null && root.right == null) {
            distance[0] = 1;
            return distance;
        }

        // fl[i]: number of leaf nodes in the left subtree that are i edges away from the left child.
        // fr[i]: number of leaf nodes in the right subtree that are i edges away from the right child.
        // f[i + 1]: number of leaf nodes that are i + 1 edges away from the current node.
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);
        for(int i = 0; i + 1 <= dis; i++) {
            for(int j = 0; i + j + 2 <= dis; j++) {
                ret += left[i] * right[j];
            }
        }

        for(int i = 0; i + 1 <= dis; i++) {
            distance[i + 1] = left[i] + right[i];
        }
        return distance;
    }

    public static void main(String[] args) {
        NumberofGoodLeafNodesPairs n = new NumberofGoodLeafNodesPairs();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        System.out.println(n.countPairs(root, 3));
    }

    // TC: O(N * dis^2)
    // SC:  O(dis + height) -> O(logN + N)
}
