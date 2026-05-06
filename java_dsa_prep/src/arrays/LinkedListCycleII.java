/*
 * Given the head of a linked list, return the node where the cycle begins. If
 * there is no cycle, return null.
 * 
 * There is a cycle in a linked list if there is any node in the list that can
 * be reached again by continuously following the next pointer.
 * 
 * Return the starting node of the cycle. Otherwise, return null.
 * 
 * Example 1: Input: head = [3,2,0,-4], pos = 1 Output: tail points to index
 * 1, return 2 Explanation:
 * 
 * Example 2: Input: head = [1,2], pos = 0 Output: tail points to index 0, return
 * 1 Explanation: There is a cycle in the linked list, where the node at
 * position 0 is the pos
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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class LinkedListCycleII {

    // Brute Force Approach (Hash Map)
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public static ListNode detectCycleHashMap(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        Map<ListNode, Integer> visitedNodes = new HashMap<>();
        ListNode current = head;
        int index = 0;
        while (current != null) {
            if (visitedNodes.containsKey(current)) {
                return current;
            }
            visitedNodes.put(current, index++);
            current = current.next;
        }
        return null;
    }

    // Brute Force Approach (HashSet)
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public static ListNode detectCycleHashSet(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        HashSet<ListNode> visitedNodes = new HashSet<>();
        ListNode current = head;
        while (current != null) {
            if (visitedNodes.contains(current)) {
                return current;
            }
            visitedNodes.add(current);
            current = current.next;
        }
        return null;
    }

    // Optimal Approach (Floyd's Tortoise and Hare Algorithm)
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public static ListNode detectCycleOptimal(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;

        // Phase 1: Detect if a cycle exists
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break; // Cycle detected
            }
        }

        // If fast reached the end, there is no cycle
        if (fast == null || fast.next == null) {
            return null;
        }

        // Phase 2: Find the start of the cycle
        // Reset slow to head, keep fast at meeting point
        slow = head;

        // Move both pointers one step at a time
        // The point where they meet is the start of the cycle
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow; // This is the start of the cycle
    }

    public static void main(String[] args) {
        // Test case 1: Cycle exists (pos = 1)
        ListNode head1 = new ListNode(3);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(0);
        head1.next.next.next = new ListNode(-4);
        head1.next.next.next.next = head1.next; // Cycle: -4 -> 2

        System.out.println("Test Case 1:");
        ListNode cycleStart1 = detectCycleHashMap(head1);
        if (cycleStart1 != null) {
            System.out.println("Hash Map - Cycle starts at node with value: " + cycleStart1.val);
        } else {
            System.out.println("Hash Map - No cycle detected");
        }

        ListNode cycleStart1Opt = detectCycleOptimal(head1);
        if (cycleStart1Opt != null) {
            System.out.println("Optimal - Cycle starts at node with value: " + cycleStart1Opt.val);
        } else {
            System.out.println("Optimal - No cycle detected");
        }
        System.out.println();

        // Test case 2: Cycle exists (pos = 0)
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = head2; // Cycle: 2 -> 1

        System.out.println("Test Case 2:");
        ListNode cycleStart2 = detectCycleHashMap(head2);
        if (cycleStart2 != null) {
            System.out.println("Hash Map - Cycle starts at node with value: " + cycleStart2.val);
        } else {
            System.out.println("Hash Map - No cycle detected");
        }

        ListNode cycleStart2Opt = detectCycleOptimal(head2);
        if (cycleStart2Opt != null) {
            System.out.println("Optimal - Cycle starts at node with value: " + cycleStart2Opt.val);
        } else {
            System.out.println("Optimal - No cycle detected");
        }
        System.out.println();

        // Test case 3: No cycle
        ListNode head3 = new ListNode(1);

        System.out.println("Test Case 3:");
        ListNode cycleStart3 = detectCycleHashMap(head3);
        if (cycleStart3 != null) {
            System.out.println("Hash Map - Cycle starts at node with value: " + cycleStart3.val);
        } else {
            System.out.println("Hash Map - No cycle detected");
        }

        ListNode cycleStart3Opt = detectCycleOptimal(head3);
        if (cycleStart3Opt != null) {
            System.out.println("Optimal - Cycle starts at node with value: " + cycleStart3Opt.val);
        } else {
            System.out.println("Optimal - No cycle detected");
        }
        System.out.println();

        // Test case 4: Empty list
        ListNode head4 = null;

        System.out.println("Test Case 4:");
        ListNode cycleStart4 = detectCycleHashMap(head4);
        if (cycleStart4 != null) {
            System.out.println("Hash Map - Cycle starts at node with value: " + cycleStart4.val);
        } else {
            System.out.println("Hash Map - No cycle detected");
        }

        ListNode cycleStart4Opt = detectCycleOptimal(head4);
        if (cycleStart4Opt != null) {
            System.out.println("Optimal - Cycle starts at node with value: " + cycleStart4Opt.val);
        } else {
            System.out.println("Optimal - No cycle detected");
        }
    }
}
