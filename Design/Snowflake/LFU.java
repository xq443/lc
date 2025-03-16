package Snowflake;

import java.util.*;

public class LFU {
  private class Node {
    String key;
    int frequency;
    Node prev, next;

    Node(String key, int frequency) {
      this.key = key;
      this.frequency = frequency;
    }
  }

  private static class FrequencyBucket {
    int frequency;
    Set<String> keys;
    FrequencyBucket prev, next;

    FrequencyBucket(int frequency) {
      this.frequency = frequency;
      this.keys = new HashSet<>();
    }
  }

  private Map<String, FrequencyBucket> keyToBucket;
  private FrequencyBucket head, tail;

  public LFU() {
    keyToBucket = new HashMap<>();
    head = new FrequencyBucket(Integer.MIN_VALUE);
    tail = new FrequencyBucket(Integer.MAX_VALUE);
    head.next = tail;
    tail.prev = head;
  }

  private void insertAfter(FrequencyBucket prev, FrequencyBucket newBucket) {
    newBucket.next = prev.next;
    newBucket.prev = prev;
    prev.next.prev = newBucket;
    prev.next = newBucket;
  }

  private void removeBucket(FrequencyBucket bucket) {
    bucket.prev.next = bucket.next;
    bucket.next.prev = bucket.prev;
  }

  public void add(String key) {
    if (keyToBucket.containsKey(key)) {
      FrequencyBucket curBucket = keyToBucket.get(key);
      int newFreq = curBucket.frequency + 1;
      FrequencyBucket nextBucket = curBucket.next;
      if (nextBucket == tail || nextBucket.frequency > newFreq) {
        nextBucket = new FrequencyBucket(newFreq);
        insertAfter(curBucket, nextBucket);
      }
      nextBucket.keys.add(key);
      keyToBucket.put(key, nextBucket);
      curBucket.keys.remove(key);
      if (curBucket.keys.isEmpty()) removeBucket(curBucket);
    } else {
      FrequencyBucket firstBucket = head.next;
      if (firstBucket == tail || firstBucket.frequency > 1) {
        firstBucket = new FrequencyBucket(1);
        insertAfter(head, firstBucket);
      }
      firstBucket.keys.add(key);
      keyToBucket.put(key, firstBucket);
    }
  }

  public void delete(String key) {
    if (!keyToBucket.containsKey(key)) return;
    FrequencyBucket curBucket = keyToBucket.get(key);
    curBucket.keys.remove(key);
    if (curBucket.frequency > 1) {
      int newFreq = curBucket.frequency - 1;
      FrequencyBucket prevBucket = curBucket.prev;
      if (prevBucket == head || prevBucket.frequency < newFreq) {
        prevBucket = new FrequencyBucket(newFreq);
        insertAfter(curBucket.prev, prevBucket);
      }
      prevBucket.keys.add(key);
      keyToBucket.put(key, prevBucket);
    } else {
      keyToBucket.remove(key);
    }
    if (curBucket.keys.isEmpty()) removeBucket(curBucket);
  }

  public String key_with_max_frequency() {
    return tail.prev == head ? "" : tail.prev.keys.iterator().next();
  }

  public String key_with_min_frequency() {
    return head.next == tail ? "" : head.next.keys.iterator().next();
  }

  public static void main(String[] args) {
    LFU cache = new LFU();
    cache.add("a");
    cache.add("b");
    cache.add("a");
    System.out.println("Max Frequency Key: " + cache.key_with_max_frequency());
    System.out.println("Min Frequency Key: " + cache.key_with_min_frequency());
    cache.delete("a");
    System.out.println("After Deletion - Max Frequency Key: " + cache.key_with_max_frequency());
    System.out.println("After Deletion - Min Frequency Key: " + cache.key_with_min_frequency());
  }
}
