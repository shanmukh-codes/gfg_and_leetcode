/*
 * Problem: LeetCode 23. Merge k Sorted Lists
 * 
 * You are given an array of k linked-lists 'lists', where each linked-list
 * is sorted in ascending order.
 * 
 * Merge all the linked-lists into one sorted linked-list and return it.
 * 
 * Example 1:
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * 
 * Example 2:
 * Input: lists = []
 * Output: []
 * 
 * Example 3:
 * Input: lists = [[]]
 * Output: []
 * 
 * Constraints:
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= node.val <= 10^4
 * lists[i].length is in the range [0, 500].
 * 1 <= lists[i].length <= 500. (Note: This constraint may contradict k=0 case, 
 *       but we'll handle empty input gracefully. Usually means at least one non-empty list if k>0).
 * All the lists are sorted in ascending order.
 */

package heaps;

import java.util.PriorityQueue;

public class MergeKSortedLists {

    public ListNode mergeKLists(ListNode[] lists) {
        // Handle edge cases: null array or empty array
        if (lists == null || lists.length == 0) {
            return null;
        }

        // Create a min-heap to store the head nodes of all lists
        // The comparator sorts based on the 'val' of the ListNode
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(lists.length, (a, b) -> a.val - b.val);

        // Add the head of each non-empty list to the heap
        for (ListNode head : lists) {
            if (head != null) {
                minHeap.offer(head);
            }
        }

        // Create a dummy head for the result list
        ListNode dummyHead = new ListNode(-1);
        ListNode current = dummyHead;

        // While the heap is not empty, extract the smallest node and add it to the
        // result
        while (!minHeap.isEmpty()) {
            // Get the node with the smallest value
            ListNode smallest = minHeap.poll();

            // Append it to the result list
            current.next = smallest;
            current = current.next;

            // If the smallest node has a next node, add it to the heap
            if (smallest.next != null) {
                minHeap.offer(smallest.next);
            }
        }

        // The merged sorted list starts from dummyHead.next
        return dummyHead.next;
    }

    public static void main(String[] args) {
        MergeKSortedLists solution = new MergeKSortedLists();

        // Test Case 1
        // Input: lists = [[1,4,5],[1,3,4],[2,6]]
        ListNode list1 = new ListNode(1, new ListNode(4, new ListNode(5)));
        ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode list3 = new ListNode(2, new ListNode(6));
        ListNode[] lists1 = { list1, list2, list3 };
        ListNode result1 = solution.mergeKLists(lists1);
        System.out.println("Test Case 1:");
        printList(result1);
        // Expected: [1,1,2,3,4,4,5,6]
        System.out.println("---------------------");

        // Test Case 2
        // Input: lists = []
        ListNode[] lists2 = {};
        ListNode result2 = solution.mergeKLists(lists2);
        System.out.println("Test Case 2:");
        printList(result2);
        // Expected: []
        System.out.println("---------------------");

        // Test Case 3
        // Input: lists = [[]]
        ListNode[] lists3 = { null };
        ListNode result3 = solution.mergeKLists(lists3);
        System.out.println("Test Case 3:");
        printList(result3);
        // Expected: []
        System.out.println("---------------------");

        // Test Case 4: Empty lists mixed with non-empty
        ListNode list4_1 = new ListNode(1);
        ListNode list4_2 = null;
        ListNode list4_3 = new ListNode(3);
        ListNode[] lists4 = { list4_1, list4_2, list4_3 };
        ListNode result4 = solution.mergeKLists(lists4);
        System.out.println("Test Case 4:");
        printList(result4);
        // Expected: [1,3]
        System.out.println("---------------------");

        // Test Case 5: Single list
        ListNode list5 = new ListNode(1, new ListNode(2, new ListNode(3)));
        ListNode[] lists5 = { list5 };
        ListNode result5 = solution.mergeKLists(lists5);
        System.out.println("Test Case 5:");
        printList(result5);
        // Expected: [1,2,3]
        System.out.println("---------------------");
    }

    // Helper function to print the linked list
    private static void printList(ListNode head) {
        if (head == null) {
            System.out.println("[]");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        ListNode current = head;
        while (current != null) {
            sb.append(current.val);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        System.out.println(sb.toString());
    }
}
