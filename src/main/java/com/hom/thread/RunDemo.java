package com.hom.thread;

public class RunDemo {

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        DemoThread demoThread = new DemoThread();
        demoThread.start();

        DemoThread1 demoThread1 = new DemoThread1();
        Thread thread = new Thread(demoThread1);
        thread.start();

        demoThread.join();
        thread.join();
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);

    }

    static class DemoThread extends Thread {
        double k = 0;

        @Override
        public void run() {
            for (double i = 0; i < 1232131232.0; i++) {
                k = k + i;
            }
            System.out.println(k);
        }
    }

    static class DemoThread1 implements Runnable {
        double m = 0;

        @Override
        public void run() {
            for (double n = 0; n < 1564432421.0; n++) {
                m = m + n;
            }
            System.out.println(m);
        }
    }


}
