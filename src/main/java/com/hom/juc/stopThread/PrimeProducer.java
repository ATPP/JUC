package com.hom.juc.stopThread;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 通过中断来取消
 */
public class PrimeProducer extends Thread {

    private final BlockingQueue<BigInteger> queue;

    PrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    @Override
    public void run(){
        BigInteger p = BigInteger.ONE;
        try {
            while (!Thread.currentThread().isInterrupted()){
                queue.put(p = p.nextProbablePrime());
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void cancel(){
        interrupt();
    }

    public static void main(String[] args) {
        BlockingQueue<BigInteger> mainQueue = new LinkedBlockingDeque<>();
        PrimeProducer primeProducer = new PrimeProducer(mainQueue);
        try {
            primeProducer.start();
            Thread.sleep(100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            primeProducer.cancel();
        }
        System.out.println(mainQueue);
    }
}
