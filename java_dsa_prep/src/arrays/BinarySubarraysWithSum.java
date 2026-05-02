package arrays;

public class BinarySubarraysWithSum {
    // Brute Force Approach
    public static int numSubarraysWithSumBruteForce(int[] nums, int goal) {
        int count = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += nums[k];
                }
                if (sum == goal) {
                    count++;
                }
            }
        }
        return count;
    }

    // Prefix Sum Approach
    public static int numSubarraysWithSumPrefixSum(int[] nums, int goal) {
        int count = 0;
        int n = nums.length;
        int[] prefixSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (prefixSum[j + 1] - prefixSum[i] == goal) {
                    count++;
                }
            }
        }
        return count;
    }

    // Wrapper Method
    public static int numSubarraysWithSumSlidingWindow(int[] nums, int goal) {
        // The Math Trick: Exactly(goal) = AtMost(goal) - AtMost(goal - 1)
        return atMost(nums, goal) - atMost(nums, goal - 1);
    }

    // Your exact logic, just renamed to 'atMost'
    private static int atMost(int[] nums, int goal) {
        // Edge case: sum can never be negative since array is only 0s and 1s
        if (goal < 0)
            return 0;

        int count = 0;
        int n = nums.length;
        int left = 0;
        int right = 0;
        int sum = 0;

        while (right < n) {
            sum += nums[right];
            while (sum > goal) {
                sum -= nums[left];
                left++;
            }
            count += (right - left + 1);
            right++;
        }
        return count;
    }

    // Main method for testing
    public static void main(String[] args) {
        int[] nums = { 1, 0, 1, 0, 1 };
        int goal = 2;
        System.out.println("Number of subarrays with sum " + goal + " (Brute Force): "
                + numSubarraysWithSumBruteForce(nums, goal));
        System.out.println(
                "Number of subarrays with sum " + goal + " (Prefix Sum): " + numSubarraysWithSumPrefixSum(nums, goal));
        System.out.println("Number of subarrays with sum " + goal + " (Sliding Window): "
                + numSubarraysWithSumSlidingWindow(nums, goal));
    }
}
