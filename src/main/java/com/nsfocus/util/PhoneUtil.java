package com.nsfocus.util;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description
 * @Author wanghui
 * @Date 2019/1/25 15:06
 **/
public class PhoneUtil {
    public static Pattern mobilePattern = Pattern.compile("[1][3,4,5,6,7,8][0-9]{9}");
    public static Pattern phonePatternWithZone = Pattern.compile("[0][1-9]{2,3}-[0-9]{5,10}");
    public static Pattern phonePattern = Pattern.compile("[1-9]{1}[0-9]{7,8}");
    /**
     * 手机号验证
     *
     * @param  str
     * @return 验证通过返回true
     */
    public static List<String> matchMobile(Pattern p, String str) {
        Matcher m = p.matcher(str);
        List<String> list = new ArrayList<>();
        while (m.find()) {
            list.add(m.group());
        }
        return list;
    }

    public static List<String> matchAllPhone(String str) {
        List<String> list = new ArrayList<>();
        list.addAll(matchMobile(mobilePattern, str));
        list.addAll(matchMobile(phonePatternWithZone, str));
        list.addAll(matchMobile(phonePattern, str));

        return list;
    }

    public static String join(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            if (!sb.toString().contains(s)) {
                if (sb.length() != 0) {
                    sb.append(",");
                }
                sb.append(s);
            }
        }
        return sb.toString();
    }

    public static String join(String str) {
        return join(matchAllPhone(str));
    }
}
