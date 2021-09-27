//Given an array of integers nums and an integer target, return indices of the t
//wo numbers such that they add up to target.
//
// You may assume that each input would have exactly one solution, and you may n
//ot use the same element twice.
//
// You can return the answer in any order.
//
//
// Example 1:
//
//
//Input: nums = [2,7,11,15], target = 9
//Output: [0,1]
//Output: Because nums[0] + nums[1] == 9, we return [0, 1].
//
//
// Example 2:
//
//
//Input: nums = [3,2,4], target = 6
//Output: [1,2]
//
//
// Example 3:
//
//
//Input: nums = [3,3], target = 6
//Output: [0,1]
//
//
//
// Constraints:
//
//
// 2 <= nums.length <= 104
// -109 <= nums[i] <= 109
// -109 <= target <= 109
// Only one valid answer exists.
//
//
//
//Follow-up: Can you come up with an algorithm that is less than O(n2) time comp
//lexity? Related Topics Array Hash Table
// 👍 24866 👎 818


package com.youxuanxue.leetcode.editor.en;

import java.util.Arrays;
import java.util.HashMap;

public class TwoSum_1 {

  public static void main(String[] args) {
       Solution solution = new TwoSum_1().new Solution();

       int[] nums = {2,7,11,15};

       solution.twoSum(nums, 9);
  }

  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] twoSum(int[] nums, int target) {
        // 1. 记录位置索引，对值排序 O(N*logN)
        HashMap<Integer,Integer> indexMap = new HashMap<Integer, Integer>();
        for (int idx = 0; idx < nums.length; idx++) {
            indexMap.put(nums[idx], idx);
        }
        Integer[] sorted = indexMap.keySet().toArray(new Integer[0]);
        Arrays.sort(sorted);

        // 2. 前后指针分别往中间移动 O(N)
        int start = 0;
        int end = sorted.length - 1;
        int[] results = {-1, -1};
        while (start < end) {
            int currentSum = sorted[start] + sorted[end];
            if (currentSum == target) {
                results[0] = indexMap.get(sorted[start]).intValue();
                results[1] = indexMap.get(sorted[end]).intValue();
                return results;
            } else if (currentSum < target) {
                start += 1;
            } else {
                end -= 1;
            }
        }

        System.out.println(results[0] + " - " + results[1]);

        return results;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}