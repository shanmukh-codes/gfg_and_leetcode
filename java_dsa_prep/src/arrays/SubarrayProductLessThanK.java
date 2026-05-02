/*
 * Problem: Given an array of integers nums and an integer k, return the number of
 * continuous subarrays where the product of all the elements in the subarray
 * is strictly less than k.
 * Example 1:
 * Input: nums = [10, 5, 2, 6], k = 100
 * Output: 8
 * Explanation: The 8 subarrays are [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
 * Note that [10, 5, 2] is not included because the product of its elements is 100.
 * Example 2:
 * Input: nums = [1, 2, 3], k = 0
 * Output: 0
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

package arrays;

public class SubarrayProductLessThanK {

    // Brute force approach
    public static int subarrayProductLessThanKBruteForce(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int product = 1;
            for (int j = i; j < nums.length; j++) {
                product *= nums[j];
                if (product < k) {
                    count++;
                }
            }
        }
        return count;
    }

    // Sliding window approach
    public static int subarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) {
            return 0;
        }
        int count = 0;
        int left = 0;
        int product = 1;
        for (int right = 0; right < nums.length; right++) {
            product *= nums[right];
            while (product >= k) {
                product /= nums[left];
                left++;
            }
            count += right - left + 1;
        }
        return count;
    }

    // Driver code
    public static void main(String[] args) {
        int[] nums = { 10, 5, 2, 6 };
        int k = 100;
        System.out.println(subarrayProductLessThanK(nums, k));
    }
}
