package SlidingWindow;

public class DietPlanPerformance {
  public int dietPlanPerformance(int[] calories, int k, int lower, int upper) {
    int left = 0, right = 0;
    int ret = 0, n = calories.length;
    int sum = 0;

    while (right < n) {
      sum += calories[right];
      while(right - left + 1 > k) {
        sum -= calories[left];
        left++;
      }
      if (right - left + 1 == k) {
        if (sum < lower)
          ret--;
        else if (sum > upper)
          ret++;
      }
      right++;
    }
    return ret;
  }

  public static void main(String[] args) {
    DietPlanPerformance d = new DietPlanPerformance();
    int[] calories = {1,2,3,4,5};
    int k = 1, lower = 3, upper = 3;
    System.out.println(d.dietPlanPerformance(calories, k, lower, upper));

    int[] calories2 = {3,2};
    int k2 = 2, lower2 = 0, upper2 = 1;
    System.out.println(d.dietPlanPerformance(calories2, k2, lower2, upper2));
  }
}
