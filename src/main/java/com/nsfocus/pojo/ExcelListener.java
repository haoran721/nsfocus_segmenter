package com.nsfocus.pojo;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author wanghui
 * @Date 2019/1/23 11:14
 **/
public class ExcelListener extends AnalysisEventListener<InfoModel> {
    private List<InfoModel> infoModelList = new ArrayList<>();
    private static Logger logger = LoggerFactory.getLogger(ExcelListener.class);
    private long startTime = System.currentTimeMillis();

    @Override
    public void invoke(InfoModel o, AnalysisContext analysisContext) {
        infoModelList.add(o);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        logger.info("读取Excel完毕   总行数:{}  耗时:{}毫秒", analysisContext.getCurrentRowNum(), System.currentTimeMillis() - startTime);
    }

    public List<InfoModel> getInfoModelList() {
        return infoModelList;
    }

    public void setInfoModelList(List<InfoModel> infoModelList) {
        this.infoModelList = infoModelList;
    }
}
