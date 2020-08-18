package com.jf.common.utils;

import java.math.BigDecimal;

/**
 * @author luoyb
 * Created on 2019/9/12
 */
public final class NumberUtil {

    public static int addAll(Integer... nums) {
        if (nums == null) return 0;

        int result = 0;
        for (Integer num : nums) {
            result += num == null ? 0 : num;
        }
        return result;
    }

    public static BigDecimal get(BigDecimal val) {
        return val == null ? BigDecimal.ZERO : val;
    }
}
