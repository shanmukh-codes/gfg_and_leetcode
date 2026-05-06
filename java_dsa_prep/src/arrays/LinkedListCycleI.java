/*
 * Given the head of a linked list, determine if the linked list has a cycle in
 * it.
 * 
 * There is a cycle in a linked list if there is any node in the list that can
 * be reached again by continuously following the next pointer.
 * 
 * Return true if there is a cycle in the linked list. Otherwise, return false.
 * 
 * Example 1: Input: head = [3,2,0,-4], pos = 1 Output: true Explanation:
 * There is a cycle in the linked list, where the node at position 1 is the
 * pos
 * 
 * Example 2: Input: head = [1,2], pos = 0 Output: true Explanation: There is a
 * cycle in the linked list, where the node at position 0 is the pos
 * 
 * Example 3: Input: head = [1], pos = -1 Output: false Explanation: There is no
 * cycle in the linked list.
 * 
 * Constraints:
 * 
 * The number of the nodes in the list is in the range [0, 10^4].
 * -10^5 <= Node.val <= 10^5
 * pos is -1 or a valid index in the linked list.
 * 
 * Follow up: Can you solve it without using extra space?
 */

package arrays;

import java.util.HashSet;
import java.util.Set;

public class LinkedListCycleI {

    // Brute Force Approach
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public static boolean hasCycleBruteForce(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        Set<ListNode> visitedNodes = new HashSet<>();
        ListNode current = head;
        while (current != null) {
            if (visitedNodes.contains(current)) {
                return true;
            }
            visitedNodes.add(current);
            current = current.next;
        }
        return false;
    }

    // Optimal Approach: Floyd's Tortoise and Hare Algorithm
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public static boolean hasCycleOptimal(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // Test case 1
        ListNode head1 = new ListNode(3);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(0);
        head1.next.next.next = new ListNode(-4);
        head1.next.next.next.next = head1.next;
        System.out.println("Brute Force Approach - Test case 1: " + hasCycleBruteForce(head1));
        System.out.println("Optimal Approach - Test case 1: " + hasCycleOptimal(head1));

        // Test case 2
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = head2;
        System.out.println("Brute Force Approach - Test case 2: " + hasCycleBruteForce(head2));
        System.out.println("Optimal Approach - Test case 2: " + hasCycleOptimal(head2));

        // Test case 3
        ListNode head3 = new ListNode(1);
        System.out.println("Brute Force Approach - Test case 3: " + hasCycleBruteForce(head3));
        System.out.println("Optimal Approach - Test case 3: " + hasCycleOptimal(head3));

        // Test case 4
        ListNode head4 = null;
        System.out.println("Brute Force Approach - Test case 4: " + hasCycleBruteForce(head4));
        System.out.println("Optimal Approach - Test case 4: " + hasCycleOptimal(head4));
    }
}
