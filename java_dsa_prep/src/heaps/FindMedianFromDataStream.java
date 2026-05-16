/*
 * Problem: LeetCode 295. Find Median from Data Stream
 * 
 * The median is the middle value in an ordered integer list. If the size of the
 * list is even, there is no middle value, and the median is the mean of the two
 * middle values.
 * 
 * For example, in the ordered list [2, 3, 4], the median is 3.
 * For in the ordered list [2, 3, 4, 5], the median is (3 + 4) / 2 = 3.5.
 * 
 * Implement the MedianFinder class:
 * - MedianFinder() initializes the MedianFinder object.
 * - void addNum(int num) adds an integer number from the data stream to the
 * data structure.
 * - double findMedian() returns the median of all elements so far.
 * 
 * Example 1:
 * Input:
 * ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
 * [[], [1], [2], [], [3], []]
 * 
 * Output:
 * [null, null, null, 2.0, null, 2.0]
 * 
 * Explanation:
 * MedianFinder medianFinder = new MedianFinder();
 * medianFinder.addNum(1);    // array becomes [1]
 * medianFinder.addNum(2);    // array becomes [1, 2]
 * medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
 * medianFinder.addNum(3);    // array becomes [1, 2, 3]
 * medianFinder.findMedian(); // return 2.0
 * 
 * Constraints:
 * -10^5 <= num <= 10^5
 * No more than 5 * 10^4 calls will be made to addNum and findMedian.
 * 
 * Follow up:
 * - If all integer numbers from the input data stream are between 0 and 100,
 * inclusive, to what optimization could you apply?
 */

package heaps;

import java.util.PriorityQueue;

public class FindMedianFromDataStream {
    // Approach: Two-Heap Method
    // We use two heaps to maintain the two halves of the sorted numbers:
    // 1. maxHeap (smallHalf): Stores the smaller half of the numbers.
    // The largest element in the smaller half is at the root.
    // 2. minHeap (largeHalf): Stores the larger half of the numbers.
    // The smallest element in the larger half is at the root.

    private PriorityQueue<Integer> maxHeap; // Stores smaller half (max-heap)
    private PriorityQueue<Integer> minHeap; // Stores larger half (min-heap)

    /** initialize your data structure here. */
    public FindMedianFromDataStream() {
        // Max-heap for the smaller half (reverse order)
        maxHeap = new PriorityQueue<>((a, b) -> b - a);
        // Min-heap for the larger half (natural order)
        minHeap = new PriorityQueue<>();
    }

    /**
     * Adds a number to the data structure.
     * Time Complexity: O(log n)
     */
    public void addNum(int num) {
        // Add to maxHeap first (always add to smaller half)
        maxHeap.offer(num);

        // Balance: Ensure the largest element of the smaller half is not greater
        // than the smallest element of the larger half.
        // If maxHeap's root is greater than minHeap's root, move it to minHeap.
        if (!maxHeap.isEmpty() && !minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()) {
            minHeap.offer(maxHeap.poll());
        }

        // Maintain size balance: The two heaps should differ in size by at most 1.
        // We want maxHeap to be equal to or one element larger than minHeap.

        // If maxHeap has more than one extra element, move the largest to minHeap
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        }

        // If minHeap has more elements than maxHeap, move the smallest to maxHeap
        if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    /**
     * Returns the median of all elements so far.
     * Time Complexity: O(1)
     */
    public double findMedian() {
        // Case 1: Odd number of elements (maxHeap has one extra element)
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        }
        // Case 2: Even number of elements
        else if (maxHeap.size() == minHeap.size()) {
            // Should not happen if addNum is correct, but safe check
            if (maxHeap.isEmpty()) {
                return 0.0;
            }
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
        // Case 3: minHeap has more elements (shouldn't happen with our balancing)
        else {
            return minHeap.peek();
        }
    }

    public static void main(String[] args) {
        FindMedianFromDataStream medianFinder = new FindMedianFromDataStream();

        // Test Case 1
        System.out.println("Test Case 1:");
        medianFinder.addNum(1);
        System.out.println("Added 1, Median: " + medianFinder.findMedian()); // Expected: 1.0
        medianFinder.addNum(2);
        System.out.println("Added 2, Median: " + medianFinder.findMedian()); // Expected: 1.5
        medianFinder.addNum(3);
        System.out.println("Added 3, Median: " + medianFinder.findMedian()); // Expected: 2.0
        System.out.println("---------------------");

        // Test Case 2: Negative numbers
        System.out.println("Test Case 2:");
        FindMedianFromDataStream medianFinder2 = new FindMedianFromDataStream();
        medianFinder2.addNum(-1);
        System.out.println("Added -1, Median: " + medianFinder2.findMedian()); // Expected: -1.0
        medianFinder2.addNum(-2);
        System.out.println("Added -2, Median: " + medianFinder2.findMedian()); // Expected: -1.5
        medianFinder2.addNum(-3);
        System.out.println("Added -3, Median: " + medianFinder2.findMedian()); // Expected: -2.0
        medianFinder2.addNum(-4);
        System.out.println("Added -4, Median: " + medianFinder2.findMedian()); // Expected: -2.5
        medianFinder2.addNum(-5);
        System.out.println("Added -5, Median: " + medianFinder2.findMedian()); // Expected: -3.0
        System.out.println("---------------------");

        // Test Case 3: Large numbers
        System.out.println("Test Case 3:");
        FindMedianFromDataStream medianFinder3 = new FindMedianFromDataStream();
        medianFinder3.addNum(100000);
        System.out.println("Added 100000, Median: " + medianFinder3.findMedian()); // Expected: 100000.0
        medianFinder3.addNum(100001);
        System.out.println("Added 100001, Median: " + medianFinder3.findMedian()); // Expected: 100000.5
        System.out.println("---------------------");

        // Test Case 4: Duplicate numbers
        System.out.println("Test Case 4:");
        FindMedianFromDataStream medianFinder4 = new FindMedianFromDataStream();
        medianFinder4.addNum(5);
        medianFinder4.addNum(5);
        System.out.println("Added 5, 5, Median: " + medianFinder4.findMedian()); // Expected: 5.0
        medianFinder4.addNum(5);
        System.out.println("Added 5, Median: " + medianFinder4.findMedian()); // Expected: 5.0
        System.out.println("---------------------");

        // Test Case 5: Mixed numbers
        System.out.println("Test Case 5:");
        FindMedianFromDataStream medianFinder5 = new FindMedianFromDataStream();
        int[] nums = { 6, 10, 2, 6, 5, 0, 6, 3, 1, 0, 0 };
        for (int num : nums) {
            medianFinder5.addNum(num);
            System.out.println("Added " + num + ", Median: " + medianFinder5.findMedian());
        }
        // Expected medians: 6.0, 8.0, 6.0, 6.0, 6.0, 5.5, 6.0, 5.5, 5.0, 5.0, 5.0
        System.out.println("---------------------");
    }
}
