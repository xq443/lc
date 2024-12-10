public class ReverseBit {
  public int reverseBits(int n) {
    if (n == 0) return 0;
    int ret = 0;
    for (int i = 0; i < 32; i++) {
      ret <<= 1;
      if ((n & 1) == 1) ret++;
      n >>= 1;
    }
    return ret;
  }

  public static void main(String[] args) {
    ReverseBit rb = new ReverseBit();
    // Use 0b prefix for binary literal
    System.out.println(rb.reverseBits(0b00000010100101000001111010011100));
  }
}
