//Given a string s, find the length of the longest substring without repeating c
//haracters.
//
//
// Example 1:
//
//
//Input: s = "abcabcbb"
//Output: 3
//Explanation: The answer is "abc", with the length of 3.
//
//
// Example 2:
//
//
//Input: s = "bbbbb"
//Output: 1
//Explanation: The answer is "b", with the length of 1.
//
//
// Example 3:
//
//
//Input: s = "pwwkew"
//Output: 3
//Explanation: The answer is "wke", with the length of 3.
//Notice that the answer must be a substring, "pwke" is a subsequence and not a
//substring.
//
//
// Example 4:
//
//
//Input: s = ""
//Output: 0
//
//
//
// Constraints:
//
//
// 0 <= s.length <= 5 * 104
// s consists of English letters, digits, symbols and spaces.
//
// Related Topics Hash Table String Sliding Window
// 👍 17890 👎 830


package com.youxuanxue.leetcode.editor.en;

import java.util.HashMap;

public class S3LongestSubstringWithoutRepeatingCharacters {

  public static void main(String[] args) {
       Solution solution = new S3LongestSubstringWithoutRepeatingCharacters().new Solution();

      solution.lengthOfLongestSubstring("pwwkew");
      solution.lengthOfLongestSubstring("abcbdef");
      solution.lengthOfLongestSubstring("aaaa");
      solution.lengthOfLongestSubstring("");
  }

  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lengthOfLongestSubstring(String s) {

        // 记录历史最长的 substring 情况
        String longestSubstring = "";
        int longestSubstringLength = 0;

        // 当前最长 substring 的中间数据
        HashMap<Character, Integer> charPositionMap = new HashMap<>();
        int currentPositionStart = 0;
        int currentPosition = 0;
        while (currentPosition < s.length()) {
            if (!charPositionMap.containsKey(s.charAt(currentPosition))) {
                charPositionMap.put(s.charAt(currentPosition), currentPosition);
            } else {
                int currentSubstringLength = currentPosition - currentPositionStart;
                if (currentSubstringLength > longestSubstringLength) {
                    longestSubstring = s.substring(currentPositionStart, currentPosition);;
                    longestSubstringLength = currentSubstringLength;
                }

                int duplicatedPosition = charPositionMap.get(s.charAt(currentPosition));
                currentPositionStart = duplicatedPosition + 1;
                charPositionMap = new HashMap<>();
                for (int index = currentPositionStart; index <= currentPosition; index++) {
                    charPositionMap.put(s.charAt(index), index);
                }
            }

            currentPosition += 1;
        }

        // 最后的子串是否是最长的
        int currentSubstringLength = currentPosition - currentPositionStart;
        if (currentSubstringLength > longestSubstringLength) {
            longestSubstring = s.substring(currentPositionStart, currentPosition);;
            longestSubstringLength = currentSubstringLength;
        }

        System.out.println(
            String.format(
                "longest substring %s(%d)(position:[%d, %d] in %s)",
                longestSubstring, longestSubstringLength, currentPositionStart, currentPosition, s
            )
        );

        return longestSubstringLength;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}