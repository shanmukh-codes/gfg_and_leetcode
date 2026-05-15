package strings;

/*
 * Example 1:
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * 
 * Example 2:
 * Input: strs = [""]
 * Output: [[""]]
 * 
 * Example 3:
 * Input: strs = ["a"]
 * Output: [["a"]]
 * 
 * Constraints:
 * 1 <= strs.length <= 104
 * 0 <= strs[i].length <= 100
 * strs[i] consists of lowercase English letters.
 * 
 * @param strs array of strings
 * @return list of groups of anagrams
 */

import java.util.*;

public class GroupAnagrams {

    // Approach 1: Using Sorted String as Key
    // Time complexity: O(n * k log k)
    // Space complexity: O(n * k)
    // where n is the number of strings and k is the maximum length of a string
    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }

        Map<String, List<String>> anagramMap = new HashMap<>();

        for (String s : strs) {
            // Sort the characters of the string to create a canonical representation
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String sortedStr = new String(chars);

            // Add the original string to the list corresponding to the sorted string
            if (!anagramMap.containsKey(sortedStr)) {
                anagramMap.put(sortedStr, new ArrayList<>());
            }
            anagramMap.get(sortedStr).add(s);
        }

        // Return the values of the map which are the groups of anagrams
        return new ArrayList<>(anagramMap.values());
    }

    // Approach 2: Using Character Count as Key
    // Time complexity: O(n * k)
    // Space complexity: O(n * k)
    // where n is the number of strings and k is the maximum length of a string
    public static List<List<String>> groupAnagramsOptimized(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }

        Map<String, List<String>> anagramMap = new HashMap<>();

        for (String s : strs) {
            // Create a character count array
            int[] count = new int[26];
            for (int i = 0; i < s.length(); i++) {
                count[s.charAt(i) - 'a']++;
            }

            // Create a unique key from the character count
            // Using a StringBuilder is more efficient than repeated string concatenation
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                sb.append(count[i]).append("#");
            }
            String key = sb.toString();

            // Add the original string to the list corresponding to the key
            if (!anagramMap.containsKey(key)) {
                anagramMap.put(key, new ArrayList<>());
            }
            anagramMap.get(key).add(s);
        }

        return new ArrayList<>(anagramMap.values());
    }

    public static void main(String[] args) {
        String[] strs1 = { "eat", "tea", "tan", "ate", "nat", "bat" };
        System.out.println("Approach 1: " + groupAnagrams(strs1));
        System.out.println("Approach 2: " + groupAnagramsOptimized(strs1));

        String[] strs2 = { "" };
        System.out.println("Approach 1: " + groupAnagrams(strs2));
        System.out.println("Approach 2: " + groupAnagramsOptimized(strs2));

        String[] strs3 = { "a" };
        System.out.println("Approach 1: " + groupAnagrams(strs3));
        System.out.println("Approach 2: " + groupAnagramsOptimized(strs3));
    }
}
