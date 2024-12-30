public class SentenceSimilarityIII {
  public boolean areSentencesSimilar(String sentence1, String sentence2) {
    String[] word1 = sentence1.split(" ");
    String[] word2 = sentence2.split(" ");
    int len1 = word1.length;
    int len2 = word2.length;

    int left = 0, right = 0;
    while(left < len1 && right < len2 && word1[left].equals(word2[right])) {
      left++; // prefix
    }

    while(right < len1 && right < len2 && word1[len1 - 1 - right].equals(word2[len2 - 1 - right])) {
      right++; // suffix
    }

    return (left + right) >= Math.min(len1, len2);
  }

  public static void main(String[] args) {
    SentenceSimilarityIII s = new SentenceSimilarityIII();
    String sentence1 = "My name is Haley", sentence2 = "My Haley";
    System.out.println(s.areSentencesSimilar(sentence1, sentence2));

    String sentence3 = "of", sentence4 = "A lot of words";
    System.out.println(s.areSentencesSimilar(sentence3, sentence4));
  }
}
