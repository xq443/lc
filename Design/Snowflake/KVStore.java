package Snowflake;

import java.util.*;
import java.util.concurrent.*;

class KVStore {
  static class Entry {
    int version;
    long timestamp;
    String value;

    Entry(int version, long timestamp, String value) {
      this.version = version;
      this.timestamp = timestamp;
      this.value = value;
    }
  }

  static class Transaction {
    Map<String, List<Entry>> snapshot;
    Map<String, List<Entry>> changes;
    Set<String> deletedKeys;

    Transaction(Map<String, List<Entry>> snapshot) {
      this.snapshot = snapshot;
      this.changes = new HashMap<>();
      this.deletedKeys = new HashSet<>();
    }
  }

  private final Map<String, List<Entry>> store = new ConcurrentHashMap<>();
  // ensure each thread maintains its own transaction state independently.
  // This prevents conflicts between multiple threads accessing the key-value store simultaneously.
  private final ThreadLocal<Transaction> threadTransaction = new ThreadLocal<>();

  public synchronized void put(String key, String value) {
    Transaction tx = threadTransaction.get();
    long timestamp = System.currentTimeMillis();

    if (tx != null) {
      List<Entry> list = tx.changes.computeIfAbsent(key, k -> new ArrayList<>());
      int baseVersion = tx.snapshot.getOrDefault(key, new ArrayList<>()).size();
      list.add(new Entry(baseVersion + list.size() + 1, timestamp, value));
      tx.deletedKeys.remove(key);
    } else {
      store.computeIfAbsent(key, k -> new ArrayList<>()).add(new Entry(store.get(key).size() + 1, timestamp, value));
    }
  }

  public synchronized String get(String key) {
    Transaction tx = threadTransaction.get();
    if (tx != null) {
      if (tx.deletedKeys.contains(key)) return null;
      List<Entry> list = new ArrayList<>();
      list.addAll(tx.snapshot.getOrDefault(key, new ArrayList<>()));
      list.addAll(tx.changes.getOrDefault(key, new ArrayList<>()));
      return list.isEmpty() ? null : list.get(list.size() - 1).value;
    }
    List<Entry> list = store.get(key);
    return (list == null || list.isEmpty()) ? null : list.get(list.size() - 1).value;
  }

  public synchronized String get(String key, int version) {
    Transaction tx = threadTransaction.get();
    List<Entry> list = (tx != null) ? mergeLists(tx.snapshot.getOrDefault(key, new ArrayList<>()), tx.changes.getOrDefault(key, new ArrayList<>())) : store.get(key);
    return (list == null || version <= 0 || version > list.size()) ? null : list.get(version - 1).value;
  }

  public synchronized String get(String key, long timestamp) {
    List<Entry> list = threadTransaction.get() != null ? mergeLists(threadTransaction.get().snapshot.getOrDefault(key, new ArrayList<>()), threadTransaction.get().changes.getOrDefault(key, new ArrayList<>())) : store.get(key);
    if (list == null || list.isEmpty()) return null;
    return findClosestEntry(list, timestamp);
  }

  public synchronized void delete(String key) {
    Transaction tx = threadTransaction.get();
    if (tx != null) {
      tx.deletedKeys.add(key);
      tx.changes.remove(key);
    } else {
      store.remove(key);
    }
  }

  public synchronized void begin() {
    if (threadTransaction.get() != null) throw new RuntimeException("Transaction already exists");
    Map<String, List<Entry>> snapshot = new HashMap<>();
    store.forEach((key, value) -> snapshot.put(key, new ArrayList<>(value)));
    threadTransaction.set(new Transaction(snapshot));
  }

  public synchronized void commit() {
    Transaction tx = threadTransaction.get();
    if (tx == null) throw new RuntimeException("No transaction to commit");

    tx.deletedKeys.forEach(store::remove);
    tx.changes.forEach((key, entries) -> {
      store.computeIfAbsent(key, k -> new ArrayList<>()).addAll(entries);
    });

    threadTransaction.remove();
  }

  public synchronized void rollback() {
    if (threadTransaction.get() == null) throw new RuntimeException("No transaction to rollback");
    threadTransaction.remove();
  }

  private List<Entry> mergeLists(List<Entry> snapshot, List<Entry> changes) {
    List<Entry> merged = new ArrayList<>(snapshot);
    merged.addAll(changes);
    return merged;
  }

  private String findClosestEntry(List<Entry> list, long timestamp) {
    int lo = 0, hi = list.size() - 1;
    while (lo <= hi) {
      int mid = (lo + hi) / 2;
      if (list.get(mid).timestamp == timestamp) return list.get(mid).value;
      if (list.get(mid).timestamp < timestamp) lo = mid + 1;
      else hi = mid - 1;
    }
    return (hi >= 0 && (lo >= list.size() || Math.abs(list.get(hi).timestamp - timestamp) <= Math.abs(list.get(lo).timestamp - timestamp))) ? list.get(hi).value : list.get(lo).value;
  }

  public static void main(String[] args) throws InterruptedException {
    KVStore kv = new KVStore();

    kv.put("a", "apple");
    Thread.sleep(10);
    kv.put("a", "apricot");
    System.out.println("Latest a: " + kv.get("a"));
    System.out.println("Version 1 a: " + kv.get("a", 1));
    System.out.println("Version 2 a: " + kv.get("a", 2));

    long t0 = System.currentTimeMillis();
    Thread.sleep(10);
    kv.put("a", "avocado");
    System.out.println("Timestamp lookup a: " + kv.get("a", t0));

    kv.begin();
    kv.put("b", "banana");
    kv.put("a", "almond");
    System.out.println("Transaction a: " + kv.get("a"));
    kv.delete("a");
    System.out.println("Transaction deleted a: " + kv.get("a"));
    kv.commit();
    System.out.println("After commit a: " + kv.get("a"));
    System.out.println("After commit b: " + kv.get("b"));

    kv.put("c", "cherry");
    kv.begin();
    kv.put("c", "cranberry");
    System.out.println("Transaction c: " + kv.get("c"));
    kv.rollback();
    System.out.println("After rollback c: " + kv.get("c"));
  }
}
