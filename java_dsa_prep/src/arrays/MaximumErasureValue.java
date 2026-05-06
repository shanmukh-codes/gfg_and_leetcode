/*
 * You are given an array of positive integers nums and want to erase a subarray
 * containing unique elements.
 * 
 * Return the maximum score you can get by erasing the subarray with the maximum
 * sum. The score of an erasure is the sum of the elements in the erased
 * subarray.
 * 
 * Example 1:
 * 
 * Input: nums = [4,2,4,5,6]
 * Output: 17
 * Explanation: The optimal subarray is [2,4,5,6] with sum 17.
 * Example 2:
 * 
 * Input: nums = [5,2,1,2,5,2,1,2]
 * Output: 8
 * Explanation: The optimal subarray is [5,2,1] with sum 8.
 * 
 * Constraints:
 * 
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 104
 */
package arrays;

import java.util.HashSet;

public class MaximumErasureValue {
    // Brute Force Approach (Nested Loops) with HashSet
    // Time Complexity: O(n^2)
    // Space Complexity: O(n)
    public static int maximumErasureValueBruteForce(int[] nums) {
        int maxSum = 0;

        for (int i = 0; i < nums.length; i++) {
            HashSet<Integer> seen = new HashSet<>();
            int currentSum = 0;

            for (int j = i; j < nums.length; j++) {
                // If we see a duplicate, this block is dead. Break to the next 'i'.
                if (seen.contains(nums[j])) {
                    break;
                }

                // Otherwise, add to our sum and set
                seen.add(nums[j]);
                currentSum += nums[j];
                maxSum = Math.max(maxSum, currentSum);
            }
        }

        return maxSum;
    }

    // Optimal Approach (Sliding Window)
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public static int maximumErasureValueOptimal(int[] nums) {
        int maxScore = 0;
        int currentScore = 0;
        int left = 0;
        HashSet<Integer> visitedNumbers = new HashSet<>();
        for (int right = 0; right < nums.length; right++) {
            while (visitedNumbers.contains(nums[right])) {
                currentScore -= nums[left];
                visitedNumbers.remove(nums[left]);
                left++;
            }
            visitedNumbers.add(nums[right]);
            currentScore += nums[right];
            maxScore = Math.max(maxScore, currentScore);
        }
        return maxScore;
    }

    public static void main(String[] args) {
        // Test case 1
        int[] nums1 = { 4, 2, 4, 5, 6 };
        System.out.println("Brute Force Approach - Test case 1: " + maximumErasureValueBruteForce(nums1));
        System.out.println("Optimal Approach - Test case 1: " + maximumErasureValueOptimal(nums1));

        // Test case 2
        int[] nums2 = { 5, 2, 1, 2, 5, 2, 1, 2 };
        System.out.println("Brute Force Approach - Test case 2: " + maximumErasureValueBruteForce(nums2));
        System.out.println("Optimal Approach - Test case 2: " + maximumErasureValueOptimal(nums2));
    }
}
