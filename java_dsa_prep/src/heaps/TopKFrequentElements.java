/*
 * LeetCode 347: Top K Frequent Elements
 * 
 * Problem Description:
 * Given an integer array nums and an integer k, return the k most frequent elements.
 * You may return the answer in any order.
 * 
 * Example 1:
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * 
 * Example 2:
 * Input: nums = [1], k = 1
 * Output: [1]
 * 
 * Constraints:
 * 1 <= k <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * The answer is guaranteed to be unique.
 * 
 * Approach: Frequency Map + Min-Heap
 * 1. Count the frequency of each element using a HashMap
 * 2. Use a min-heap of size k to keep track of the top k frequent elements
 * 3. Iterate through the frequency map: add element to heap, if heap size > k, remove min
 * 4. Finally, the heap contains the top k frequent elements
 * 
 * Time Complexity: O(n log k)
 * - Frequency map: O(n)
 * - Heap operations: O(n log k) since we iterate through n elements and heap size is at most k
 * 
 * Space Complexity: O(n + k)
 * - Frequency map: O(n) in worst case (all distinct elements)
 * - Heap: O(k)
 */
package heaps;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            throw new IllegalArgumentException("Invalid input");
        }

        // Step 1: Count the frequency of each element
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Step 2: Use a min-heap of size k to keep track of the top k frequent elements
        // The heap stores pairs of [element, frequency]
        // We use a min-heap based on frequency. This way, the element with the lowest
        // frequency is at the top and can be easily removed if we find a more frequent
        // element.
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k, (a, b) -> frequencyMap.get(a) - frequencyMap.get(b));

        // Step 3: Iterate through the frequency map
        for (int element : frequencyMap.keySet()) {
            // Add the current element to the heap
            minHeap.offer(element);

            // If the heap size exceeds k, remove the element with the minimum frequency
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // Step 4: Extract the elements from the heap to form the result
        int[] result = new int[k];
        int index = 0;
        while (!minHeap.isEmpty()) {
            result[index++] = minHeap.poll();
        }

        return result;
    }

    public static void main(String[] args) {
        TopKFrequentElements solution = new TopKFrequentElements();

        // Test Case 1
        int[] nums1 = { 1, 1, 1, 2, 2, 3 };
        int k1 = 2;
        int[] result1 = solution.topKFrequent(nums1, k1);
        System.out.println("Test Case 1:");
        System.out.println("Input: nums = " + Arrays.toString(nums1) + ", k = " + k1);
        System.out.println("Output: " + Arrays.toString(result1));
        // Expected: [1,2] or [2,1]
        System.out.println("---------------------");

        // Test Case 2
        int[] nums2 = { 1 };
        int k2 = 1;
        int[] result2 = solution.topKFrequent(nums2, k2);
        System.out.println("Test Case 2:");
        System.out.println("Input: nums = " + Arrays.toString(nums2) + ", k = " + k2);
        System.out.println("Output: " + Arrays.toString(result2));
        // Expected: [1]
        System.out.println("---------------------");

        // Test Case 3: All distinct elements
        int[] nums3 = { 1, 2, 3, 4, 5 };
        int k3 = 3;
        int[] result3 = solution.topKFrequent(nums3, k3);
        System.out.println("Test Case 3:");
        System.out.println("Input: nums = " + Arrays.toString(nums3) + ", k = " + k3);
        System.out.println("Output: " + Arrays.toString(result3));
        // Expected: Any 3 elements since all have frequency 1
        System.out.println("---------------------");

        // Test Case 4: All same elements
        int[] nums4 = { 7, 7, 7, 7, 7 };
        int k4 = 1;
        int[] result4 = solution.topKFrequent(nums4, k4);
        System.out.println("Test Case 4:");
        System.out.println("Input: nums = " + Arrays.toString(nums4) + ", k = " + k4);
        System.out.println("Output: " + Arrays.toString(result4));
        // Expected: [7]
        System.out.println("---------------------");

        // Test Case 5: Negative numbers
        int[] nums5 = { -1, -1, -2, -2, -2, 0, 0, 3 };
        int k5 = 3;
        int[] result5 = solution.topKFrequent(nums5, k5);
        System.out.println("Test Case 5:");
        System.out.println("Input: nums = " + Arrays.toString(nums5) + ", k = " + k5);
        System.out.println("Output: " + Arrays.toString(result5));
        // Expected: [-2, 0, -1] in some order (frequencies: -2:3, 0:2, -1:2)
        System.out.println("---------------------");
    }
}
