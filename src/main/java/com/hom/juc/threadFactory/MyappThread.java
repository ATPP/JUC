package com.hom.juc.threadFactory;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyappThread extends Thread {
    public final static String DEFAULT_NAME = "MyAPPThread";
    private static volatile boolean debugLifecycle = false;
    private final static AtomicInteger created = new AtomicInteger();
    private final static AtomicInteger alive = new AtomicInteger();
    private final Logger log = Logger.getAnonymousLogger();

    public MyappThread(Runnable r) {
        this(r, DEFAULT_NAME);
    }

    public MyappThread(Runnable r, String poolName) {
        super(r, poolName + "-" + created.incrementAndGet());
        setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                log.log(Level.SEVERE, "UNCAUGHT in thread" + t.getName(), e);
            }
        });
    }

    @Override
    public void run() {
//        复制debuglifecycle以确保一致的值
        boolean debug = debugLifecycle;
        if (debug) {
            log.log(Level.FINE, "Created" + getName());
        }
        try {
            alive.incrementAndGet();
            super.run();
        } finally {
            alive.decrementAndGet();
            if (debug) {
                log.log(Level.FINE, "Exit" + getName());
            }
        }
    }

    public boolean isDebugLifecycle() {
        return debugLifecycle;
    }

    public static AtomicInteger getCreated() {
        return created;
    }

    public static AtomicInteger getAlive() {
        return alive;
    }


}
