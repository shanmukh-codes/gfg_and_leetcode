package strings;

import java.util.Arrays;
import java.util.Collections;

public class ReverseWordsInString {
    public static String reverseWords(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        // 1. Remove extra spaces and split
        String[] words = s.trim().split("\s+");
        // 2. Reverse the array
        Collections.reverse(Arrays.asList(words));
        // 3. Join
        return String.join(" ", words);
    }

    // Interview Version: In-place (or using StringBuilder)
    public static String reverseWordsInPlace(String s) {
        StringBuilder sb = new StringBuilder();
        int n = s.length();

        for (int i = n - 1; i >= 0; i--) {
            // Skip trailing/multiple spaces
            if (s.charAt(i) == ' ') {
                continue;
            }
            // Found the end of a word
            int j = i;
            while (j >= 0 && s.charAt(j) != ' ') {
                j--;
            }
            // Extract word (substring is exclusive of end index)
            String word = s.substring(j + 1, i + 1);
            if (sb.length() > 0) {
                sb.append(" "); // Add space before word (since we are going backwards)
            }
            sb.append(word);

            // Move pointer to the start of the word we just processed
            i = j + 1;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(reverseWords("the sky is blue"));
        System.out.println(reverseWords("  hello world  "));
        System.out.println(reverseWords("a good   example"));
        System.out.println("In-place:\n" + reverseWordsInPlace("the sky is blue"));
        System.out.println("In-place:\n" + reverseWordsInPlace("  hello world  "));
    }
}
