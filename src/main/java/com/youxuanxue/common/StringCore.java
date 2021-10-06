package com.youxuanxue.common;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Copyright @2021 xuejiao
 *
 * @author xuejiao <forsurexue@gmail.com>
 *         2021/10/06
 */
public class StringCore {
    public static String fromNums(int [] nums, String joiner) {
       return Arrays.stream(nums)
            .mapToObj(String::valueOf)
            .collect(Collectors.joining(joiner));
    }
}
