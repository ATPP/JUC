package com.hom.thread.exceple.station;

/**
 * 对同一个数量进行操作
 */
public class Station extends Thread {
    /**
     * 1.票数要使用同一个静态值
       2.为保证不会出现卖出同一个票数，要java多线程同步锁。
     */

    static int ticketTotal = 300;

    static Object ob = "aa";//值是任意的

    public Station(String name){
        super(name);
    }

    @Override
    public void run() {
        while (ticketTotal>0){
            synchronized (Station.class) {
                if (ticketTotal > 0) {
                    ticketTotal = ticketTotal - 1;
                    System.out.println("还剩" + ticketTotal + "张票");
                }else {
                    //提醒其它线程票没了
                    System.out.println("卖完了");
                }
            }
        }
    }
}
