package com.nsfocus.extract;

import com.nsfocus.constant.NsfocusConstant;
import com.nsfocus.pojo.InfoModel;
import com.nsfocus.util.PhoneUtil;
import com.nsfocus.util.SegmentUtil;
import org.apache.commons.lang.StringUtils;
import org.apdplat.word.recognition.PersonName;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author wanghui
 * @Date 2019/1/25 11:46
 **/
public class OtherExtractor implements Extractor {
    private static Extractor extractor;

    private OtherExtractor() {
    }

    public static Extractor getInstance() {
        if (extractor == null) {
            synchronized (OtherExtractor.class) {
                if (extractor == null) {
                    extractor = new OtherExtractor();
                }
            }
        }
        return extractor;
    }

    @Override
    public void extract(InfoModel infoModel) {
        //提取手机号
        List<String> phones = PhoneUtil.matchAllPhone(infoModel.getSourceInfo().replaceAll(NsfocusConstant.blanke, ""));
        infoModel.setPhone(PhoneUtil.join(phones));
        String sourceInfo = infoModel.getSourceInfo();
        for (String phone : phones) {
            sourceInfo = sourceInfo.replaceAll(phone, "");
        }
        //按所有符号分割
        List<String> words = Arrays.asList(sourceInfo.split(NsfocusConstant.symbols)).stream()
                .map(s -> s.endsWith("收") ? s.substring(0, s.length() - 1) : s).collect(Collectors.toList());
        //去除停用词
        words.removeAll(NsfocusConstant.stopwords);
        //提取人名
        List<String> names = words.stream().filter(w -> PersonName.is(w)).collect(Collectors.toList());
        if (names.isEmpty()) {
            List<String> segment = SegmentUtil.segment(StringUtils.join(words, ","));
            names = segment.stream().filter(w -> PersonName.is(w)).collect(Collectors.toList());
        }
        infoModel.setName(StringUtils.join(names, ","));
        //剩下的合成地址
        words.removeAll(names);
        infoModel.setAddress(StringUtils.join(words, ""));
    }
}
