/*
 * 
 * You are given an array of positive integers nums and a positive integer target.
 * Return the minimal length of a contiguous subarray
 * whose sum is greater than or equal to target.
 * If there is no such subarray, return 0 instead.
 * 
 * 
 * 
 */
package arrays;

public class MinimumSizeSubarraySum {

    public static int minSubArrayLenBruteForce(int target, int[] nums) {
        int minLen = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int currentSum = 0;
            for (int j = i; j < nums.length; j++) {
                currentSum += nums[j];
                if (currentSum >= target) {
                    minLen = Math.min(minLen, j - i + 1);
                }
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    // Optimized Solution
    public static int minSubArrayLenOptimized(int target, int[] nums) {
        int minLen = Integer.MAX_VALUE;
        int left = 0;
        int currentSum = 0;

        for (int right = 0; right < nums.length; right++) {
            currentSum += nums[right];
            while (currentSum >= target) {
                minLen = Math.min(minLen, right - left + 1);
                currentSum -= nums[left];
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    public static void main(String[] args) {
        int[] nums = { 2, 3, 1, 2, 4, 3 };
        int target = 7;
        System.out.println(minSubArrayLenBruteForce(target, nums)); // Output: 2
        System.out.println(minSubArrayLenOptimized(target, nums)); // Output: 2
    }
}
