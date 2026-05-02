/*  
 * Problem: In a row of trees, each tree produces a fruit of type specified by 
 * an integer in the array fruits. Return the maximum number of fruits you can 
 * collect if you can pick at most two types of fruits.
 * 
 * Input: fruits = [1,2,1,3,2]
 * Output: 4
 * Explanation:
 * You can pick from trees 1, 2, 1, 2, giving a total of 4 fruits.
 * 
 * Time Complexity: O(n) when using sliding window, O(n^2) when using brute force
 * Space Complexity: O(1)
 */
package arrays;

import java.util.HashMap;

public class FruitIntoBaskets {

    // Brute Force Approach
    public static int fruitIntoBasketsBruteForce(int[] fruits) {
        int maxCount = 0;
        int n = fruits.length;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                // Check how many types of fruits are in the subarray fruits[i..j]
                int[] fruitTypes = new int[10]; // Assuming fruit types are 0-9
                int count = 0;
                for (int k = i; k <= j; k++) {
                    if (fruitTypes[fruits[k]] == 0) {
                        count++;
                    }
                    fruitTypes[fruits[k]]++;
                }
                if (count <= 2) {
                    maxCount = Math.max(maxCount, j - i + 1);
                }
            }
        }
        return maxCount;
    }

    // Optimal Approach
    public static int fruitIntoBasketsOptimal(int[] fruits) {
        int maxCount = 0;
        int n = fruits.length;
        int left = 0;
        int right = 0;
        int[] fruitTypes = new int[10]; // Assuming fruit types are 0-9
        int count = 0;
        while (right < n) {
            if (fruitTypes[fruits[right]] == 0) {
                count++;
            }
            fruitTypes[fruits[right]]++;
            while (count > 2) {
                fruitTypes[fruits[left]]--;
                if (fruitTypes[fruits[left]] == 0) {
                    count--;
                }
                left++;
            }
            maxCount = Math.max(maxCount, right - left + 1);
            right++;
        }
        return maxCount;
    }

    // Optimal Approach - HashMap
    public static int fruitIntoBasketsOptimalHashMap(int[] fruits) {
        int maxCount = 0;
        int n = fruits.length;
        int left = 0;
        int right = 0;
        HashMap<Integer, Integer> fruitTypes = new HashMap<>();
        while (right < n) {
            fruitTypes.put(fruits[right], fruitTypes.getOrDefault(fruits[right], 0) + 1);
            while (fruitTypes.size() > 2) {
                fruitTypes.put(fruits[left], fruitTypes.get(fruits[left]) - 1);
                if (fruitTypes.get(fruits[left]) == 0) {
                    fruitTypes.remove(fruits[left]);
                }
                left++;
            }
            maxCount = Math.max(maxCount, right - left + 1);
            right++;
        }
        return maxCount;
    }

    // Main method for testing
    public static void main(String[] args) {
        int[] fruits = { 1, 2, 1, 3, 2 };
        System.out.println("Fruit Into Baskets (Brute Force): " + fruitIntoBasketsBruteForce(fruits));
        System.out.println("Fruit Into Baskets (Optimal): " + fruitIntoBasketsOptimal(fruits));

        int[] fruits2 = { 0, 1, 2, 2 };
        System.out.println("Fruit Into Baskets (Brute Force): " + fruitIntoBasketsBruteForce(fruits2));
        System.out.println("Fruit Into Baskets (Optimal): " + fruitIntoBasketsOptimal(fruits2));

        int[] fruits3 = { 1, 2, 3, 2, 2 };
        System.out.println("Fruit Into Baskets (Brute Force): " + fruitIntoBasketsBruteForce(fruits3));
        System.out.println("Fruit Into Baskets (Optimal): " + fruitIntoBasketsOptimal(fruits3));
    }
}
