package arrays;

public class CountNumberOfNiceSubarrays {
    public static int numberOfSubarrays(int[] nums, int k) {
        return atMost(nums, k) - atMost(nums, k - 1);
    }

    public static int atMost(int[] nums, int k) {
        if (k < 0) {
            return 0;
        }
        int count = 0;
        int n = nums.length;
        int left = 0;
        int right = 0;
        int oddCount = 0;
        while (right < n) {
            if (nums[right] % 2 != 0) {
                oddCount++;
            }
            while (oddCount > k) {
                if (nums[left] % 2 != 0) {
                    oddCount--;
                }
                left++;
            }
            count += (right - left + 1);
            right++;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 1, 2, 1, 1 };
        int k = 3;
        System.out.println("Number of subarrays with " + k + " odd numbers: " + numberOfSubarrays(nums, k));

        int[] nums2 = { 2, 4, 6 };
        int k2 = 1;
        System.out.println("Number of subarrays with " + k2 + " odd numbers: " + numberOfSubarrays(nums2, k2));

        int[] nums3 = { 2, 2, 2, 1, 2, 2, 1, 2, 2, 2 };
        int k3 = 2;
        System.out.println("Number of subarrays with " + k3 + " odd numbers: " + numberOfSubarrays(nums3, k3));
    }
}
