public class MaximumPoints {
  public int maxScore(int[] cardPoints, int k) {
    int len = cardPoints.length;
    int sum = 0;
    for (int num : cardPoints) {
      sum += num;
    }
    int curr = 0;
    int min = Integer.MAX_VALUE;
    int size = len - k;

    for(int i = 0; i < size; i++) {
      curr += cardPoints[i];
    }
    min = curr;

    for(int i = size; i < len; i++) {
      curr += cardPoints[i] - cardPoints[i - size];
      min = Math.min(min, curr);
    }
    return sum - min;
  }

  public static void main(String[] args) {
    MaximumPoints m =  new MaximumPoints();
    System.out.println(m.maxScore(new int[]{1,2,3,4,5,6,1}, 3));
  }
}
