package com.hom.juc.reentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
* @author wangh
* @description : 等待和唤醒，唤醒时需要加锁
* @date : 18/9/12 _ 下午9:58
*/
public class ConditionDemo implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();

    @Override
    public void run() {
        try{
            lock.lock();
//            condition.await(2000, TimeUnit.SECONDS);
            condition.await();
            System.out.println("gogogo");
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
            e.printStackTrace();
        }finally {
            if (lock.isHeldByCurrentThread()){
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConditionDemo demo = new ConditionDemo();
        Thread t1 = new Thread(demo);
        t1.start();
        Thread.sleep(1000);
//        t1.interrupt();
        //唤醒时需要加锁
        lock.lock();
        condition.signal();
        lock.unlock();
    }
}
