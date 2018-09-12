package com.hom.juc.reentrantLock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo implements Runnable {

    ReentrantLock reentrantLock = new ReentrantLock();
    public static int i = 0;

    @Override
    public void run() {
        for (int k=0;k<100000;k++){
            reentrantLock.lock();
            try {
                i++;
            }finally {
                reentrantLock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockDemo reentrantLockDemo = new ReentrantLockDemo();
        Thread t1 = new Thread(reentrantLockDemo);
        Thread t2 = new Thread(reentrantLockDemo);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("i="+i);
    }
}
