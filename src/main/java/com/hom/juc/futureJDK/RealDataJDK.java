package com.hom.juc.futureJDK;

import java.util.concurrent.Callable;

public class RealDataJDK implements Callable<String>{

    private String resultData;

    public RealDataJDK(String resultData){
        this.resultData = resultData;
    }

    @Override
    public String call() throws Exception {
        //具体的费时业务操作
        StringBuffer buffer = new StringBuffer();
        buffer.append(resultData);
        Thread.sleep(6000);
        return buffer.toString();
    }
}
