// flip at most one zero to get max consecutive ones
// flip at most k zero to get max consecutive ones
/*
    
 * Time Complexity: O(n) when using sliding window, O(n^2) when using brute force
 * Space Complexity: O(1)
 */
package arrays;

public class MaxConsecutiveOnesII {

    // Brute Force Approach
    public static int maxConsecutiveOnesByFlippingOneZeroBruteForce(int[] nums) {
        int maxCount = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {

            if (nums[i] == 0) {
                nums[i] = 1;
                int currentCount = 0;
                for (int num : nums) {
                    currentCount = (num == 1) ? currentCount + 1 : 0;
                    maxCount = Math.max(maxCount, currentCount);
                }
                nums[i] = 0;
            }
        }
        if (maxCount == 0) {
            return n;
        }
        return maxCount;
    }

    // Optimal Approach
    public static int maxConsecutiveOnesByFlippingOneZeroOptimal(int[] nums) {
        int maxCount = 0;
        int n = nums.length;
        int left = 0;
        int right = 0;
        int zeroCount = 0;
        while (right < n) {
            if (nums[right] == 0) {
                zeroCount++;
            }
            while (zeroCount > 1) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }
            maxCount = Math.max(maxCount, right - left + 1);
            right++;
        }
        return maxCount;
    }

    // Main method for testing
    public static void main(String[] args) {
        int[] nums = { 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1 };
        System.out.println("Max Consecutive Ones by Flipping One Zero (Brute Force): "
                + maxConsecutiveOnesByFlippingOneZeroBruteForce(nums));
        System.out.println("Max Consecutive Ones by Flipping One Zero (Optimal): "
                + maxConsecutiveOnesByFlippingOneZeroOptimal(nums));

        int[] nums2 = { 1, 0, 1, 1, 0, 1 };
        System.out.println("Max Consecutive Ones by Flipping One Zero (Brute Force): "
                + maxConsecutiveOnesByFlippingOneZeroBruteForce(nums2));
        System.out.println("Max Consecutive Ones by Flipping One Zero (Optimal): "
                + maxConsecutiveOnesByFlippingOneZeroOptimal(nums2));

        int[] nums3 = { 0, 0, 0, 0, 0 };
        System.out.println("Max Consecutive Ones by Flipping One Zero (Brute Force): "
                + maxConsecutiveOnesByFlippingOneZeroBruteForce(nums3));
        System.out.println("Max Consecutive Ones by Flipping One Zero (Optimal): "
                + maxConsecutiveOnesByFlippingOneZeroOptimal(nums3));
    }
}
