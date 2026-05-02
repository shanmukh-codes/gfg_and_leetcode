/*Problem: Remove Duplicates from Sorted Array II

Given a sorted integer array nums, remove the duplicates in-place such that each unique element appears at most twice.
The relative order of the elements should be kept the same.

Return k after placing the final result in the first k slots of nums.

Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.

Example 1:

Input: nums = [1,1,1,2,2,3]
Output: 5, nums = [1,1,2,2,3,_]
Explanation: Your function should return k = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).

Example 2:

Input: nums = [0,0,1,1,1,1,2,3,3]
Output: 7, nums = [0,0,1,1,2,3,3,_,_]
Explanation: Your function should return k = 7, with the first seven elements of nums being 0, 0, 1, 1, 2, 3 and 3 respectively.
It does not matter what you leave beyond the returned k.

Constraints:

1 <= nums.length <= 3 * 10^4
-10^4 <= nums[i] <= 10^4
nums is sorted in non-decreasing order
 */

package arrays;

import java.util.Arrays;
import java.util.HashMap;

public class RemoveDuplicatesFromSortedArrayII {

    // Brute Force Approach
    public static int removeDuplicatesBruteForce(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return n;
        }

        int[] temp = new int[n];
        int k = 0;
        temp[k++] = nums[0];
        temp[k++] = nums[1];

        for (int i = 2; i < n; i++) {
            if (nums[i] != temp[k - 2]) {
                temp[k++] = nums[i];
            }
        }

        for (int i = 0; i < k; i++) {
            nums[i] = temp[i];
        }

        return k;
    }

    // HashMap Approach and ArrayList Approach
    public static int removeDuplicatesHashMap(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return n;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        int k = 0;
        for (int i = 0; i < n; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            if (map.get(nums[i]) <= 2) {
                nums[k++] = nums[i];
            }
        }

        return k;
    }

    // Optimized Approach (Two Pointers)
    public static int removeDuplicatesOptimized(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return n;
        }

        // 'k' is the index where the next unique element should be placed
        // We already know the first two elements are part of the result
        int slow = 2;

        for (int fast = 2; fast < n; fast++) {
            // If the current element is different from the element at k-2
            // (which is the second-to-last element in the result so far)
            // then it's a valid element to include
            if (nums[fast] != nums[slow - 2]) {
                nums[slow] = nums[fast];
                slow++;
            }
        }

        return slow;
    }

    public static void main(String[] args) {
        int[] nums1 = { 1, 1, 1, 2, 2, 3 };
        int[] nums2 = { 0, 0, 1, 1, 1, 1, 2, 3, 3 };
        int[] nums3 = { 1, 1, 1, 1, 1 };

        System.out.println("Array 1: " + Arrays.toString(nums1));
        System.out.println("Brute Force: " + removeDuplicatesBruteForce(nums1));
        System.out.println("Optimized: " + removeDuplicatesOptimized(nums1.clone()));
        System.out.println();

        System.out.println("Array 2: " + Arrays.toString(nums2));
        System.out.println("Brute Force: " + removeDuplicatesBruteForce(nums2));
        System.out.println("Optimized: " + removeDuplicatesOptimized(nums2.clone()));
        System.out.println();

        System.out.println("Array 3: " + Arrays.toString(nums3));
        System.out.println("Brute Force: " + removeDuplicatesBruteForce(nums3));
        System.out.println("Optimized: " + removeDuplicatesOptimized(nums3.clone()));
        System.out.println();
    }
}
