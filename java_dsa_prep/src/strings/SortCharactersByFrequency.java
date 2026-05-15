package strings;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
 * Given a string s, sort it in decreasing order based on the frequency of the
 * characters. The frequency of a character is the number of times it appears in
 * the string.
 * 
 * Return the sorted string. If there are multiple solutions, return any of them.
 * 
 * Example 1:
 * Input: s = "tree"
 * Output: "eert" or "eetr"
 * Explanation: 'e' appears twice while 'r' and 't' appear once.
 * So we sort them in reverse order.
 * 
 * Example 2:
 * Input: s = "ccaa"
 * Output: "ccaa" or "aacc"
 * Explanation: Both 'c' and 'a' appear twice.
 * Note that in one possible answer, 'c' appears first, while on another, 'a' appears first.
 * 
 * Example 3:
 * Input: s = "Aabb"
 * Output: "bbAa" or "bbaA"
 * Explanation: 'A' and 'a' are treated as two different characters.
 * 
 * Constraints:
 * 1 <= s.length <= 500
 * s consists of uppercase and lowercase English letters.
 * 
 * @param s the input string
 * @return the sorted string
 */

public class SortCharactersByFrequency {

    // Approach 1: HashMap + PriorityQueue (Max-Heap)
    // Time: O(N log K) where K is number of unique characters (at most 52)
    // Since K is constant, this is effectively O(N)
    // Space: O(K) for HashMap and PriorityQueue
    public static String frequencySort(String s) {
        // Step 1: Count character frequencies
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        // Step 2: Use a Max-Heap to sort by frequency
        // The heap will store characters, ordered by their frequency in descending
        // order
        PriorityQueue<Character> maxHeap = new PriorityQueue<>(
                (a, b) -> freqMap.get(b) - freqMap.get(a));

        maxHeap.addAll(freqMap.keySet());

        // Step 3: Build the result string
        StringBuilder result = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            char ch = maxHeap.poll();
            int frequency = freqMap.get(ch);

            // Append the character 'frequency' times
            for (int i = 0; i < frequency; i++) {
                result.append(ch);
            }
        }

        return result.toString();
    }

    // Approach 2: Bucket Sort (More optimized for fixed character range)
    // Time: O(N) - Linear time complexity
    // Space: O(N) - For the buckets array
    public static String frequencySortBucket(String s) {
        // Since the string consists of uppercase and lowercase English letters,
        // the maximum frequency can be s.length() (at most 500).
        // We can use an array of strings (or StringBuilder) as "buckets",
        // where the index represents the frequency.

        // Create buckets for frequencies 1 to s.length()
        // We use an array of StringBuilder for efficient string construction
        StringBuilder[] buckets = new StringBuilder[s.length() + 1];

        // Initialize buckets
        for (int i = 0; i <= s.length(); i++) {
            buckets[i] = new StringBuilder();
        }

        // Step 1: Count character frequencies and populate buckets
        for (char c : s.toCharArray()) {
            int freq = 0;
            // This inner loop is a bit tricky. We can't just use a simple count
            // because we need to distinguish between different characters with the same
            // frequency.
            // Actually, we can just append the character to the bucket corresponding to its
            // frequency.
            // But we need to know the frequency first.

            // Let's use a frequency map first for clarity, then optimize
        }

        // Optimized Bucket Sort:
        // We still need to count frequencies first
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        // Populate buckets based on frequencies
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            char ch = entry.getKey();
            int frequency = entry.getValue();

            // Append the character to the bucket corresponding to its frequency
            for (int i = 0; i < frequency; i++) {
                buckets[frequency].append(ch);
            }
        }

        // Step 2: Build the result string by iterating through buckets in reverse order
        StringBuilder result = new StringBuilder();
        for (int i = s.length(); i > 0; i--) {
            if (buckets[i] != null) {
                result.append(buckets[i]);
            }
        }

        return result.toString();
    }

    // Approach 3: Counting Sort (Even more optimized for character range)
    // Time: O(N) - Since character range is fixed (52 letters)
    // Space: O(1) - Constant extra space (fixed size array)
    public static String frequencySortCountingSort(String s) {
        // Step 1: Count character frequencies using a fixed-size array
        // ASCII values for 'a'-'z' are 97-122, 'A'-'Z' are 65-90
        int[] counts = new int[128]; // Sufficient for ASCII

        for (char c : s.toCharArray()) {
            counts[c]++;
        }

        // Step 2: Build the result string
        StringBuilder result = new StringBuilder(s.length());

        // Iterate through the counts array in reverse order (from highest possible char
        // value)
        // Or iterate from 'z' down to 'a' and 'Z' down to 'A' to ensure deterministic
        // output
        // The problem allows any valid output, but iterating in character order is
        // cleaner

        // Iterate through uppercase letters
        for (char c = 'Z'; c >= 'A'; c--) {
            int count = counts[c];
            for (int i = 0; i < count; i++) {
                result.append(c);
            }
        }

        // Iterate through lowercase letters
        for (char c = 'z'; c >= 'a'; c--) {
            int count = counts[c];
            for (int i = 0; i < count; i++) {
                result.append(c);
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String[] testCases = {
                "tree",
                "ccaa",
                "Aabb",
                "",
                "a",
                "Programming"
        };

        for (String s : testCases) {
            System.out.println("Original: " + s);
            System.out.println("PQ:       " + frequencySort(s));
            System.out.println("Bucket:   " + frequencySortBucket(s));
            System.out.println("Counting: " + frequencySortCountingSort(s));
            System.out.println("-".repeat(20));
        }
    }
}
