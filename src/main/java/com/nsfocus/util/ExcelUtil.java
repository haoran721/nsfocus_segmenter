package com.nsfocus.util;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.nsfocus.pojo.ExcelListener;
import com.nsfocus.pojo.InfoModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;

/**
 * @Description
 * @Author wanghui
 * @Date 2019/1/23 11:10
 **/
public class ExcelUtil {
    private static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    public static List<InfoModel> readExcel(InputStream inputStream) {
        ExcelListener excelListener = new ExcelListener();
        EasyExcelFactory.readBySax(inputStream, new Sheet(1, 1, InfoModel.class), excelListener);
        return excelListener.getInfoModelList();
    }

    public static List<InfoModel> readExcel(String filepath) {
        File file = new File(filepath);
        try (FileInputStream inputStream = new FileInputStream(file);) {
            logger.info("读取Excel  文件:{}", filepath);
            return readExcel(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeExcel(OutputStream outputStream, List<InfoModel> infoModels) {
        try {
            ExcelWriter writer = new ExcelWriter(outputStream, ExcelTypeEnum.XLSX);
            Sheet sheet = new Sheet(1, 0, InfoModel.class);
            writer.write(infoModels, sheet);
            writer.finish();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void writeExcel(String filepath, List<InfoModel> infoModels) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filepath);){

            writeExcel(fileOutputStream, infoModels);
            logger.info("写入Excel  文件:{}", filepath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
