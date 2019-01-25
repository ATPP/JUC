package com.hom.juc.stopThread.log;

import java.io.PipedReader;
import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;

public class LogService {
    private BlockingQueue<String> queue;
    private LoggerThread loggerThread;
    private PrintWriter printWriter;
    private boolean isShutdown;
    private int reservations;

    public void start() {
        loggerThread.start();
    }

    public void stop() {
        synchronized (this) {
            isShutdown = true;
        }
        loggerThread.interrupt();
    }

    public void log(String msg) {
        synchronized (this) {
            isShutdown = true;
        }
        loggerThread.interrupt();
    }

    public class LoggerThread extends Thread {

        @Override
        public void run() {
            while (true) {
                try {
                    synchronized (LogService.this) {
                        if (isShutdown && reservations == 0) {
                            break;
                        }
                    }
                    String msg = queue.take();
                    synchronized (LogService.this) {
                        --reservations;
                    }
                    printWriter.println(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    printWriter.close();
                }
            }
        }
    }
}
