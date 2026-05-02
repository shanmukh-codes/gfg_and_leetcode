/*Problem: Move Zeroes

Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
Note that you must do this in-place without making a copy of the array.

Example 1:

Input: nums = [0,1,0,3,12]
Output: [1,3,12,0,0]

Example 2:

Input: nums = [0]
Output: [0]

Constraints:

1 <= nums.length <= 10^4
-2^31 <= nums[i] <= 2^31 - 1

Follow up: Could you minimize the total number of operations?
 */

package arrays;

import java.util.Arrays;

public class MoveZeroes {
    // Brute Force Approach
    public static void moveZeroesBruteForce(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return;
        }

        int[] temp = new int[n];
        int k = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] != 0) {
                temp[k++] = nums[i];
            }
        }

        for (int i = 0; i < k; i++) {
            nums[i] = temp[i];
        }
    }

    // Optimized Approach (Two Pointers)
    public static void moveZeroesOptimized(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return;
        }

        int slow = 0;

        for (int fast = 0; fast < n; fast++) {
            if (nums[fast] != 0) {
                nums[slow++] = nums[fast];
            }
        }

        while (slow < n) {
            nums[slow++] = 0;
        }
    }

    public static void main(String[] args) {
        int[] nums1 = { 0, 1, 0, 3, 12 };
        int[] nums2 = { 0 };

        System.out.println("Array 1: " + Arrays.toString(nums1));
        moveZeroesBruteForce(nums1);
        System.out.println("Brute Force: " + Arrays.toString(nums1));
        moveZeroesOptimized(nums1);
        System.out.println("Optimized: " + Arrays.toString(nums1));
        System.out.println();

        System.out.println("Array 2: " + Arrays.toString(nums2));
        moveZeroesBruteForce(nums2);
        System.out.println("Brute Force: " + Arrays.toString(nums2));
        moveZeroesOptimized(nums2);
        System.out.println("Optimized: " + Arrays.toString(nums2));
        System.out.println();
    }
}
