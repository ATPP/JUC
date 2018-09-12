package com.hom.juc.reentrantLock;

import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

public class LockInterrupt implements Runnable {
    ReentrantLock lock1 = new ReentrantLock();
    ReentrantLock lock2 = new ReentrantLock();
    int i;

    public LockInterrupt(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        try {
            if (i == 1) {
                lock1.lock();
                try {
                    sleep(1500);
                    lock2.lock();
                } catch (InterruptedException e) {

                }
            } else {
                lock2.lock();
                try {
                    sleep(1500);
                    lock1.lock();
                } catch (InterruptedException e) {

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            if (lock1.isHeldByCurrentThread()) {
//                lock1.unlock();
//            }
//            if (lock2.isHeldByCurrentThread()) {
//                lock2.unlock();
//            }
            System.out.println("tuichu");
        }
    }

    public static void main(String[] args) {
        LockInterrupt lockInterrupt = new LockInterrupt(1);
        LockInterrupt lockInterrupt1 = new LockInterrupt(2);
        Thread t1 = new Thread(lockInterrupt);
        Thread t2 = new Thread(lockInterrupt1);
        t1.start();
        t2.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
