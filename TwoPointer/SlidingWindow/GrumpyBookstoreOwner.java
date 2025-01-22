package SlidingWindow;

public class GrumpyBookstoreOwner {
  public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
    int n = grumpy.length;
    int ret = 0;
    int sum = 0;

    for(int i = 0; i < n; i++) {
      if(grumpy[i] == 0) sum += customers[i];
    }

    for(int i = 0; i < n; i++) {
      if(grumpy[i] == 1) sum += customers[i];
      if(i - minutes >= 0 && grumpy[i - minutes] == 1) sum -= customers[i - minutes];
      ret = Math.max(ret, sum);
    }
    return ret;
  }

  public static void main(String[] args) {
    GrumpyBookstoreOwner g = new GrumpyBookstoreOwner();
    int[] customers = {1,0,1,2,1,1,7,5}, grumpy = {0,1,0,1,0,1,0,1};
    int minutes = 3;
    System.out.println(g.maxSatisfied(customers, grumpy, minutes));
  }
}
