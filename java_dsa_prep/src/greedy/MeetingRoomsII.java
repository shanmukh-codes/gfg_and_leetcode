/*
 * Leetcode 253. Meeting Rooms II
 *
 * Problem Description:
 * Given an array of meeting time intervals consisting of start and end times [start, end],
 * find the minimum number of conference rooms required.
 *
 * Example 1:
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: 2
 * Explanation:
 * We need two rooms. First room for [0,30]. Second room for [5,10] and [15,20].
 *
 * Constraints:
 * 1 <= intervals.length <= 10^4
 * intervals[i].length == 2
 * 0 <= start_i < end_i <= 10^6
 *
 * Approach:
 * We use a greedy approach with a min-heap.
 * 1. Sort the intervals based on their start times
 * 2. Use a min-heap to keep track of the end times of meetings currently in progress
 * 3. For each meeting, check if the earliest finishing meeting has ended before this one starts
 * 4. If yes, reuse that room (poll from heap)
 * 5. Add the current meeting's end time to the heap
 * 6. The maximum size of the heap at any point is the minimum number of rooms required
 *
 * Time Complexity: O(n log n) - due to sorting and heap operations
 * Space Complexity: O(n) - for the heap in the worst case
 */

package greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MeetingRoomsII {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        // Sort intervals based on start times
        // Processing meetings in chronological order is the key greedy choice
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // Min-Heap to store the end times of meetings currently in progress
        // The smallest end time will always be at the top
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Add the end time of the first meeting to the heap
        minHeap.add(intervals[0][1]);

        // Iterate through the rest of the meetings
        for (int i = 1; i < intervals.length; i++) {
            int currentStart = intervals[i][0];
            int currentEnd = intervals[i][1];

            // Check if the meeting that finishes earliest is done before this meeting
            // starts
            if (currentStart >= minHeap.peek()) {
                // If yes, we can reuse that room
                // Remove the earliest end time (poll)
                minHeap.poll();
            }

            // Add the current meeting's end time to the heap
            // If we reused a room, this updates that room's end time
            // If we couldn't reuse a room, this adds a new room requirement
            minHeap.add(currentEnd);
        }

        // The size of the heap at the end is the maximum number of overlapping
        // meetings,
        // which is the minimum number of rooms required
        return minHeap.size();
    }
}
