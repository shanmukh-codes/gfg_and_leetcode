/*
 * Leetcode 621. Task Scheduler
 * 
 * Problem Description:
 * Given a characters array `tasks`, representing the tasks a CPU needs to do, where each
 * character represents a different task. Tasks could be done in any order. Each task is done in one
 * unit of time. For each unit of time, the CPU could complete either one task or just be idle.
 * 
 * However, there is a non-negative integer `n` that represents the cooldown period between two
 * same tasks (the same letter tasks). 
 * 
 * Return the minimum number of units of time so that the CPU completes all the given tasks.
 * 
 * Example 1:
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * Output: 8
 * Explanation: 
 * A -> B -> idle -> A -> B -> idle -> A -> B
 * 
 * Example 2:
 * Input: tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
 * Output: 16
 * 
 * Constraints:
 * 1 <= tasks.length <= 10^4
 * tasks.length <= n <= 100
 * tasks[i] is uppercase English letter.
 * 
 * Approach: Greedy + Math
 * 1. Count the frequency of each task
 * 2. Find the maximum frequency (maxFreq)
 * 3. The tasks with the maximum frequency determine the minimum time
 * 4. The formula is: (maxFreq - 1) * (n + 1) + numberOfMaxFreqTasks
 * 
 * Time Complexity: O(n) - iterate through tasks once
 * Space Complexity: O(1) - constant space for frequency map
 */

package greedy;

public class TaskScheduler {
    public int leastInterval(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0) {
            return 0;
        }
        if (n == 0) {
            return tasks.length;
        }

        // Count the frequency of each task
        // We only care about uppercase English letters (A-Z), so size 26 is enough
        int[] freq = new int[26];
        for (char task : tasks) {
            freq[task - 'A']++;
        }

        // Find the maximum frequency
        int maxFreq = 0;
        for (int count : freq) {
            maxFreq = Math.max(maxFreq, count);
        }

        // Count how many tasks have the maximum frequency
        int maxFreqCount = 0;
        for (int count : freq) {
            if (count == maxFreq) {
                maxFreqCount++;
            }
        }

        // The minimum number of time units is determined by the most frequent task.
        // Consider the most frequent task, say 'A', which appears `maxFreq` times.
        // We need to place the other tasks in the `n` slots between the 'A's.
        //
        // Visualization:
        // A _ _ A _ _ A ... A (maxFreq times)
        //
        // There are `maxFreq - 1` gaps between the 'A's.
        // Each gap needs to be at least `n` slots long (either other tasks or idle).
        // So each block of (A + gap) takes `n + 1` time units.
        //
        // Total time for these blocks = (maxFreq - 1) * (n + 1)
        //
        // Finally, we add the last block of tasks. The last block consists of all tasks
        // that have the maximum frequency.
        // Number of tasks in the last block = `maxFreqCount`
        //
        // Total time = (maxFreq - 1) * (n + 1) + maxFreqCount
        //
        // Example: ["A","A","A","B","B","B"], n = 2
        // maxFreq = 3, maxFreqCount = 2 (A and B)
        // Time = (3 - 1) * (2 + 1) + 2 = 2 * 3 + 2 = 8
        // A B _ A B _ A B
        //
        // Edge case: If we have many different tasks, we might not need any idle time.
        // The actual time will be at least the number of tasks.
        // So the result is the maximum of the calculated time and the number of tasks.

        int time = (maxFreq - 1) * (n + 1) + maxFreqCount;

        // The result cannot be less than the number of tasks
        return Math.max(time, tasks.length);
    }
}
