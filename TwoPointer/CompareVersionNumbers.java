public class CompareVersionNumbers {
  public int compareVersion(String version1, String version2) {
    String[] v1 = version1.split("\\.");
    String[] v2 = version2.split("\\.");

    int len1 = v1.length;
    int len2 = v2.length;
    int max = Math.max(len1, len2);
    for (int i = 0; i < max; i++) {
      String str1 = (i < len1) ? v1[i] : "0";
      String str2 = (i < len2) ? v2[i] : "0";

      str1 = removingLeadingZero(str1);
      str2 = removingLeadingZero(str2);

      int num1 = Integer.parseInt(str1);
      int num2 = Integer.parseInt(str2);
      if (num1 > num2) return 1;
      if (num1 < num2) return -1;
    }
    return 0;
  }

  private String removingLeadingZero(String str) {
    int idx = 0;
    while (idx < str.length() && str.charAt(idx) == '0') {
      idx++;
    }
    return idx == str.length() ? "0" : str.substring(idx);
  }

  public static void main(String[] args) {
    CompareVersionNumbers c =  new CompareVersionNumbers();
    System.out.println(c.compareVersion("1.1", "1.2"));
    System.out.println(c.compareVersion("1.3.0", "1.2"));
    System.out.println(c.compareVersion("1.02", "1.2"));
  }
}
