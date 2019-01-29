package com.nsfocus.constant;

import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author wanghui
 * @Date 2019/1/23 20:54
 **/
public class NsfocusConstant {
    public static final char[] symbol = {' ', '/', '\\', ',', '.', ':', '，', '。', '：', '；', ';','（','）','　'};

    public static final String symbols;

    public static final String space = " ";

    public static final String blanke = "";

    static {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < symbol.length; i++) {
            if (symbol[i] == '.') {
                list.add("\\" + symbol[i]);
            } else if (symbol[i] == '\\') {
                list.add("\\" + symbol[i]);
            } else {
                list.add(symbol[i] + "");
            }
        }
        symbols = StringUtils.join(list, "|");
    }

    public static final List<String> stopwords = new ArrayList<>();

    static {
        try (BufferedReader bf = new BufferedReader(new FileReader(new File("src/main/resources/stopwords.txt")))) {
            String str;
            //按行读取字符串
            while ((str = bf.readLine()) != null) {
                if (null != str || !str.trim().equals("")) {
                    stopwords.add(str);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
