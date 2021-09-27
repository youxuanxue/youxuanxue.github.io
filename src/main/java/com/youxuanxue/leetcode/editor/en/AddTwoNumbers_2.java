//You are given two non-empty linked lists representing two non-negative integer
//s. The digits are stored in reverse order, and each of their nodes contains a si
//ngle digit. Add the two numbers and return the sum as a linked list.
//
// You may assume the two numbers do not contain any leading zero, except the nu
//mber 0 itself.
//
//
// Example 1:
//
//
//Input: l1 = [2,4,3], l2 = [5,6,4]
//Output: [7,0,8]
//Explanation: 342 + 465 = 807.
//
//
// Example 2:
//
//
//Input: l1 = [0], l2 = [0]
//Output: [0]
//
//
// Example 3:
//
//
//Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
//Output: [8,9,9,9,0,0,0,1]
//
//
//
// Constraints:
//
//
// The number of nodes in each linked list is in the range [1, 100].
// 0 <= Node.val <= 9
// It is guaranteed that the list represents a number that does not have leading
// zeros.
//
// Related Topics Linked List Math Recursion
// 👍 14118 👎 3143


package com.youxuanxue.leetcode.editor.en;


public class AddTwoNumbers_2 {

    public static void main(String[] args) {
        Solution solution = new AddTwoNumbers_2().new Solution();

        ListNode l1 = new AddTwoNumbers_2().new ListNode(
            9,
            new AddTwoNumbers_2().new ListNode(9)
        );

        ListNode l2 = new AddTwoNumbers_2().new ListNode(
            1,
        new AddTwoNumbers_2().new ListNode(9)

        );

        solution.addTwoNumbers(l1, l2);
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        public String toString() {
            StringBuffer buffer = new StringBuffer();
            buffer.append(val);
            ListNode curNext = next;
            while (curNext != null){
                buffer.append(curNext.val);
                curNext = curNext.next;
            }
            return String.join(",", buffer);
        }
    }

    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode pointer1 = l1;
            ListNode pointer2 = l2;

            // head 头指针不属于结果 result
            ListNode head = new ListNode(0);
            ListNode resultPointer = head;

            // 当前是否有进位
            int extraAdd = 0;

            while (pointer1 != null && pointer2 != null) {
                int currentSum = pointer1.val + pointer2.val + extraAdd;
                int currentNum = currentSum % 10;
                if (currentSum > 9) {
                    extraAdd = 1;
                } else {
                    extraAdd = 0;
                }
                resultPointer.next = new ListNode(currentNum);

                // 指针前移
                pointer1 = pointer1.next;
                pointer2 = pointer2.next;
                resultPointer = resultPointer.next;
            }

            ListNode nonEmpty = null;
            if (pointer1 != null) {
                nonEmpty = pointer1;
            } else {
                nonEmpty = pointer2;
            }

            // 两个指针不一样长
            while (nonEmpty != null) {
                int currentSum = nonEmpty.val + extraAdd;
                int currentNum = currentSum % 10;
                if (currentSum > 9) {
                    extraAdd = 1;
                } else {
                    extraAdd = 0;
                }
                resultPointer.next = new ListNode(currentNum);
                resultPointer = resultPointer.next;
                nonEmpty = nonEmpty.next;
            }

            // 最后的进位
            if (extraAdd == 1) resultPointer.next = new ListNode(1);

            // 结果打印
            System.out.println(String.format("%s + %s = %s", l1.toString(), l2.toString(), head.next.toString()));
            return head.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}