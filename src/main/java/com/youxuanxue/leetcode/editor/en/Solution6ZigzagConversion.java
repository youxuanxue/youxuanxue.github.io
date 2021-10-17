//The string "PAYPALISHIRING" is written in a zigzag pattern on a given number o
//f rows like this: (you may want to display this pattern in a fixed font for bett
//er legibility)
//
//
//P   A   H   N
//A P L S I I G
//Y   I   R
//
//
// And then read line by line: "PAHNAPLSIIGYIR"
//
// Write the code that will take a string and make this conversion given a numbe
//r of rows:
//
//
//string convert(string s, int numRows);
//
//
//
// Example 1:
//
//
//Input: s = "PAYPALISHIRING", numRows = 3
//Output: "PAHNAPLSIIGYIR"
//
//
// Example 2:
//
//
//Input: s = "PAYPALISHIRING", numRows = 4
//Output: "PINALSIGYAHRPI"
//Explanation:
//P     I    N
//A   L S  I G
//Y A   H R
//P     I
//
//
// Example 3:
//
//
//Input: s = "A", numRows = 1
//Output: "A"
//
//
//
// Constraints:
//
//
// 1 <= s.length <= 1000
// s consists of English letters (lower-case and upper-case), ',' and '.'.
// 1 <= numRows <= 1000
//
// Related Topics String
// 👍 2897 👎 6895


package com.youxuanxue.leetcode.editor.en;

import java.util.LinkedList;

import com.youxuanxue.common.GeneralTreeNode;

public class Solution6ZigzagConversion {

    public static void main(String[] args) {
        Solution solution = new Solution6ZigzagConversion().new Solution();

        solution.convert("PAYPALISHIRING", 3);
        solution.convert("PAYPALISHIRING", 4);
        solution.convert("A", 1);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String convert(String s, int numRows) {
            String result = null;
            if(numRows > 1) {
                GeneralTreeNode root = buildBinaryTree(s, numRows);
                result = root.bfs();
            } else {
                result = s;
            }

            System.out.println(String.format("convert result: %s", result));

            return result;
        }

        private GeneralTreeNode buildBinaryTree(String s, int depth) {
            GeneralTreeNode root = new GeneralTreeNode();
            root.children = new LinkedList<>();

            // 一颗子树的节点数是 depth（左子树左节点）+（depth - 2）（分叉右子树的个数，用于连接前后两颗子树）
            int subTreeNodeNum = depth + (depth - 2);
            int index = 0;
            while (index < s.length()) {
                int currentEnd = index + subTreeNodeNum;
                String subString = null;
                if (currentEnd > s.length()) {
                    subString = s.substring(index);
                    currentEnd = s.length();
                } else {
                    subString = s.substring(index, index + subTreeNodeNum);
                }

                GeneralTreeNode child = buildSubTree(subString, depth);
                root.children.add(child);

                index = currentEnd;
            }

            return root;
        }

        private GeneralTreeNode buildSubTree(String s, int depth) {
            GeneralTreeNode root = null, father = null;

            // 构造纯左子树，最大深度为 depth
            int minLen = Math.min(s.length(), depth);
            for (int index = 0; index < minLen; index++) {
                GeneralTreeNode currentNode = new GeneralTreeNode();
                currentNode.value = s.substring(index, index + 1);
                currentNode.children = new LinkedList<>();
                if (index == 0) {
                    root = currentNode;
                } else {
                    currentNode.father = father;
                    father.children.add(currentNode);
                }
                father = currentNode;
            }

            // 构造左子树的右边分叉
            father = father.father;
            for (int index = minLen; index < s.length(); index ++) {
                GeneralTreeNode currentNode = new GeneralTreeNode();
                currentNode.value = s.substring(index, index + 1);
                currentNode.children = new LinkedList<>();
                father.children.add(currentNode);
                father = father.father;
            }

            return root;
        }

    }


//leetcode submit region end(Prohibit modification and deletion)

}