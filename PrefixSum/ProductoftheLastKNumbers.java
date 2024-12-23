import java.util.ArrayList;
import java.util.List;

public class ProductoftheLastKNumbers {
  List<Integer> prefix;
  int idx;
  int zeroPosition;

  public ProductoftheLastKNumbers() {
    this.prefix = new ArrayList<>();
    this.prefix.add(1);
    this.idx = 0;
    this.zeroPosition = 0;
  }

  public void add(int num) {
    idx++;
    if(num == 0) {
      prefix.add(1);
      zeroPosition = idx;
    } else {
      prefix.add(prefix.get(prefix.size() - 1) * num);
    }
  }

  public int getProduct(int k) {
    // x x x x [3 2 1]
    if(zeroPosition <= idx - k) {
      return prefix.get(idx) / prefix.get(idx - k);
    } else {
      return 0;
    }
  }

  public static void main(String[] args) {
    ProductoftheLastKNumbers productOfNumbers =  new ProductoftheLastKNumbers();
    productOfNumbers.add(3);        // [3]
    productOfNumbers.add(0);        // [3,0]
    productOfNumbers.add(2);        // [3,0,2]
    productOfNumbers.add(5);        // [3,0,2,5]
    productOfNumbers.add(4);        // [3,0,2,5,4]
    System.out.println(productOfNumbers.getProduct(2)); // return 20. The product of the last 2 numbers is 5 * 4 = 20
    System.out.println(productOfNumbers.getProduct(3));; // return 40. The product of the last 3 numbers is 2 * 5 * 4 = 40
    System.out.println(productOfNumbers.getProduct(4)); // return 0. The product of the last 4 numbers is 0 * 2 * 5 * 4 = 0
    productOfNumbers.add(8);        // [3,0,2,5,4,8]
    System.out.println(productOfNumbers.getProduct(2)); // return 32. The product of the last 2 numbers is 4 * 8 = 32
  }
}
