package com.hom.juc.threadPool;

import org.springframework.beans.factory.annotation.Autowired;

public class ExcuteThread implements Runnable{

    @Autowired
    private ThreadProcessService threadProcessService;

    public ExcuteThread(ThreadProcessService threadProcessService){
        this.threadProcessService = threadProcessService;
    }

    public void run() {
        threadProcessService.processSomething();
    }
}
