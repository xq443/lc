package Databricks.QPS;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class KeyValueStoreWithQueue {

  // 键值存储
  private Map<String, String> map;

  // 用于记录 put 操作的时间戳和计数的队列
  private Queue<Slot> putQueue;
  // 用于记录 get 操作的时间戳和计数的队列
  private Queue<Slot> getQueue;

  // 监控的时间窗口，单位为秒（5分钟）
  private static final int WND = 300;

  // Slot 类表示一个时间戳和在该时间戳发生的操作次数
  private static class Slot {
    long ts; // 时间戳（秒）
    int cnt; // 操作次数

    Slot(long ts, int cnt) {
      this.ts = ts;
      this.cnt = cnt;
    }
  }

  // 构造函数初始化键值存储和操作记录队列
  public KeyValueStoreWithQueue() {
    map = new HashMap<>();
    putQueue = new LinkedList<>();
    getQueue = new LinkedList<>();
  }

  // 插入键值对并记录 put 操作
  public void put(String key, String val) {
    map.put(key, val);
    recOp(putQueue);
  }

  // 获取键对应的值并记录 get 操作
  public String get(String key) {
    recOp(getQueue);
    return map.get(key);
  }

  // 测量过去五分钟内 put 操作的平均 QPS
  public double mPut() {
    return calcQPS(putQueue);
  }

  // 测量过去五分钟内 get 操作的平均 QPS
  public double mGet() {
    return calcQPS(getQueue);
  }

  // 记录一次操作，更新对应的队列
  private void recOp(Queue<Slot> queue) {
    long cur = System.currentTimeMillis() / 1000; // 获取当前时间的秒数
    synchronized (queue) { // 确保线程安全
      if (!queue.isEmpty() && queue.peek().ts == cur) {
        queue.peek().cnt++; // 同一秒内的操作次数加一
      } else {
        queue.offer(new Slot(cur, 1)); // 添加新的时间戳和计数
      }
      // 移除超过时间窗口的操作
      while (!queue.isEmpty() && cur - queue.peek().ts >= WND) {
        queue.poll();
      }
    }
  }

  // 计算平均 QPS
  private double calcQPS(Queue<Slot> queue) {
    long cur = System.currentTimeMillis() / 1000; // 获取当前时间的秒数
    int total = 0;
    synchronized (queue) { // 确保线程安全
      for (Slot slot : queue) {
        if (cur - slot.ts < WND) { // 判断操作是否在五分钟内
          total += slot.cnt; // 累计操作次数
        }
      }
    }
    return total / (double) WND; // 计算平均 QPS
  }

  // 调用接口获取在过去五分钟内 add 操作和 get 操作各自平均的 QPS
  public static String getQPSFromEndpoint(String endpoint) throws Exception {
    URL url = new URL(endpoint);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");

    int responseCode = conn.getResponseCode();
    if (responseCode == HttpURLConnection.HTTP_OK) {
      BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      String inputLine;
      StringBuilder response = new StringBuilder();

      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();
      return response.toString();
    } else {
      throw new RuntimeException("Failed to get QPS from endpoint: " + responseCode);
    }
  }

  // 从接口获取数据并执行 put 或 get 操作
  public void performOperationsFromEndpoint(String endpoint) throws Exception {
    String data = getQPSFromEndpoint(endpoint);
    // 假设数据格式为 "put:key1,val1;get:key2;put:key3,val3"
    String[] operations = data.split(";");
    for (String operation : operations) {
      if (operation.startsWith("put:")) {
        String[] kv = operation.substring(4).split(",");
        if (kv.length == 2) {
          put(kv[0], kv[1]);
        }
      } else if (operation.startsWith("get:")) {
        String key = operation.substring(4);
        get(key);
      }
    }
  }

  // 主方法用于运行测试用例
  public static void main(String[] args) throws Exception {
    KeyValueStoreWithQueue store = new KeyValueStoreWithQueue();

//    String endpoint = "http://mockapi.com/qps"; // Mock URL
//    store.performOperationsFromEndpoint(endpoint);
//    System.out.println("\n从接口获取并执行的操作完成");

    // 测试用例 1：基本的 put 和 get 操作
    store.put("a", "1");
    store.put("b", "2");
    store.get("a");
    store.get("b");
    store.get("c"); // 获取不存在的键

    System.out.println("测试用例 1:");
    System.out.println("Put QPS: " + store.mPut()); // 预期: ~0.006 (2/300)
    System.out.println("Get QPS: " + store.mGet()); // 预期: ~0.01 (3/300)

    // 测试用例 2：同一秒内的多次操作
    for (int i = 0; i < 100; i++) {
      store.put("key" + i, "val" + i);
      store.get("key" + i);
    }

    System.out.println("\n测试用例 2:");
    System.out.println("Put QPS: " + store.mPut()); // 预期: ~0.34 (100/300)
    System.out.println("Get QPS: " + store.mGet()); // 预期: ~0.34 (100/300)

    // 测试用例 3：操作分布在不同时间
    store.put("d", "4");
    store.get("d");
    Thread.sleep(2000); // 等待2秒
    store.put("e", "5");
    store.get("e");

    System.out.println("\n测试用例 3:");
    System.out.println("Put QPS: " + store.mPut()); // 预期: 略高于之前
    System.out.println("Get QPS: " + store.mGet());

    // 测试用例 4：边界情况，操作时间恰好在5分钟前
    long cur = System.currentTimeMillis() / 1000;
    int idx = (int) (cur % WND);
    synchronized (store.putQueue) {
      store.putQueue.offer(new Slot(cur - WND, 50)); // 这些不应被计入
    }
    synchronized (store.getQueue) {
      store.getQueue.offer(new Slot(cur - WND, 50)); // 这些不应被计入
    }

    System.out.println("\n测试用例 4:");
    System.out.println("Put QPS 添加旧操作后: " + store.mPut()); // 不应包含50
    System.out.println("Get QPS 添加旧操作后: " + store.mGet()); // 不应包含50

    // 测试用例 5：高频率操作
    for (int i = 0; i < 1000; i++) {
      store.put("high" + i, "val" + i);
      store.get("high" + i);
    }

    System.out.println("\n测试用例 5:");
    System.out.println("Put QPS 高频操作后: " + store.mPut()); // 预期: ~3.34 (100 + 1000)/300
    System.out.println("Get QPS 高频操作后: " + store.mGet()); // 预期: ~3.34 (100 + 1000)/300

    // 测试用例 6：无操作
    KeyValueStoreWithQueue emptyStore = new KeyValueStoreWithQueue();
    System.out.println("\n测试用例 6:");
    System.out.println("Put QPS (empty): " + emptyStore.mPut()); // 预期: 0.0
    System.out.println("Get QPS (empty): " + emptyStore.mGet()); // 预期: 0.0
  }
}
/**
 * WAL:
 * 为了在键值存储系统中引入 WAL(预写日志)和快照机制以提升数据持久性和恢复能力，
 * 首先在每次执行 put 或 get 操作时，将操作详情(如操作类型、键、值和时间戳)同步写入 预写日志文件，
 * 确保在系统崩溃时能够通过日志回放恢复数据。
 * 其次，定期生成快照，保存 当前整个键值映射的状态，
 * 例如每隔一定时间或达到特定操作次数时，将 HashMap 序列化 并存储到快照文件中，以减少需要回放的日志量并加快恢复速度。
 * 在系统启动时，首先加载 最新的快照文件，然后回放自快照生成以来的日志文件，以恢复到最新状态。此外，为了优 化性能，
 * 可以采用异步日志写入方式，将日志操作委托给独立线程处理，并实现日志轮转和 压缩策略以节省存储空间。
 */
/**
 * Fine-Grained Locking with Lock Striping
 * One effective way to implement fine-grained locking is through lock striping.
 * This technique involves dividing the data structure into several segments, each protected by its own lock.
 * This allows multiple threads to operate on different segments concurrently without interfering with each other.
 */