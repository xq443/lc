import java.util.*;

public class AllRecipesFromSupplies {
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

        Queue<String> queue = new LinkedList<>();
        for(String supply : supplies) {
            queue.add(supply);
        }

        List<String> ret = new ArrayList<>();
        while (!queue.isEmpty()) {
            String curr = queue.poll();
            if(Arrays.asList(recipes).contains(curr)) ret.add(curr);
            if(adj.containsKey(curr)) {
                for(String next: adj.get(curr)) {
                    inDegree.put(next, inDegree.get(next) - 1);
                    if(inDegree.get(next) == 0) {
                        inDegree.remove(next);
                        queue.add(next);
                    }
                }
            }
        }
        return ret;
    }
    // TC: O(N + M + K)
    // SC: O(N + E)
}
