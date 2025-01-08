package Apple;

public class VersionNum {
  public int compareVersion(String version1, String version2) {
    String[] v1 = version1.split("\\.");
    String[] v2 = version2.split("\\.");

    int len1 = v1.length;
    int len2 = v2.length;
    int maxLen = Math.max(len1, len2);

    for(int i = 0; i < maxLen; i++) {
      String str1 = i < len1 ? v1[i] : "";
      String str2 = i < len2 ? v2[i] : "";

//      int num1 = Integer.parseInt(str1);
//      int num2 = Integer.parseInt(str2);
      str1 = removeLeadingZero(str1);
      str2 = removeLeadingZero(str2);
      if(Integer.parseInt(str1) > Integer.parseInt(str2)) {
        return 1;
      } else if(Integer.parseInt(str1) < Integer.parseInt(str2)) {
        return -1;
      }
    }
    return 0;
  }

  public String removeLeadingZero(String str) {
    int idx = 0;
    while(idx < str.length() && str.charAt(idx) == '0') {
      idx++;
    }
    return idx == str.length() ? "0" : str.substring(idx);
  }

  public static void main(String[] args) {
    VersionNum v = new VersionNum();
    String version1 = "1.2", version2 = "1.10";
    System.out.println(v.compareVersion(version1, version2));

    System.out.println(v.compareVersion("1.01", "1.001"));
    System.out.println(v.compareVersion("1.1", "1.1.0.0"));
  }
}
