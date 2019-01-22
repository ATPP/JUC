package com.hom.juc.stopThread;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * 仅运行一秒的素数生成器
 */
public class PrimeGenerator implements Runnable {

    private final List<BigInteger> bigIntegers = new ArrayList<>();
    private volatile boolean cancelled;

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        while (!cancelled) {
            p = p.nextProbablePrime();
            synchronized (this) {
                bigIntegers.add(p);
            }
        }
    }

    public void cancle() {
        cancelled = true;
    }

    public synchronized List<BigInteger> get() {
        return new ArrayList<>(bigIntegers);
    }

    public static void main(String[] args) {
        aSecondOfPrimes();
    }

    private static List<BigInteger> aSecondOfPrimes(){
        PrimeGenerator primeGenerator = new PrimeGenerator();
        new Thread(primeGenerator).start();
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            primeGenerator.cancle();
        }
        System.out.println(primeGenerator.get());
        return primeGenerator.get();
    }
}
