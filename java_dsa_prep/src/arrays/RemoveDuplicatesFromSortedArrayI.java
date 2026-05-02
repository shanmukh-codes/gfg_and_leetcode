/*
 * Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. The relative order of the elements should be kept the same. Then return the number of unique elements in nums.
 * 
 * Consider the number of unique elements of nums to be k. To get accepted, you need to do the following things:
 * 
 * Change the array nums such that the first k elements of nums contain the unique elements in the order they were present in nums initially. The remaining elements of nums are not important as well as the size of nums.
 * Return k.
 * 
 * Example 1:
 * Input: nums = [1,1,2]
 * Output: 2, nums = [1,2,_]
 * Explanation: Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively. It does not matter what you leave beyond the returned k (hence they are underscores).
 * 
 * Example 2:
 * Input: nums = [0,0,1,1,1,2,2,3,3,4]
 * Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
 * Explanation: Your function should return k = 5, with the first five elements of nums being 0, 1, 2, 3, and 4 respectively. It does not matter what you leave beyond the returned k (hence they are underscores).
 * 
 * Constraints:
 * 1 <= nums.length <= 3 * 104
 * -100 <= nums[i] <= 100
 * nums is sorted in non-decreasing order.
 */

package arrays;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class RemoveDuplicatesFromSortedArrayI {

    // Brute Force Approach
    public static int removeDuplicatesBruteForce(int[] nums) {
        int n = nums.length;
        int[] temp = new int[n];
        int j = 0;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] != nums[i + 1]) {
                temp[j++] = nums[i];
            }
        }
        temp[j++] = nums[n - 1];
        for (int i = 0; i < j; i++) {
            nums[i] = temp[i];
        }
        return j;
    }

    // HashSet Approach
    public static int removeDuplicatesHashSet(int[] nums) {
        int n = nums.length;
        Set<Integer> uniqueElements = new LinkedHashSet<>();
        for (int i = 0; i < n; i++) {
            uniqueElements.add(nums[i]);
        }
        int i = 0;
        for (int num : uniqueElements) {
            nums[i++] = num;
        }
        return uniqueElements.size();
    }

    // Optimized Approach
    public static int removeDuplicatesOptimized(int[] nums) {
        int n = nums.length;
        int slow = 0;
        int fast = 1;
        while (fast < n) {
            if (nums[slow] != nums[fast]) {
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
        }
        return slow + 1;
    }

    public static void main(String[] args) {
        int[] nums1 = { 1, 1, 2 };
        int[] nums2 = { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 };
        int[] nums3 = { 1, 1, 1, 1, 1 };
        System.out.println("Array 1: " + Arrays.toString(nums1));
        System.out.println("Brute Force: " + removeDuplicatesBruteForce(nums1));
        System.out.println("Array 1: " + Arrays.toString(nums1));
        System.out.println("HashSet: " + removeDuplicatesHashSet(nums1));
        System.out.println("Array 1: " + Arrays.toString(nums1));
        System.out.println("Optimized: " + removeDuplicatesOptimized(nums1));
        System.out.println("Array 2: " + Arrays.toString(nums2));
        System.out.println("Brute Force: " + removeDuplicatesBruteForce(nums2));
        System.out.println("Array 2: " + Arrays.toString(nums2));
        System.out.println("HashSet: " + removeDuplicatesHashSet(nums2));
        System.out.println("Array 2: " + Arrays.toString(nums2));
        System.out.println("Optimized: " + removeDuplicatesOptimized(nums2));
        System.out.println("Array 3: " + Arrays.toString(nums3));
        System.out.println("Brute Force: " + removeDuplicatesBruteForce(nums3));
        System.out.println("Array 3: " + Arrays.toString(nums3));
        System.out.println("HashSet: " + removeDuplicatesHashSet(nums3));
        System.out.println("Array 3: " + Arrays.toString(nums3));
        System.out.println("Optimized: " + removeDuplicatesOptimized(nums3));
    }
}
