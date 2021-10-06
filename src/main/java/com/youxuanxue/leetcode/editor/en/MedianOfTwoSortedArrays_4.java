//Given two sorted arrays nums1 and nums2 of size m and n respectively, return t
//he median of the two sorted arrays.
//
// The overall run time complexity should be O(log (m+n)).
//
//
// Example 1:
//
//
//Input: nums1 = [1,3], nums2 = [2]
//Output: 2.00000
//Explanation: merged array = [1,2,3] and median is 2.
//
//
// Example 2:
//
//
//Input: nums1 = [1,2], nums2 = [3,4]
//Output: 2.50000
//Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
//
//
// Example 3:
//
//
//Input: nums1 = [0,0], nums2 = [0,0]
//Output: 0.00000
//
//
// Example 4:
//
//
//Input: nums1 = [], nums2 = [1]
//Output: 1.00000
//
//
// Example 5:
//
//
//Input: nums1 = [2], nums2 = []
//Output: 2.00000
//
//
//
// Constraints:
//
//
// nums1.length == m
// nums2.length == n
// 0 <= m <= 1000
// 0 <= n <= 1000
// 1 <= m + n <= 2000
// -106 <= nums1[i], nums2[i] <= 106
//
// Related Topics Array Binary Search Divide and Conquer
// 👍 12668 👎 1714


package com.youxuanxue.leetcode.editor.en;

import com.youxuanxue.common.StringCore;

public class MedianOfTwoSortedArrays_4 {

    public static void main(String[] args) {
        System.out.println("======solution===");
        Solution solution = new MedianOfTwoSortedArrays_4().new Solution();
        solution.findMedianSortedArrays(new int[]{1,2,3,4}, new int[]{5});
        solution.findMedianSortedArrays(new int[]{1,2,3}, new int[]{5});
        solution.findMedianSortedArrays(new int[]{1,2}, new int[]{3,4});
        solution.findMedianSortedArrays(new int[]{}, new int[]{3});
        solution.findMedianSortedArrays(new int[]{1,2,3,4,5,6,7,8,9}, new int[]{1,2});

        System.out.println("======solution 2===");
        Solution2 solution2 = new MedianOfTwoSortedArrays_4().new Solution2();
        solution2.findMedianSortedArrays(new int[]{1,2,3,4}, new int[]{5});
        solution2.findMedianSortedArrays(new int[]{1,2,3}, new int[]{5});
        solution2.findMedianSortedArrays(new int[]{1,2}, new int[]{3,4});
        solution2.findMedianSortedArrays(new int[]{}, new int[]{3});
        solution2.findMedianSortedArrays(new int[]{1,2,3,4,5,6,7,8,9}, new int[]{1,2});
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int totalLen = nums1.length + nums2.length;
            double median = 0;
            if (totalLen % 2 == 1) {
                median= findKthElement(nums1, nums2, totalLen / 2 + 1);
            } else {
                double median1 = findKthElement(nums1, nums2, totalLen / 2 + 1);
                double median2 = findKthElement(nums1, nums2, totalLen / 2);
                median= (median1 + median2) / 2.0;
            }

            System.out.println(String.format(" nums1: %s\n nums2: %s \n median: %.1f\n",
                                             StringCore.fromNums(nums1, ","),
                                             StringCore.fromNums(nums2, ","),
                                             median
            ));
            return median;
        }

        /**
         * 从两个有序数组中找到合并后的第 K 个元素
         * @param nums1 有序数组，正序
         * @param nums2 有序数组，正序
         * @param k 位置 k
         * @return 合并后的第 k 个位置的元素
         */
        private double findKthElement(int[] nums1, int[] nums2, int k) {
            int index1 = 0, index2 = 0;
            int length1 = nums1.length, length2 = nums2.length;

            while (true) {
                if (index1 == nums1.length) {
                    return nums2[index2 + k - 1];
                }
                if (index2 == nums2.length) {
                    return nums1[index1 + k - 1];
                }
                if (k == 1) {
                    return Math.min(nums1[index1], nums2[index2]);
                }

                int half = k / 2;
                int newIndex1 = Math.min(index1 + half, length1) - 1;
                int newIndex2 = Math.min(index2 + half, length2) - 1;
                if (nums1[newIndex1] < nums2[newIndex2]) {
                    k -= (newIndex1 - index1 + 1);
                    index1 = newIndex1 + 1;
                } else {
                    k -= (newIndex2 - index2 + 1);
                    index2 = newIndex2 + 1;
                }
            }
        }
    }
//leetcode submit regionnd(Prohibit modification and deletion)

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution2 {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int [] shortNums = nums1, longNums = nums2;
            if (nums1.length > nums2.length) {
                shortNums = nums2;
                longNums = nums1;
            }
            int totalLen = shortNums.length + longNums.length;
            int medianIdx = totalLen/2;
            int leftMax = 0, rightMin = 0;
            int left = 0, right = shortNums.length;
            while (left <= right) {
                int shortPivotIdx = (left + right)/2;
                int longPivotIdx = medianIdx - shortPivotIdx;

                int shortPivotPrevious = shortPivotIdx == 0 ? Integer.MIN_VALUE : shortNums[shortPivotIdx - 1];
                int shortPivot = shortPivotIdx == shortNums.length ? Integer.MAX_VALUE: shortNums[shortPivotIdx];
                int longPivotPrevious = longPivotIdx == 0? Integer.MIN_VALUE: longNums[longPivotIdx - 1];
                int longPivot = longPivotIdx == longNums.length ? Integer.MAX_VALUE: longNums[longPivotIdx];

                if (shortPivotPrevious <= longPivot) {
                    leftMax = Math.max(shortPivotPrevious, longPivotPrevious);
                    rightMin = Math.min(shortPivot, longPivot);
                    left = shortPivotIdx + 1;
                } else {
                    right = shortPivotIdx - 1;
                }
            }

            double median = totalLen % 2 == 0 ? (leftMax + rightMin)/2.0 : rightMin;
            System.out.println(String.format(" nums1: %s\n nums2: %s \n median: %.1f\n",
                                             StringCore.fromNums(nums1, ","),
                                             StringCore.fromNums(nums2, ","),
                                             median
            ));
            return median;
        }
    }
//leetcode submit regionnd(Prohibit modification and deletion)


}