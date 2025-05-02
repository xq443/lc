import java.util.ArrayList;
import java.util.List;

public class PrintBinaryTree {
    public List<List<String>> printTree(TreeNode root) {
        List<List<String>> ret = new ArrayList<>();
        int height = getHeight(root);
        int cnt = (1 << height) - 1; // 2 ^ h - 1

        for(int i = 0; i < height; i++) {
            List<String> list = new ArrayList<>();
            for(int j = 0; j < cnt; j++) {
                list.add("");
            }
            ret.add(list);
        }

        fillUp(root, ret, 0, cnt - 1, 0);
        return ret;
    }

    public void fillUp(TreeNode root, List<List<String>> ret, int left, int right, int height) {
        if(root == null) return;
        int mid = (left + right) / 2;
        ret.get(height).set(mid, String.valueOf(root.val));
        fillUp(root.left, ret, left, mid - 1, height + 1);
        fillUp(root.right, ret, mid + 1, right, height + 1);
    }

    public int getHeight(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }

    public static void main(String[] args) {
        PrintBinaryTree p = new PrintBinaryTree();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        System.out.println(p.printTree(root));
    }
}
