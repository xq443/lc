package Snowflake.HitCounter;

public class HitCounter {

    public int[] times;
    public int[] counts;

    public HitCounter() {
        this.times = new int[300];
        this.counts = new int[300];
    }

    public void hit(int timestamp) {
        int idx = timestamp % 300;

        if(timestamp != times[idx]) {
            times[idx] = timestamp;
            counts[idx] = 1;
        } else {
            counts[idx]++;
        }
    }

    public int getHits(int timestamp) {
        int ret = 0;
        for(int i = 0; i < 300; i++) {
            if(timestamp - times[i] < 300) {
                ret += counts[i];
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        HitCounter counter = new HitCounter();

        counter.hit(1);
        counter.hit(2);
        counter.hit(3);
        System.out.println(counter.getHits(4)); // Output: 3
        counter.hit(300);
        System.out.println(counter.getHits(300)); // Output: 4
        System.out.println(counter.getHits(301)); // Output: 3 (hit at timestamp 1 expires)
    }
}
