package Apple;

import java.util.ArrayList;
import java.util.List;

public class TextJustification {
  public List<String> fullJustify(String[] words, int maxWidth) {
    List<String> ret = new ArrayList<>();

    // find the correct position
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
        j--;
      }

      // if this is the last word
      if(j == words.length - 1) {
        StringBuilder temp = new StringBuilder();
        for(int k = i; k <= j; k++) {
          temp.append(words[k]);
          temp.append(' ');
        }
        temp.deleteCharAt(temp.length() - 1);
        temp.append(addSpace(maxWidth - temp.length()));
        ret.add(temp.toString());
      } else {
        ret.add(printLine(words, i, j, maxWidth));
      }

      i = j;
    }
    return ret;
  }

  public String printLine(String[] words, int start, int end, int maxWidth) {
    StringBuilder ret = new StringBuilder();
    if(start == end) {
      ret.append(words[start]);
      ret.append(addSpace(maxWidth - words[start].length()));
      return ret.toString();
    }
    int cnt = 0;
    // count the length of the curr chars
    for(int i = start; i <= end; i++) {
      cnt += words[i].length();
    }

    int space = (maxWidth - cnt) / (end - start);
    int extra = (maxWidth - cnt) % (end - start);

    for(int k = start; k <= end; k++) {
      if(k > start) {
        ret.append(addSpace(space + (extra > 0 ? 1 : 0)));
      }
      ret.append(words[k]);
    }
    return ret.toString();
  }

  public String addSpace(int nums) {
    StringBuilder ret = new StringBuilder();
    for(int i = 0; i < nums; i++) {
      ret.append(' ');
    }
    return ret.toString();
  }

  public static void main(String[] args) {
    TextJustification t = new TextJustification();
    String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
    int maxWidth = 16;
    System.out.println(t.fullJustify(words, maxWidth));
  }
}
