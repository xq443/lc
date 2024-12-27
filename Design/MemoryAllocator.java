public class MemoryAllocator {
  int[] memory;

  public MemoryAllocator(int n) {
    this.memory = new int[n];
  }

  public int allocation(int size, int mID) {
    int free = 0;
    for(int i = 0; i < memory.length; i++) {
      if(memory[i] == 0) free++;
      else free = 0;

      if(free == size) {
        for(int j = i - size + 1; j <= i; j++) {
          memory[j] = mID;
        }
        return i - size + 1;
      }
    }
    return -1;
  }

  public int freeMemory(int mID) {
    // return the units of the block with mID
    int ret = 0;
    for(int i = 0; i < memory.length; i++) {
      if(memory[i] == mID) {
        ret++;
        memory[i] = 0;
      }
    }
    return ret;
  }

  public static void main(String[] args) {
    MemoryAllocator m = new MemoryAllocator(10);

    System.out.println(m.allocation(1, 1)); // 0
    System.out.println(m.allocation(1, 2)); // 1
    System.out.println(m.allocation(1, 3)); // 2

    System.out.println(m.freeMemory(2)); // 1
    System.out.println(m.allocation(3, 4)); // 3
    System.out.println(m.allocation(1, 1)); // 1
    System.out.println(m.allocation(1, 1)); // 6
    System.out.println(m.freeMemory(1)); // 3
    System.out.println(m.allocation(10, 1)); // -1
    System.out.println(m.freeMemory(7)); // 0






  }
}
