//Given a string s, return the longest palindromic substring in s.
//
//
// Example 1:
//
//
//Input: s = "babad"
//Output: "bab"
//Note: "aba" is also a valid answer.
//
//
// Example 2:
//
//
//Input: s = "cbbd"
//Output: "bb"
//
//
// Example 3:
//
//
//Input: s = "a"
//Output: "a"
//
//
// Example 4:
//
//
//Input: s = "ac"
//Output: "a"
//
//
//
// Constraints:
//
//
// 1 <= s.length <= 1000
// s consist of only digits and English letters.
//
// Related Topics String Dynamic Programming
// 👍 13811 👎 816


package com.youxuanxue.leetcode.editor.en;

public class S5LongestPalindromicSubstring {

    public static void main(String[] args) {
        Solution solution = new S5LongestPalindromicSubstring().new Solution();
        solution.longestPalindrome("aabbc");
        solution.longestPalindrome("babad");
        solution.longestPalindrome("ac");
        solution.longestPalindrome("cbbd");
        solution.longestPalindrome("a");
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String longestPalindrome(String s) {
        /*
        一个指针从前，一个指针从后，找到对称的字符串，时间复杂度 O(n^2)
         */
            String currentPalindrome = s.substring(0, 1);
            int start = 0;
            while (start < s.length() && currentPalindrome.length() < s.length() - start) {
                int end = s.indexOf(s.charAt(start), start + 1);
                if (end != -1 &&
                    currentPalindrome.length() < end - start + 1 &&
                    isPalindrome(start, end, s)) {
                    currentPalindrome = s.substring(start, end + 1);

                }
                start += 1;
            }

            System.out.println(
                String.format("longest palindrome is [%s] from [%s]", currentPalindrome, s)
            );

            return currentPalindrome;
        }

        private boolean isPalindrome(int start, int end, String s) {
            while (start < end && s.indexOf(start) == s.indexOf(end)) {
                start += 1;
                end -= 1;
            }

            return start >= end;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}