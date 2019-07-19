package com.lyb.util;

/**
 * @Auther: 野性的呼唤
 * @Date: 2019/7/18 17:06
 * @Description:
 */
public class StringUtil {

    /**
     * 判断字符串是否为空白，常见几种情况如下：
     * <pre>
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = true
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     * </pre>
     */
    private static boolean isBlank(String str) {
        int strLen;
        if (null == str || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((!Character.isWhitespace(str.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 根据特定值分割字符串
     */
    public static String cutting(String txt, String spot, String position){
        int length = txt.length();
        int spotLength = spot.length();
        int a = txt.indexOf(spot);

        switch (position) {
            case "l":
                return txt.substring(0, a);
            case "r":
                return txt.substring(a + spotLength, length);
            default:
                return null;
        }
    }
}
