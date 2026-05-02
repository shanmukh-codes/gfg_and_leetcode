/*Problem: Remove Element

Given an integer array nums and an integer val, remove all occurrences of val in nums in-place.
The order of the elements may be changed. Then return the number of elements in nums which are not equal to val.

Consider the number of elements in nums which are not equal to val be k. You need to do the following things:

Change the array nums such that the first k elements of nums contain the elements which are not equal to val. The remaining elements of nums are not important as well as the size of nums.
Return k.

Example 1:

Input: nums = [3,2,2,3], val = 3
Output: 2, nums = [2,2,_,_]
Explanation: Your function should return k = 2, with the first two elements of nums being 2. It does not matter what you leave beyond the returned k (hence they are underscores).

Example 2:

Input: nums = [0,1,2,2,3,0,4,2], val = 2
Output: 5, nums = [0,1,4,0,3,_,_,_]
Explanation: Your function should return k = 5, with the first five elements of nums containing 0, 1, 4, 0, and 3. It does not matter what you leave beyond the returned k.

Constraints:

0 <= nums.length <= 100
0 <= nums[i] <= 50
0 <= val <= 100
 */

package arrays;

import java.util.Arrays;

public class RemoveElement {
    // Brute Force Approach
    public static int removeElementBruteForce(int[] nums, int val) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }

        int[] temp = new int[n];
        int k = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] != val) {
                temp[k++] = nums[i];
            }
        }

        for (int i = 0; i < k; i++) {
            nums[i] = temp[i];
        }

        return k;
    }

    // Optimized Approach (Two Pointers)
    public static int removeElementOptimized(int[] nums, int val) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }

        int k = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] != val) {
                nums[k++] = nums[i];
            }
        }

        return k;
    }

    public static void main(String[] args) {
        int[] nums1 = { 3, 2, 2, 3 };
        int val1 = 3;
        int[] nums2 = { 0, 1, 2, 2, 3, 0, 4, 2 };
        int val2 = 2;

        System.out.println("Array 1: " + Arrays.toString(nums1));
        System.out.println("Brute Force: " + removeElementBruteForce(nums1, val1));
        System.out.println("Optimized: " + removeElementOptimized(nums1.clone(), val1));
        System.out.println();

        System.out.println("Array 2: " + Arrays.toString(nums2));
        System.out.println("Brute Force: " + removeElementBruteForce(nums2, val2));
        System.out.println("Optimized: " + removeElementOptimized(nums2.clone(), val2));
        System.out.println();
    }
}
