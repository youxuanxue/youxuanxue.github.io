//Given a signed 32-bit integer x, return x with its digits reversed. If reversi
//ng x causes the value to go outside the signed 32-bit integer range [-231, 231 -
// 1], then return 0.
//
// Assume the environment does not allow you to store 64-bit integers (signed or
// unsigned).
//
//
// Example 1:
// Input: x = 123
//Output: 321
// Example 2:
// Input: x = -123
//Output: -321
// Example 3:
// Input: x = 120
//Output: 21
// Example 4:
// Input: x = 0
//Output: 0
//
//
// Constraints:
//
//
// -231 <= x <= 231 - 1
//
// Related Topics Math
// 👍 5810 👎 8673


package com.youxuanxue.leetcode.editor.en;

public class Solution7ReverseInteger {

  public static void main(String[] args) {
       Solution solution = new Solution7ReverseInteger().new Solution();
      solution.reverse(123);
      solution.reverse(-123);
      solution.reverse(120);
      solution.reverse(0);
  }

  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int reverse(int x) {
        int result = 0;

        int tmp = x;
        while (tmp != 0) {
           result = result * 10 + tmp % 10;
           tmp = tmp / 10;
        }

        // todo(xuejiao): how to process overflow for int32

        System.out.println(String.format("%d reverse: %d", x, result));

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}