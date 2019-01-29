package com.nsfocus.extract;

import com.nsfocus.constant.NsfocusConstant;
import com.nsfocus.pojo.InfoModel;
import com.nsfocus.util.PhoneUtil;
import com.nsfocus.util.SegmentUtil;
import org.apache.commons.lang.StringUtils;
import org.apdplat.word.recognition.PersonName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author wanghui
 * @Date 2019/1/25 11:45
 **/
public class NoneSymbolExtractor implements Extractor {
    private static Logger logger = LoggerFactory.getLogger(NoneSymbolExtractor.class);
    private static Extractor extractor;

    private NoneSymbolExtractor() {
    }

    public static Extractor getInstance() {
        if (extractor == null) {
            synchronized (NoneSymbolExtractor.class) {
                if (extractor == null) {
                    extractor = new NoneSymbolExtractor();
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
        //分词
        List<String> words = SegmentUtil.segment(infoModel.getSourceInfo());
        logger.info("分词结果:【{}】", StringUtils.join(words, " "));
        //提取姓名
        List<String> names = words.stream().filter(s -> PersonName.is(s)).collect(Collectors.toList());
        infoModel.setName(StringUtils.join(names, ","));
        //剩下的合成地址
        words.removeAll(phones);
        words.removeAll(names);
        infoModel.setAddress(StringUtils.join(words, ""));
    }
}
