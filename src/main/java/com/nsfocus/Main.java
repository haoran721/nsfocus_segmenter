package com.nsfocus;

import com.nsfocus.pojo.InfoModel;
import com.nsfocus.segment.WordEvaluation;
import com.nsfocus.util.ExcelUtil;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description
 * @Author wanghui
 * @Date 2019/1/21 18:26
 **/
public class Main {

    public static Map<String, Set<String>> contrast(String text){
        Map<String, Set<String>> map = new LinkedHashMap<>();
        map.put("word分词器", new WordEvaluation().seg(text));
        return map;
    }
    public static Map<String, Map<String, String>> contrastMore(String text){
        Map<String, Map<String, String>> map = new LinkedHashMap<>();
        map.put("word分词器", new WordEvaluation().segMore(text));
        return map;
    }
    public static void show(Map<String, Set<String>> map){
        map.keySet().forEach(k -> {
            System.out.println(k + " 的分词结果：");
            AtomicInteger i = new AtomicInteger();
            map.get(k).forEach(v -> {
                System.out.println("\t" + i.incrementAndGet() + " 、" + v);
            });
        });
    }
    public static void showMore(Map<String, Map<String, String>> map){
        map.keySet().forEach(k->{
            System.out.println(k + " 的分词结果：");
            AtomicInteger i = new AtomicInteger();
            map.get(k).keySet().forEach(a -> {
                System.out.println("\t" + i.incrementAndGet()+ " 、【"   + a + "】\t" + map.get(k).get(a));
            });
        });
    }
    public static void main(String[] args) {
        String s = "长春市绿园区景阳大路与普阳街交汇中海凯旋门商业广场A5栋1434室焦庆春13843003064";
        show(contrast(s));
//        showMore(contrastMore(s));
//        List<InfoModel> infoModels = ExcelUtil.readExcel("C:/Users/WH/Desktop/梅姐/需要拆分数据.xlsx");
//        for (InfoModel infoModel : infoModels) {
//            System.out.println(infoModel);
//        }
    }
}
