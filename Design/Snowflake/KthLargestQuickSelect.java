public class KthLargestQuickSelect {
    public int findKthLargest(int[] nums, int k) {
        if(nums == null || nums.length == 0) return -1;
        int kthLargestIdx = nums.length - k;
        return quickSelect(nums, k, 0, nums.length - 1, kthLargestIdx);
    }

    public int quickSelect(int[] nums, int k, int left, int right, int kthLargestIdx) {
        if(left == right) return nums[left];
        while(left <= right) {
            int pivot = partition(nums, left, right);
            if(pivot == kthLargestIdx) return nums[pivot];
            else if(pivot < kthLargestIdx) left = pivot + 1;
            else right = pivot - 1;
        }
        return nums[kthLargestIdx];
    }

    public int partition(int[] nums, int left, int right) {
        int pivot = nums[right];
        int idx = left;

        for(int i = left; i <= right; i++) {
            if(nums[i] < pivot) {
                // swap idx and i
                int temp = nums[idx];
                nums[idx] = nums[i];
                nums[i] = temp;
                idx++;
            }
        }
        // swap right and idx
        int temp = nums[idx];
        nums[idx] = nums[right];
        nums[right] = temp;
        return idx;
    }

    public static void main(String[] args) {
        KthLargestQuickSelect k = new KthLargestQuickSelect();
        System.out.println(k.findKthLargest(new int[] {3,2,1,5,6,4}, 2));
        System.out.println(k.findKthLargest(new int[] {3,2,3,1,2,4,5,5,6}, 4));
    }

    // TC: O(N) / O(N^2) worst case
    // SC: O(1) / O(LogN) recursion stack
}
