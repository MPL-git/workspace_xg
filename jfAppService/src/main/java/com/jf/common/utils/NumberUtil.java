package com.jf.common.utils;

import java.math.BigDecimal;
import java.util.Random;

/**
 * @author luoyb
 * Created on 2019/9/27
 */
public final class NumberUtil {

    private static Random random = new Random();

    public static int max(int one, int two) {
        return one > two ? one : two;
    }

    public static int min(int one, int two) {
        return one < two ? one : two;
    }

    public static BigDecimal max(BigDecimal one, BigDecimal two) {
        return one.compareTo(two) >= 0 ? one : two;
    }

    public static BigDecimal min(BigDecimal one, BigDecimal two) {
        return one.compareTo(two) < 0 ? one : two;
    }

    public static BigDecimal get(BigDecimal val) {
        return val == null ? BigDecimal.ZERO : val;
    }

    /**
     * 过滤限制条件下，实际可用数量
     *
     * @param limit 限制配置（0为不限制）
     * @param value 过滤前数值
     * @return 过滤后数值
     */
    public static int filterLimit(int limit, int value) {
        if (limit == 0) {
            return value;
        }
        return limit < value ? limit : value;
    }

    /**
     * 用于随机生成 大于等于begin,小于等于end 的数值
     */
    public static int rand(int begin, int end) {
        return begin + random.nextInt(end - begin + 1);
    }

    /**
     * 装饰折扣：0.30 转化为 3
     */
    public static String decorateDiscount(BigDecimal discount) {
        return discount.multiply(BigDecimal.TEN).stripTrailingZeros().toPlainString();
    }

    /**
     * 装饰面额
     */
    public static String decorateDenomination(BigDecimal denomination) {
        return denomination.stripTrailingZeros().toPlainString();
    }

    public static boolean gtZero(BigDecimal amount) {
        return amount != null && amount.compareTo(BigDecimal.ZERO) > 0;
    }
}
