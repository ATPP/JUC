package com.hom.juc.future;

import org.springframework.util.StringUtils;

public class RealData implements Data {

    private String resultData;

    public RealData(String param) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(param);
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!StringUtils.isEmpty(buffer)) {
            resultData = buffer.toString();
        }
    }

    @Override
    public String getResut() {
        return resultData;
    }
}
