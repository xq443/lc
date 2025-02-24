package Tiktok.Recursion;

import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddresses {
  public List<String> restoreIpAddresses(String s) {
    List<String> ret = new ArrayList<>();
    if(s == null || s.length() == 0) return ret;
    dfs(ret, s, 0, 0, new StringBuilder());
    return ret;
  }

  public void dfs(List<String> ret, String s, int index, int segment, StringBuilder sb) {
    if(index == s.length() && segment == 4) {
      ret.add(sb.toString());
      return;
    } else if(index == s.length() || segment == 4) {
      return;
    }

    for(int len = 1; len < 4; len++) {
      if(index + len > s.length()) break;
      String temp = s.substring(index, index + len);
      if(isValid(temp)) {
        int prev = sb.length();
        if(segment > 0) sb.append(".");
        sb.append(temp);
        dfs(ret, s, index + len, segment + 1, sb);
        sb.setLength(prev);
      }
    }
  }

  public boolean isValid(String s) {
    if(s.length() > 1 && s.charAt(0) == '0') return false;
    int num = Integer.parseInt(s);
    return num >= 0 && num <= 255;
  }

  public static void main(String[] args) {
    RestoreIPAddresses r = new RestoreIPAddresses();
    System.out.println(r.restoreIpAddresses("25525511135"));
  }

}
