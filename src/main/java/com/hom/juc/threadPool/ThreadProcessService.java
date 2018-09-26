package com.hom.juc.threadPool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ThreadProcessService {

    Logger logger = LoggerFactory.getLogger(ThreadProcessService.class);

    public void processSomething() {
        logger.info("start-processSomething" + Thread.currentThread());
        System.out.println("要做的事情");
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        logger.info("end-processSomething" + Thread.currentThread());
    }
}
