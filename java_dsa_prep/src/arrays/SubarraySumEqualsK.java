/*
 * Problem: Given an array of integers nums and an integer k, return the total
 * number of continuous subarrays whose sum equals to k.
 * Example 1:
 * Input: nums = [1,1,1], k = 2
 * Output: 2
 * Explanation: The subarrays [1,1] and [1,1] have the sum of 2.
 * Example 2:
 * Input: nums = [1,2,3], k = 3
 * Output: 2
 * Explanation: The subarrays [1,2] and [3] have the sum of 3.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
package arrays;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {

    // Brute force approach
    public static int subarraySumBruteForce(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    // Optimal approach - Use a hashmap to store the frequency of cumulative sums.
    public static int subarraySumOptimal(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        int runningSum = 0;
        map.put(0, 1);
        for (int num : nums) {
            runningSum += num;
            int targetToChopOff = runningSum - k;
            if (map.containsKey(targetToChopOff)) {
                count += map.get(targetToChopOff);
            }
            map.put(runningSum, map.getOrDefault(runningSum, 0) + 1);
        }
        return count;
    }

    // Driver code
    public static void main(String[] args) {
        int[] nums = { 1, 1, 1 };
        int k = 2;
        System.out.println(subarraySumOptimal(nums, k));

        int[] nums2 = { 1, 2, 3 };
        int k2 = 3;
        System.out.println(subarraySumOptimal(nums2, k2));

        int[] nums3 = { 1, 2, 1, 2, 1 };
        int k3 = 3;
        System.out.println(subarraySumOptimal(nums3, k3));

        int[] nums4 = { 1, 2, 1, 2, 1 };
        int k4 = 4;
        System.out.println(subarraySumOptimal(nums4, k4));

        int[] nums5 = { 1, 2, 1, 2, 1 };
        int k5 = 6;
        System.out.println(subarraySumOptimal(nums5, k5));
    }
}
