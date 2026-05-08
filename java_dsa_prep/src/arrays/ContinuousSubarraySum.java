/*
 * Given an integer array nums and an integer k, return true if nums has a
 * continuous
 * subarray of size at least two whose elements sum up to a multiple of k,
 * or false otherwise.
 * 
 * An integer x is a multiple of k if there exists an integer n such that
 * x = n * k.
 * 0 is always a multiple of k.
 * 
 * 
 * 
 * 
 */
package arrays;

public class ContinuousSubarraySum {

    // Brute Force Approach
    public static boolean checkSubarraySumBruteForce(int[] nums, int k) {
        for (int i = 0; i < nums.length - 1; i++) {
            int sum = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                if (sum % k == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    // Optimized Approach
    public static boolean checkSubarraySum(int[] nums, int k) {
        // key -> remainder, value -> index
        java.util.Map<Integer, Integer> map = new java.util.HashMap<>();
        map.put(0, -1);
        int currentSum = 0;

        for (int i = 0; i < nums.length; i++) {
            currentSum += nums[i];
            int remainder = currentSum % k;

            if (map.containsKey(remainder)) {
                if (i - map.get(remainder) > 1) {
                    return true;
                }
            } else {
                map.put(remainder, i);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = { 23, 2, 4, 6, 7 };
        int k = 6;
        System.out.println(checkSubarraySum(nums, k)); // Output: true
    }

}
