package Snowflake.graph;

import java.util.*;

public class AllRecipesFromSuppliesDFS {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Map<String, List<String>> adj = new HashMap<>();
        Map<String, Integer> inDegree = new HashMap<>();
        for(int i = 0; i < recipes.length; i++) {
            for(int j = 0; j < ingredients.size(); j++) {
                String ingredient = ingredients.get(i).get(j);
                adj.computeIfAbsent(ingredient, k -> new ArrayList<>()).add(recipes[i]);
                inDegree.put(recipes[i], inDegree.getOrDefault(recipes[i], 0) + 1);
            }
        }

        List<String> ret = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        for(String supply : supplies) {
            dfs(supply, visited, ret, inDegree, adj);
        }
        return ret;
    }

    public void dfs(String node, Set<String> visited, List<String> ret, Map<String, Integer> inDegree, Map<String, List<String>> adj) {
        if(visited.contains(node)) return;
        if(inDegree.containsKey(node) && inDegree.get(node) == 0) {
            ret.add(node);
            visited.add(node);
        }
        if(adj.containsKey(node)) {
            for(String next : adj.get(node)) {
                inDegree.put(next, inDegree.get(next) - 1);
                if(inDegree.get(next) == 0) {
                    dfs(next, visited, ret, inDegree, adj);
                }
            }
        }
    }
}
