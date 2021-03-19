package com.iptv.season3.facetalk;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liuqi
 * @description:
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @create 2020-11-12 15:08
 */
public class TwoSumDemo {

    public static int[] twoSum(int[] nums, int target) {
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (target - nums[i] == nums[j]) {
                    // 记下i,j
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }


    public static int[] twoSum2(int[] nums, int target) {

        int length = nums.length;
        Map<Integer, Integer> map = new HashMap<>(length - 1);
        for (int i = 0; i < length; i++) {

            int another = target - nums[i];
            if (map.containsKey(another)) {
                return new int[]{map.get(another), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }

    public static void main(String[] args) {
        int[] ints = {3, 1, 7, 19};
        int[] result = twoSum2(ints, 20);

        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i]);
        }

    }
}
