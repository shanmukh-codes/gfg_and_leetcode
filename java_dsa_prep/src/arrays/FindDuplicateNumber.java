/*
 * Given an array of integers nums containing n + 1 integers where each integer is
 * in the range [1, n] inclusive.
 * 
 * There is only one repeated number in nums, return this repeated number.
 * 
 * You must solve the problem without modifying the array nums and uses only
 * constant extra space.
 * 
 * Example 1:
 * 
 * Input: nums = [1,3,4,2,2]
 * Output: 2
 * Example 2:
 * 
 * Input: nums = [3,1,3,4,2]
 * Output: 3
 * 
 * Constraints:
 * 
 * 1 <= n <= 105
 * nums.length == n + 1
 * 1 <= nums[i] <= n
 * All the integers of nums appear only once except for precisely one integer
 * which appears two or more times.
 */
package arrays;

import java.util.HashSet;

public class FindDuplicateNumber {

    // Brute Force Approach (Nested Loops)
    // Time Complexity: O(n^2)
    // Space Complexity: O(1)
    public static int findDuplicateBruteForce(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return nums[i];
                }
            }
        }
        return -1;
    }

    // Brute Force Approach (HashSet)
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public static int findDuplicateHashSet(int[] nums) {
        HashSet<Integer> visitedNumbers = new HashSet<>();
        for (int num : nums) {
            if (visitedNumbers.contains(num)) {
                return num;
            }
            visitedNumbers.add(num);
        }
        return -1;
    }

    // Optimal Approach (Floyd's Tortoise and Hare Algorithm)
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public static int findDuplicateOptimal(int[] nums) {
        int slow = nums[0];
        int fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    public static void main(String[] args) {
        int[] nums1 = { 1, 3, 4, 2, 2 };
        int[] nums2 = { 3, 1, 3, 4, 2 };

        System.out.println("Test Case 1:");
        System.out.println("Brute Force - Duplicate number: " + findDuplicateBruteForce(nums1));
        System.out.println("Optimal - Duplicate number: " + findDuplicateOptimal(nums1));

        System.out.println("\nTest Case 2:");
        System.out.println("Brute Force - Duplicate number: " + findDuplicateBruteForce(nums2));
        System.out.println("Optimal - Duplicate number: " + findDuplicateOptimal(nums2));
    }
}
