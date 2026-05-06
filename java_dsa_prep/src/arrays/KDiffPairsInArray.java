package arrays;

import java.util.Arrays;
import java.util.HashSet;

public class KDiffPairsInArray {
    // Brute Force Approach
    public static int findPairsBruteForce(int[] nums, int k) {
        int count = 0;
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (Math.abs(nums[i] - nums[j]) == k) {
                    int min = Math.min(nums[i], nums[j]);
                    int max = Math.max(nums[i], nums[j]);
                    set.add(min + " " + max);
                }
            }
        }
        return set.size();
    }

    // Optimized Approach
    public static int findPairsOptimized(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        int count = 0;
        int left = 0;
        int right = 1;
        while (left < n && right < n) {
            if (left == right) {
                right++;
                continue;
            }

            int diff = nums[right] - nums[left];
            if (diff < k) {
                right++;
            } else if (diff > k) {
                left++;
            } else {
                count++;
                left++;
                while (left < n && nums[left] == nums[left - 1]) {
                    left++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = { 3, 1, 4, 1, 5 };
        int k = 2;
        System.out.println(findPairsBruteForce(nums, k)); // Output: 2
        System.out.println(findPairsOptimized(nums, k)); // Output: 2
    }
}
