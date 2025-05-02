public class GraphValidTreeUnionFind {
    static class UnionFind {
        int[] parent;

        public UnionFind(int n) {
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int root(int a) {
            if(a == parent[a]) return a;
            parent[a] = root(parent[a]);
            return parent[a];
        }

        public boolean connected(int a, int b) {
            int rootA = root(a);
            int rootB = root(b);
            if(rootA == rootB) return true;
            parent[rootA] = rootB;
            return false;
        }
    }

    public boolean validTree(int n, int[][] edges) {
        if(edges.length != n - 1) return false;
        UnionFind uf = new UnionFind(n);
        for(int[] edge: edges) {
            if(uf.connected(edge[0], edge[1])) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        GraphValidTreeUnionFind gs = new GraphValidTreeUnionFind();
        int[][] edges = new int[][]{{0,1},{0,2},{0,3},{1,4}};
        System.out.println(gs.validTree(5, edges));
    }
}
