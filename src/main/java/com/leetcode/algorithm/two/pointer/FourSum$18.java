package com.leetcode.algorithm.two.pointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum$18 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return res;
        }
        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 0; i < len - 3; i ++) {
            if (i > 0 && nums[i] == nums[i - 1]) { continue; }
            for (int j = i + 1; j < len - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) { continue; }
                int k = j + 1;
                int l = len - 1;
                while (k < l) {
                    int sum = nums[i] + nums[j] + nums[k] + nums[l];
                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
                        ++k;
                        --l;
                        while(k < l && nums[k] == nums[k - 1] ) { ++k; }
                        while (k < l && nums[l] == nums[l + 1]) { --l; }
                    } else if (sum < target) {
                        ++k;
                    } else {
                        --l;
                    }
                }
            }
        }
        return res;
    }
}
