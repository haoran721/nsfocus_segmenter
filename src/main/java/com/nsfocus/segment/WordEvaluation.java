package com.nsfocus.segment;

import org.apdplat.word.WordSegmenter;
import org.apdplat.word.recognition.PersonName;
import org.apdplat.word.segmentation.SegmentationAlgorithm;
import org.apdplat.word.segmentation.Word;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author wanghui
 * @Date 2019/1/21 18:23
 **/
public class WordEvaluation implements Segmenter {
    @Override
    public Map<String, String> segMore(String text) {
        Map<String, String> map = new LinkedHashMap<>();
        for(SegmentationAlgorithm segmentationAlgorithm : SegmentationAlgorithm.values()){
//        SegmentationAlgorithm segmentationAlgorithm = SegmentationAlgorithm.MaxNgramScore;
            map.put(segmentationAlgorithm.getDes(), seg(text, segmentationAlgorithm));
        }
        return map;
    }
    private static String seg(String text, SegmentationAlgorithm segmentationAlgorithm) {
        StringBuilder result = new StringBuilder("【" + segmentationAlgorithm.getDes() + "】");
        List<Word> words = WordSegmenter.segWithStopWords(text, segmentationAlgorithm);
        for(Word word : words){
            result.append(word.getText()).append(" ");
        }
        System.out.println("==========人名==========");
        for (Word word : words) {
            if (PersonName.is(word.getText())) {
                System.out.println("is"+word.getText());
            }
            if (PersonName.isSurname(word.getText())) {
                System.out.println("isSurname"+word.getText());
            }
        }
        return result.toString();
    }

}
