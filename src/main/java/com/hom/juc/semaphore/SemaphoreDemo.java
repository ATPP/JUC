package com.hom.juc.semaphore;

import java.util.concurrent.*;

public class SemaphoreDemo implements Runnable {

//    初始许可数量
    Semaphore semaphore = new Semaphore(5);

    @Override
    public void run() {
        try {
//            semaphore.acquire();
//            获取许可
            semaphore.acquire(2);
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
//            semaphore.release();
//            释放许可
            semaphore.release(2);
        }
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(20);
        SemaphoreDemo demo = new SemaphoreDemo();
        for (int i = 0; i < 20; i++) {
            service.submit(demo);
        }
    }
}
