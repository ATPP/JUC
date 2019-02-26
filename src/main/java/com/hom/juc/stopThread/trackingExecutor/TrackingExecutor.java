package com.hom.juc.stopThread.trackingExecutor;

import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class TrackingExecutor extends AbstractExecutorService {

    @Override
    public void execute(Runnable command) {

    }
}
