package com.hom.juc.futureJDK;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        new Thread(() -> {
            completableFuture.complete(doSomeThing());
        }).start();
        doSomeThingElse();
        System.out.println(completableFuture.get());
    }

    private static void doSomeThingElse() {
        try {
            Thread.sleep(5000);
            System.out.println("完成5秒任务");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String doSomeThing() {
        try {
            Thread.sleep(3000);
            System.out.println("完成3秒任务");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "要返回的数据："+Thread.currentThread().getName();
    }
}
