package com.hom.juc.futureJDK;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FutureJDKMain {

    public static void main(String[] args) {
        FutureTask<String> futureTask = new FutureTask<>(new RealDataJDK("hello"));
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(futureTask);
        System.out.println("请求完毕");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("这里有个2秒操作");
        try {
            System.out.println("最后的数据" + futureTask.get());
            executorService.shutdown();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (InterruptedException e){
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static RuntimeException launderThtowable(Throwable t){
        if (t instanceof RuntimeException){
            return (RuntimeException) t;
        }else if (t instanceof Error){
            throw (Error) t;
        }else {
            throw new IllegalStateException("not unchecked", t);
        }
    }
}
