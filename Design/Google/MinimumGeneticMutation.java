package Google;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

public class MinimumGeneticMutation {
  public int minMutation(String startGene, String endGene, String[] bank) {
    Set<String> bankSet = new HashSet<>(Arrays.asList(bank));
    if(!bankSet.contains(endGene)) return -1;

    Queue<String> queue = new LinkedList<>();
    queue.add(startGene);
    Set<String> visited = new HashSet<>();
    visited.add(startGene);

    Character[] alternatives = new Character[]{'A', 'C', 'G', 'T'};
    int ret = 0;

    // bfs
    while (!queue.isEmpty()) {
      int size = queue.size();
      ret++;
      for (int i = 0; i < size; i++) {
        String curr = queue.poll();
        for(int j = 0; j < curr.length(); j++) {
          char[] currChar = curr.toCharArray();
          for(char ch : alternatives) {
            if(ch  == currChar[j]) continue;
            currChar[j] = ch;
            String newStr = new String(currChar);
            if(newStr.equals(endGene)) return ret;
            if(!visited.contains(newStr) && bankSet.contains(newStr)) {
              queue.add(newStr);
              visited.add(newStr);
            }
          }
        }
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    MinimumGeneticMutation m = new MinimumGeneticMutation();
    String startGene = "AACCGGTT", endGene = "AAACGGTA";
    String[] bank = {"AACCGGTA","AACCGCTA","AAACGGTA"};
    System.out.println(m.minMutation(startGene, endGene, bank));
  }
}
