/*
 * 
 */
package strings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentWords {
    public static List<String> topKFrequent(String[] words, int k) {
        List<String> result = new ArrayList<>();
        if (words == null || words.length == 0 || k <= 0) {
            return result;
        }

        Map<String, Integer> freqMap = new HashMap<>();
        for (String word : words) {
            freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<String> minHeap = new PriorityQueue<>((a, b) -> {
            int freqComparison = freqMap.get(a) - freqMap.get(b);
            if (freqComparison == 0) {
                return b.compareTo(a);
            }
            return freqComparison;
        });

        for (String word : freqMap.keySet()) {
            minHeap.offer(word);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll());
        }
        Collections.reverse(result);
        return result;
    }

    public static void main(String[] args) {
        String[] words = { "the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is" };
        int k = 4;
        System.out.println(topKFrequent(words, k));
    }
}
