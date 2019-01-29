package com.nsfocus;

import com.nsfocus.constant.NsfocusConstant;
import com.nsfocus.extract.FourSlashExtractor;
import com.nsfocus.extract.NoneSymbolExtractor;
import com.nsfocus.extract.OtherExtractor;
import com.nsfocus.pojo.InfoModel;
import com.nsfocus.util.ExcelUtil;
import org.apache.commons.lang.StringUtils;
import org.apdplat.word.recognition.PersonName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @Description
 * @Author wanghui
 * @Date 2019/1/23 20:41
 **/
public class ProcessInfo {
    private static Logger logger = LoggerFactory.getLogger(ProcessInfo.class);

    public static void process(List<InfoModel> infoModelList) {
        for (InfoModel infoModel : infoModelList) {
            try {
                //判断是否为  4 /  类型提取
                if (is4Slash(infoModel.getSourceInfo())) {
                    FourSlashExtractor.getInstance().extract(infoModel);
                }
                //判断是否为  无任何符号 类型
                else if (isNoneSymbol(infoModel.getSourceInfo())) {
                    NoneSymbolExtractor.getInstance().extract(infoModel);
                }
                //其他类型
                else {
                    OtherExtractor.getInstance().extract(infoModel);
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("无法处理:{}", infoModel);
            }
        }
    }

    private static boolean is4Slash(String str) {
        return 4 == StringUtils.countMatches(str, "/");
    }

    private static boolean isNoneSymbol(String str) {
        return StringUtils.containsNone(str, NsfocusConstant.symbol);
    }

    public static void main(String[] args) {

//        System.out.println(processInfo.is4Slash("中国电信股份有限公司江西分公司/南昌, 江西, 330046, CN/省政府大院北一路2号/南昌市北京东路1969号/ /"));
        String filepath = "C:/Users/WH/Desktop/梅姐/需要拆分数据.xlsx";
        String outFilepath = "C:/Users/WH/Desktop/梅姐/test_out_"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) +".xlsx";
        List<InfoModel> infoModels = ExcelUtil.readExcel(filepath);
        ProcessInfo.process(infoModels);
        ExcelUtil.writeExcel(outFilepath, infoModels);
        System.out.println(PersonName.is("黑晓旭"));

//        String s = "北京市海淀区海淀路19-1号中成大厦1101室毛晓峰13701281515";
//        String[] split = s.split(NsfocusConstant.symbols);
//        for (String s1 : split) {
//            System.out.println(s1);
//        }
//
//        InfoModel model = new InfoModel();
//        model.setSourceInfo(s);
//        NoneSymbolExtractor.getInstance().extract(model);
//        System.out.println(model);
    }
}
