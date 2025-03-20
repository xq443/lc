package Snowflake.recursion;

import java.util.*;

public class PathPermutations {

  // Function to generate all path permutations given a string and a segment size
  public List<String> generatePaths(String input, int segmentSize) {
    List<String> result = new ArrayList<>();
    generatePathsHelper(input, segmentSize, new StringBuilder(), result);
    return result;
  }

  // Helper function to recursively generate all paths
  private void generatePathsHelper(String input, int segmentSize, StringBuilder currentPath, List<String> result) {
    // Base case: if the input string is empty, store the current path
    if (input.isEmpty()) {
      result.add(currentPath.toString());
      return;
    }

    // Try to form a valid path by splitting the input into segments
    for (int i = 1; i <= segmentSize && i <= input.length(); i++) {
      // Take the first i characters as a segment
      String segment = input.substring(0, i);

      // Add this segment to the current path
      int currentLength = currentPath.length();
      if (currentLength > 0) {
        currentPath.append(".");
      }
      currentPath.append(segment);

      // Recursively process the remaining input string
      generatePathsHelper(input.substring(i), segmentSize, currentPath, result);

      // Backtrack: remove the last segment added to try a new one
      currentPath.setLength(currentLength);
    }
  }

  // Function to calculate scores for each path (example scoring logic)
  public Map<String, Integer> calculateScores(List<String> paths) {
    Map<String, Integer> pathScores = new HashMap<>();

    // Example score logic: score based on the number of segments in the path
    for (String path : paths) {
      String[] segments = path.split("\\.");
      int score = segments.length;  // For example, score based on the number of segments
      pathScores.put(path, score);
    }

    return pathScores;
  }

  // Main method for testing
  public static void main(String[] args) {
    PathPermutations solution = new PathPermutations();
    String input = "abcd";
    int segmentSize = 2;

    // Generate all path permutations
    List<String> paths = solution.generatePaths(input, segmentSize);
    System.out.println("Generated Paths: " + paths);

    // Calculate scores for each path
    Map<String, Integer> pathScores = solution.calculateScores(paths);
    System.out.println("Path Scores: " + pathScores);
  }
}
