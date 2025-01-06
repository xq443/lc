import java.util.ArrayList;
import java.util.List;

public class TextJustification {
  public List<String> fullJustify(String[] words, int maxWidth) {
    List<String> ret = new ArrayList<>();

    // find the position that ends the line
    for(int i = 0; i < words.length; i++) {
      int j = i, cnt = 0;
      while(j < words.length && cnt <= maxWidth) {
        if(cnt > 0) cnt += words[j].length() + 1;
        else cnt += words[j].length();
        j++;
      }
      j--;
      if(cnt > maxWidth) {
        cnt -= words[j].length() + 1;
        j--; // [i, j]
      }

      // last word, with left justification
      if(j == words.length - 1) {
        StringBuilder temp = new StringBuilder();
        for(int k = i; k <= j; k++) {
          temp.append(words[k]).append(" ");
        }
        temp.deleteCharAt(temp.length() - 1); // Remove the trailing space
        temp.append(addSpace(maxWidth - temp.length()));
        ret.add(temp.toString());

      } else {
        ret.add(printLine(words, i, j, maxWidth));
      }

      i = j;
    }
    return ret;
  }

  private String printLine(String[] words, int start, int end, int maxWidth) {
    if(start == end) {
      return words[start] + addSpace(maxWidth - words[start].length());
    }

    int cnt = 0;
    for(int i = start; i <= end; i++) {
      cnt += words[i].length();
    }

    StringBuilder ret = new StringBuilder();
    int space = (maxWidth - cnt) / (end - start); // 6/4 = 1
    int extra = (maxWidth - cnt) % (end - start); // 6%4 = 2

    for(int k = start; k <= end; k++) {
      if(k > start) {
        ret.append(addSpace(space + (extra-- > 0 ? 1 : 0)));
      }
      ret.append(words[k]);
    }
    return ret.toString();
  }

  private String addSpace(int num) {
    StringBuilder ret = new StringBuilder();
    for(int i = 0; i < num; i++) {
      ret.append(" ");
    }
    return ret.toString();
  }

  public static void main(String[] args) {
    TextJustification t = new TextJustification();
    String[] words1 = {"This", "is", "an", "example", "of", "text", "justification."};
    int mw = 16;
    System.out.println(t.fullJustify(words1, mw));

    String[] words2 = {"What","must","be","acknowledgment","shall","be"};
    System.out.println(t.fullJustify(words2, mw));

    int mw2 = 20;
    String[] words3 = {"Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"};
    System.out.println(t.fullJustify(words3, mw2));
  }
}
