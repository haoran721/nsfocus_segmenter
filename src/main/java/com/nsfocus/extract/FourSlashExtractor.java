package com.nsfocus.extract;

import com.nsfocus.pojo.InfoModel;

/**
 * @Description
 * @Author wanghui
 * @Date 2019/1/25 11:45
 **/
public class FourSlashExtractor implements Extractor {
    private static Extractor extractor;

    private FourSlashExtractor() {
    }

    public static Extractor getInstance() {
        if (extractor == null) {
            synchronized (FourSlashExtractor.class) {
                if (extractor == null) {
                    extractor = new FourSlashExtractor();
                }
            }
        }
        return extractor;
    }

    @Override
    public void extract(InfoModel infoModel) {
        String[] strings = infoModel.getSourceInfo().split("/");
        infoModel.setCompany(strings[0]);
        infoModel.setAddress(strings[1] + "/" + strings[2]);
        infoModel.setName(strings.length == 5 ? strings[3] : "");
        infoModel.setPhone(strings.length == 5 ? strings[4] : "");
    }
}
