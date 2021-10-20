//Given an input string s and a pattern p, implement regular expression matching
// with support for '.' and '*' where:
//
//
// '.' Matches any single character.
// '*' Matches zero or more of the preceding element.
//
//
// The matching should cover the entire input string (not partial).
//
//
// Example 1:
//
//
//Input: s = "aa", p = "a"
//Output: false
//Explanation: "a" does not match the entire string "aa".
//
//
// Example 2:
//
//
//Input: s = "aa", p = "a*"
//Output: true
//Explanation: '*' means zero or more of the preceding element, 'a'. Therefore,
//by repeating 'a' once, it becomes "aa".
//
//
// Example 3:
//
//
//Input: s = "ab", p = ".*"
//Output: true
//Explanation: ".*" means "zero or more (*) of any character (.)".
//
//
// Example 4:
//
//
//Input: s = "aab", p = "c*a*b"
//Output: true
//Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, i
//t matches "aab".
//
//
// Example 5:
//
//
//Input: s = "mississippi", p = "mis.is*p*."
//Output: false
//
//
//
// Constraints:
//
//
// 1 <= s.length <= 20
// 1 <= p.length <= 30
// s contains only lowercase English letters.
// p contains only lowercase English letters, '.', and '*'.
// It is guaranteed for each appearance of the character '*', there will be a pr
//evious valid character to match.
//
// Related Topics String Dynamic Programming Recursion
// 👍 6865 👎 953


package com.youxuanxue.leetcode.editor.en;

public class Solution10RegularExpressionMatching {

    public static void main(String[] args) {
        Solution solution = new Solution10RegularExpressionMatching().new Solution();
        solution.isMatch("", "*");
        solution.isMatch("", "a*");
        solution.isMatch("a", "");
        solution.isMatch("aa", "a*");
        solution.isMatch("mississippi", "mis.is*p*.");
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isMatch(String s, String p) {
            /**
             * dp[m][n] s 的前 m 个字符串和 p 的前 n 个是否匹配
             * 初始化:
             * 1）dp[0][0] = true，空串是匹配的
             * 2）dp[x][0] = false, 空 p 不匹配非空串
             * 3）dp[0][x] 分情况处理，只有 x* 可以匹配，及双数 x* 可以匹配
             *
             * 子问题：
             * dp[i][j] 在面对 s[i] 和 p[j] 的情况处理
             *
             * 结果：
             * dp[m][n]
             *
             * todo(xuejiao): 可视化解读教程/视频
             */
            int m = s.length();
            int n = p.length();

            boolean[][] dp = new boolean[m + 1][n + 1];
            dp[0][0] = true;
            // 初始化行向量
            for (int j = 1; j <= n; ++j) {
                if (j == 1) {
                    dp[0][1] = false;
                } else if (p.charAt(j - 1) == '*') {
                    dp[0][j] = dp[0][j - 2];
                }
            }
            // 初始化列向量
            for (int i = 1; i <= m; i++) {
                dp[i][0] = false;
            }

            for (int i = 1; i <= m; ++i) {
                for (int j = 1; j <= n; ++j) {
                    if (matches(s, p, i, j)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else if (i == 1 && j == 1) {
                        // dp[1][1] 仅在首字符匹配的情况下，才有可能是 true，其他情况都是 false
                        dp[1][1] = false;
                    } else if (p.charAt(j - 1) == '*') {
                        // .* 匹配 0 次
                        dp[i][j] = dp[i][j - 2];
                        if (matches(s, p, i, j - 1)) {
                            // .* 匹配 1 次或多次
                            dp[i][j] = dp[i][j] || dp[i - 1][j];
                        }
                    }
                }
            }
            boolean success = dp[m][n];
            System.out.println(String.format("%s => %s, match: %s", s, p, success));
            return success;
        }


        /**
         * @param s raw string
         * @param p pattern string, contains '.' or '*'
         * @param i the first i length of s
         * @param j the first j length of p
         * @return true if s[i-1] matches p[j-1], else false
         */
        private boolean matches(String s, String p, int i, int j) {
            if (p.charAt(j - 1) == '.') {
                return true;
            }
            return s.charAt(i - 1) == p.charAt(j - 1);
        }
//leetcode submit region end(Prohibit modification and deletion)
    }
}