public class FindtheIndexoftheFirstOccurrence {
  public int strStr(String haystack, String needle) {
    if(needle.isEmpty()) return 0;

    int left = 0, right = 0;

    while(left < haystack.length()) {
      if(haystack.charAt(left) == needle.charAt(right)) {
        int start = left;
        while (left < haystack.length() && right < needle.length()
            && haystack.charAt(left) == needle.charAt(right)) {
          left++;
          right++;
        }

        if(right == needle.length()) return start;
        left = start + 1;
        right = 0;
      } else {
        left++;
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    FindtheIndexoftheFirstOccurrence f = new FindtheIndexoftheFirstOccurrence();
    String haystack = "sadbutsad", needle = "sad";
    System.out.println(f.strStr(haystack, needle));

    haystack = "leetcode";
    needle = "leeto";
    System.out.println(f.strStr(haystack, needle));
  }
}
