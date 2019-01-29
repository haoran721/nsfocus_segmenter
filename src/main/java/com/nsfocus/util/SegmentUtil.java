package com.nsfocus.util;

import org.apdplat.word.WordSegmenter;
import org.apdplat.word.recognition.PersonName;
import org.apdplat.word.segmentation.SegmentationAlgorithm;
import org.apdplat.word.segmentation.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author wanghui
 * @Date 2019/1/25 16:26
 **/
public class SegmentUtil {
    public static List<String> segment(String str) {
        List<Word> words = WordSegmenter.seg(str, SegmentationAlgorithm.MaxNgramScore);
        return words.stream().map(Word::getText).collect(Collectors.toList());
    }
}
