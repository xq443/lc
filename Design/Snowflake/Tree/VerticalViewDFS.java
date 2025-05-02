import java.util.*;

public class VerticalViewDFS {
    Map<Integer, Map<Integer, List<Integer>>> colNodes;
    public List<List<Integer>> verticalOrder(TreeNode root) {
        this.colNodes = new TreeMap<>();
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) return ret;
        dfs(root, 0, 0);

        for(Map<Integer, List<Integer>> entry : colNodes.values()) {
            List<Integer> sub = new ArrayList<>();
            for(List<Integer> vals : entry.values()) {
                sub.addAll(vals);
            }
            ret.add(sub);
        }
        return ret;
    }

    public void dfs(TreeNode root, int col, int row) {
        if (root == null) return;
        colNodes.putIfAbsent(col, new TreeMap<>());
        colNodes.get(col).putIfAbsent(row, new ArrayList<>());
        colNodes.get(col).get(row).add(root.val);
        dfs(root.left, col - 1, row + 1);
        dfs(root.right, col + 1, row + 1);
    }

    public static void main(String[] args) {
        VerticalViewDFS vvdfs = new VerticalViewDFS();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(6);
        System.out.println(vvdfs.verticalOrder(root));
    }

    // TC: O(NLogN)
    // SC: O(N)
}
