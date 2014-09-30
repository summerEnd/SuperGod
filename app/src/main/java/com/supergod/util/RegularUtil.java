package com.supergod.util;

import android.os.PatternMatcher;

import java.util.regex.Pattern;

/**
 * Created by acer on 2014/9/30.
 */
public class RegularUtil {
    public class Regular {
        /**
         * 手机号
         */
        public static final String MOBILE = "^((14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$";
        /**
         * 电话号码
         * 匹配形式如 0511-4405222 或 021-87888822
         */
        public static final String TEL_PATTERN = "\\d{3}-\\d{8}|\\d{4}-\\d{7}";
        /**
         * 邮箱
         */
        public static final String EMAIL_PATTERN = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        /**
         * 字母开头
         */
        public static final String PATTERN_a123 = "^[a-zA-Z][a-zA-Z0-9_]{4,15}$";
        /**
         * 邮政编码
         */
        public static final String POSTAL_CODE = "[1-9]\\d{5}(?!\\d)";
        /**
         * 身份证
         */
        public static final String ID_CARD = "\\d{15}|\\d{18}";
        /**
         * IP地址
         */
        public static final String IP = "\\d+\\.\\d+\\.\\d+\\.\\d+";
        /**
         * 匹配由26个英文字母组成的字符串
         */
        public static final String PATTERN_aBc = "^[A-Za-z]+$";
        /**
         * 匹配由26个英文字母的大写组成的字符串
         */
        public static final String PATTERN_ABC = "^[A-Z]+$";
        /**
         * 匹配由26个英文字母的小写组成的字符串
         */
        public static final String PATTERN_abc = "^[a-z]+$";
    }

    private String pattern;
    private String input;

    /**
     * @param pattern 见Regular
     */
    public RegularUtil(String pattern, String input) {
        this.pattern = pattern;
        this.input = input;
    }

    public boolean matches() {
        return pattern.matches(input);
    }
}
