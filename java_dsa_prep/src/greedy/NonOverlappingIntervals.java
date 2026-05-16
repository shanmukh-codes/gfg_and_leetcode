/*
 * Leetcode 435. Non-overlapping Intervals
 * 
 * 
 */

package greedy;

import java.util.Arrays;

public class NonOverlappingIntervals {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }

        // Sort intervals based on end times
        // This is the key greedy choice: always pick the interval that finishes first
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));

        int count = 0; // Number of intervals to remove
        int currentEnd = intervals[0][1]; // End time of the last non-overlapping interval

        // Iterate from the second interval
        for (int i = 1; i < intervals.length; i++) {
            int nextStart = intervals[i][0];
            int nextEnd = intervals[i][1];

            // If the next interval overlaps with the current one
            if (nextStart < currentEnd) {
                // Remove the current interval (increment count)
                // Don't update currentEnd because we want to keep the one that ends earlier
                // to maximize room for more intervals
                count++;
            } else {
                // No overlap, update currentEnd to the end of this interval
                currentEnd = nextEnd;
            }
        }

        return count;
    }
}
