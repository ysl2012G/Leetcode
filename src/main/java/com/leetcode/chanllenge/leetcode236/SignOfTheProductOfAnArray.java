package com.leetcode.chanllenge.leetcode236;

public class SignOfTheProductOfAnArray {
    public int arraySign(int[] nums) {
        int negativeCount = 0;
        for (int num : nums) {
            if (num == 0) {
                return 0;
            } else if (num < 0) {
                ++negativeCount;
            }
        }

        return negativeCount % 2 == 0 ? 1 : -1;

    }
}