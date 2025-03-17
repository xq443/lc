package Snowflake.tree;

import java.util.*;

public class GetAllChildren {
  private final Map<Integer, List<Integer>> graph = new HashMap<>();
  private final Map<Integer, Set<Integer>> cache = new HashMap<>(); // 记忆化缓存

  public void addEdge(int parent, int child) {
    graph.computeIfAbsent(parent, k -> new ArrayList<>()).add(child);
  }

  public Set<Integer> getAllChildren(int node) {
    if (cache.containsKey(node)) {
      return cache.get(node);
    }

    Set<Integer> result = new HashSet<>();
    if (graph.containsKey(node)) {
      for (int child : graph.get(node)) {
        result.add(child);
        result.addAll(getAllChildren(child)); // 递归合并子节点
      }
    }

    cache.put(node, result); // 记忆化缓存
    return result;
  }

  public static void main(String[] args) {
    GetAllChildren g = new GetAllChildren();
    g.addEdge(1, 2);
    g.addEdge(1, 3);
    g.addEdge(2, 4);
    g.addEdge(2, 5);
    g.addEdge(3, 5);
    g.addEdge(3, 6);

    System.out.println(g.getAllChildren(1)); // 预期输出 {2, 3, 4, 5, 6}
  }
}
