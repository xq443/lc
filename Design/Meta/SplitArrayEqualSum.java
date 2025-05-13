package Meta;

public class SplitArrayEqualSum {
  public static void findSplitPoint(int[] arr) {
    if (arr == null || arr.length < 2) {
      System.out.println("Cannot split array.");
      return;
    }

    int totalSum = 0;
    for (int num : arr) {
      totalSum += num;
    }

    int leftSum = 0;
    for (int i = 0; i < arr.length - 1; i++) {
      leftSum += arr[i];
      int rightSum = totalSum - leftSum;
      if (leftSum == rightSum) {
        System.out.print("Subarray 1: ");
        for (int j = 0; j <= i; j++) {
          System.out.print(arr[j] + " ");
        }
        System.out.println();

        System.out.print("Subarray 2: ");
        for (int j = i + 1; j < arr.length; j++) {
          System.out.print(arr[j] + " ");
        }
        return;
      }
    }

    System.out.println("No split possible.");
  }

  public static void main(String[] args) {
    int[] arr = {1, 1, 1, 2, 1};
    findSplitPoint(arr);
  }
}
