/*
 * You are given an integer array nums and two integers minK and maxK.
 * A fixed-bound subarray of nums is a subarray that satisfies the following conditions:
 * - The minimum value in the subarray is equal to minK.
 * - The maximum value in the subarray is equal to maxK.
 * Return the number of fixed-bound subarrays.
 * 
 * Ex : nums = [1,3,5,2,7,5], minK = 1, maxK = 5 -> Output : 2
 * Explanation :
 */
package arrays;

public class CountSubarraysWithFixedBounds {
    // Brute Force Approach
    public static int countSubarraysBruteForce(int[] nums, int minK, int maxK) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int minVal = Integer.MAX_VALUE;
            int maxVal = Integer.MIN_VALUE;
            for (int j = i; j < nums.length; j++) {
                minVal = Math.min(minVal, nums[j]);
                maxVal = Math.max(maxVal, nums[j]);
                if (minVal == minK && maxVal == maxK) {
                    count++;
                }
                if (minVal < minK || maxVal > maxK) {
                    break;
                }
            }
        }
        return count;
    }

    // Optimized Approach
    public static long countSubarraysOptimized(int[] nums, int minK, int maxK) {
        int n = nums.length;
        int minIndex = -1;
        int maxIndex = -1;
        int invalidIndex = -1;
        long count = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] < minK || nums[i] > maxK) {
                invalidIndex = i;
            }
            if (nums[i] == minK) {
                minIndex = i;
            }
            if (nums[i] == maxK) {
                maxIndex = i;
            }
            int bottleneck = Math.min(minIndex, maxIndex);
            if (bottleneck > invalidIndex) {
                count += bottleneck - invalidIndex;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 3, 5, 2, 7, 5 };
        int minK = 1;
        int maxK = 5;
        System.out.println(countSubarraysBruteForce(nums, minK, maxK)); // Output: 2
    }
}
