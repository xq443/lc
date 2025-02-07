package Tiktok.Stack;

public class RemoveConsecutiveDuplicates {
  public static void main(String[] args) {
    String input = "cabbbbaad";
    int k = 3;
    String result = removeConsecutiveChars(input, k);
    System.out.println(result);  // 预期输出 "cd"
  }

  /**
   * 逐轮扫描字符串，删除所有连续相同字符数大于等于 k 的子串，
   * 直到没有可以删除的部分为止。
   */
  public static String removeConsecutiveChars(String input, int k) {
    // 标记本轮扫描是否有删除操作
    boolean removalHappened = true;

    // 只要有删除操作，就继续扫描，因为删除后相邻字符可能合并成新的连续区域
    while (removalHappened) {
      removalHappened = false;
      StringBuilder sb = new StringBuilder();
      int i = 0;

      // 遍历整个字符串
      while (i < input.length()) {
        int j = i;
        // 统计从 i 开始有多少个连续相同的字符 "cabbbbaad"
        while (j < input.length() && input.charAt(j) == input.charAt(i)) {
          j++;
        }
        int count = j - i;
        // 如果连续字符数大于等于 k，则本轮删除这一部分
        if (count >= k) {
          removalHappened = true; // 标记有删除操作
          // 什么都不做，相当于跳过这一段
        } else {
          // 否则将这部分保留
          sb.append(input, i, j);
        }
        i = j;
      }
      // 更新字符串为本轮处理后的结果
      input = sb.toString();
    }
    return input;
  }
}