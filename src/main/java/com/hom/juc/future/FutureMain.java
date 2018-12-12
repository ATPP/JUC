package com.hom.juc.future;

public class FutureMain {

    public static void main(String[] args) {
        long beginTime = System.currentTimeMillis();
        Client client = new Client();
        Data data = client.request("数据");
        System.out.println("请求完毕");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("realData:" + data.getResut());
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - beginTime + "ms");
    }
}
