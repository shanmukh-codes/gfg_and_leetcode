/*
 * Given an integer array nums, you need to find one continuous subarray that if
 * you only sort this subarray in ascending order, then the whole array will
 * be sorted in ascending order.
 * Return the shortest such subarray and output its length.
 * 
 * Ex: nums = [2, 6, 4, 8, 10, 9, 15] -> Output : 5
 */
package arrays;

public class ShortestUnsortedContinuousSubarray {

    // Brute Force Approach
    public static int findUnsortedSubarrayBruteForce(int[] nums) {
        int n = nums.length;
        int left = n;
        int right = -1;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] > nums[j]) {
                    left = Math.min(left, i);
                    right = Math.max(right, j);
                }
            }
        }
        return right - left + 1;
    }

    // Optimized Approach
    public static int findUnsortedSubarrayOptimized(int[] nums) {
        int n = nums.length;
        int minVal = Integer.MAX_VALUE;
        int maxVal = Integer.MIN_VALUE;
        boolean flag = false;

        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[i - 1])
                flag = true;
            if (flag) {
                minVal = Math.min(minVal, nums[i]);
            }
        }

        flag = false;

        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1])
                flag = true;
            if (flag) {
                maxVal = Math.max(maxVal, nums[i]);
            }
        }

        if (minVal == Integer.MAX_VALUE) {
            return 0; // Already sorted
        }

        // Find the correct position for minVal
        int left = 0;
        while (left < n && nums[left] <= minVal) {
            left++;
        }

        // Find the correct position for maxVal
        int right = n - 1;
        while (right >= 0 && nums[right] >= maxVal) {
            right--;
        }

        return right - left + 1;
    }

    public static void main(String[] args) {
        int[] nums = { 2, 6, 4, 8, 10, 9, 15 };
        System.out.println(findUnsortedSubarrayBruteForce(nums)); // Output: 5
        System.out.println(findUnsortedSubarrayOptimized(nums)); // Output: 5
    }
}
